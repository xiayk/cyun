package com.cyun.utils.token;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cyun.dto.LoginUserDTO;
import com.cyun.exception.BadRequestException;
import com.cyun.exception.TokenException;
import com.cyun.exception.TokenUndefinedException;
import com.cyun.model.SysUser;
import com.cyun.utils.bean.BeanRewriteUtils;
import com.cyun.utils.spring.SpringContextHolder;
import com.cyun.utils.spring.UUIDFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/*
会话服务层的实现，用redis保存key-value格式，key为token，value为cashierId
 *
 */
public class UserToken {

    public final static String Cashier_SessionId_Prefix = "cyun-group:";

    /**
     * 过期时间为一天
     * TODO 正式上线更换为15分钟
     */
    private static final long EXPIRE_TIME = 24*60*60*1000;

    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "cyun-token-123456";

    @Value("${sys.param.test_token}")
    private String adminTestToken;


    /**
     * 获取登陆的 LoginAppUser
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static LoginUserDTO getLoginUserDTO(HttpServletRequest request, Long defaultUserId) throws Exception {
        String token = request.getHeader("token");
        // TODO 本地测试使用，不能提交上去，需注释掉
//        token = StringUtils.isEmpty(token) ? "sys_admin_test_token.1584760461693" : token;
        if (StringUtils.isEmpty(token)) {
            token = (String) request.getParameter("token");
            if (StringUtils.isEmpty(token) && BeanRewriteUtils.isNotNullOrEmpty(defaultUserId)) {
                throw new BadRequestException("请求没有带token.");
            }
        }
        if ("undefined".equals(token)) {
            throw new TokenUndefinedException("token有误，请重新登录(code)");
        }
        StringRedisTemplate stringRedisTemplate = SpringContextHolder.getApplicationContext().getBean(StringRedisTemplate.class);
        String sessionKey = Cashier_SessionId_Prefix + token;
        String s = stringRedisTemplate.opsForValue().get(sessionKey);
        if (BeanRewriteUtils.isNullOrEmpty(s)) {
            if (BeanRewriteUtils.isNullOrEmpty(defaultUserId)) {
                throw new TokenException("token过期或者无效，请重新登录");
            } else {
                return null;
            }
        }
        LoginUserDTO userDTO = JSON.parseObject(s, LoginUserDTO.class);
        return userDTO;
    }

    /**
     * 保存当前用户信息到redis
     * 1、查询当前用户在redis中存储的数据
     * 2、删除以前的数据
     * 3、保存新token
     */
    public static String saveLoginUserToken(LoginUserDTO user, String tokenId) {
        StringRedisTemplate stringRedisTemplate = SpringContextHolder.getApplicationContext().getBean(StringRedisTemplate.class);
        // 查询当前用户在redis中存储的数据
        // TODO 正式放开
//        Set<String> keys = stringRedisTemplate.keys(Cashier_SessionId_Prefix + tokenId + ".*");
//        // 删除以前的数据
//        for (String str : keys){
//            stringRedisTemplate.delete(str);
//        }
        // 保存新token
        tokenId = tokenId + "." + System.currentTimeMillis();
        String sessionKey = Cashier_SessionId_Prefix + tokenId;
        user.setToken(tokenId);
        stringRedisTemplate.opsForValue().set(sessionKey, JSON.toJSONString(user), 3, TimeUnit.DAYS);
        return tokenId;
    }

    /*
     跟新token的用户信息
     */
    public static void updateLoginUserToken(LoginUserDTO user, String tokenId) {
        StringRedisTemplate stringRedisTemplate = SpringContextHolder.getApplicationContext().getBean(StringRedisTemplate.class);
        String sessionKey = Cashier_SessionId_Prefix + tokenId;
        stringRedisTemplate.opsForValue().set(sessionKey, JSON.toJSONString(user), 3, TimeUnit.DAYS);
    }

