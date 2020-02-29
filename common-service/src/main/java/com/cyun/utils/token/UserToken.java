package com.cyun.utils.token;

import com.alibaba.fastjson.JSON;
import com.cyun.dto.LoginUserDTO;
import com.cyun.exception.BadRequestException;
import com.cyun.exception.TokenException;
import com.cyun.exception.TokenUndefinedException;
import com.cyun.utils.bean.BeanRewriteUtils;
import com.cyun.utils.spring.SpringContextHolder;
import com.cyun.utils.spring.UUIDFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/*
会话服务层的实现，用redis保存key-value格式，key为token，value为cashierId
 *
 */
public class UserToken {

    public final static String Cashier_SessionId_Prefix = "zjkj-group:";

    /**
     * 获取登陆的 LoginAppUser
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static LoginUserDTO getLoginUserDTO(HttpServletRequest request, Long defaultUserId) throws Exception {
        String token = request.getHeader("token");
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

    /*
     保存
     */
    public static String saveLoginUserToken(LoginUserDTO user, String tokenId) {
        StringRedisTemplate stringRedisTemplate = SpringContextHolder.getApplicationContext().getBean(StringRedisTemplate.class);
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
}