    /**
     * 通过原来的token值
     *
     * @return
     */
    public static String updateLoginUserToken(HttpServletRequest request) throws Exception {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            token = (String) request.getParameter("token");
            if (StringUtils.isEmpty(token)) {
                throw new BadRequestException("请求没有带token.");
            }
        }
        StringRedisTemplate stringRedisTemplate = SpringContextHolder.getApplicationContext().getBean(StringRedisTemplate.class);
        String newToken = UUIDFactory.newUUID();
        String sessionKey = Cashier_SessionId_Prefix + token;
        String newSessionKey = Cashier_SessionId_Prefix + newToken;
        stringRedisTemplate.renameIfAbsent(sessionKey, newSessionKey);
        return newToken;
    }

    /**
     * 清空token
     * @param tokenId
     * @return
     */
    public static void removeLoginUserToken(String tokenId) {
        StringRedisTemplate stringRedisTemplate = SpringContextHolder.getApplicationContext().getBean(StringRedisTemplate.class);
        // 查询当前用户在redis中存储的数据
//        Set<String> keys = stringRedisTemplate.keys(Cashier_SessionId_Prefix + tokenId + ".*");
//        // 删除以前的数据
//        for (String str : keys){
//            stringRedisTemplate.delete(str);
//        }

        stringRedisTemplate.delete(TOKEN_AUTH + getJwtIdByToken(tokenId));
    }

    private static final String TOKEN_AUTH = "CYUN-AUTH:";

    /**
     * 生成签名
     * @param user
     * @return
     */
    public static String sign(LoginUserDTO user){
        StringRedisTemplate stringRedisTemplate = SpringContextHolder.getApplicationContext().getBean(StringRedisTemplate.class);

        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        String token = JWT.create()
                .withSubject(user.getAccount())
                .withKeyId(user.getId())
                .withIssuer(user.getParentId())
                .withClaim("time", System.currentTimeMillis())
                //.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))  //JWT 配置过期时间的正确姿势
                .sign(algorithm);
        //Redis缓存JWT
        stringRedisTemplate.opsForValue().set(TOKEN_AUTH + user.getId(), token, EXPIRE_TIME, TimeUnit.SECONDS);
        return token;
    }

    public static void verity(String token){
        StringRedisTemplate stringRedisTemplate = SpringContextHolder.getApplicationContext().getBean(StringRedisTemplate.class);
        try {
            //1 . 根据token解密，解密出jwt-id , 先从redis中查找出redisToken，匹配是否相同
            String redisToken = stringRedisTemplate.opsForValue().get(TOKEN_AUTH + getJwtIdByToken(token));
            //2 . 得到算法相同的JWTVerifier
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
//                    .withClaim("uid", getJwtIdByToken(token))
//                    .withClaim("account", getAccountByToken(token))
                    //.acceptExpiresAt(System.currentTimeMillis() + EXPIRE_TIME)  //JWT 正确的配置续期姿势
                    .build();
            //3 . 验证token
            DecodedJWT jwt = verifier.verify(redisToken);
            try {
                String a = stringRedisTemplate.opsForValue().get(TOKEN_AUTH + getJwtIdByToken(token));
                if (a == null) {
                    throw new TokenException("登录过期, 请重新登录");
                }
            }catch (Exception e){
                throw new TokenException("登录过期, 请重新登录");
            }
            //4 . Redis缓存JWT续期
            stringRedisTemplate.opsForValue().set(TOKEN_AUTH + getJwtIdByToken(token), redisToken, EXPIRE_TIME, TimeUnit.SECONDS);
        } catch (Exception e) { //捕捉到任何异常都视为校验失败
            throw new TokenException("登录过期, 请重新登录");
        }
    }

    public static String getJwtIdByToken(String token){
        try{
            return JWT.decode(token).getKeyId();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return "";
    }

    public static LoginUserDTO getJwtIdByToken(HttpServletRequest request){
        String token = request.getHeader("token");
        LoginUserDTO user = new LoginUserDTO();
        try{
            user.setToken(token);
            user.setAccount(JWT.decode(token).getSubject());
            user.setId(JWT.decode(token).getKeyId());
            user.setParentId(JWT.decode(token).getIssuer());
            return user;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
