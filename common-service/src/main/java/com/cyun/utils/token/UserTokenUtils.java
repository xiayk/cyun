package com.cyun.utils.token;


import com.cyun.dto.LoginUserDTO;
import com.cyun.utils.http.RequestHolder;

public class UserTokenUtils {

    public static LoginUserDTO getLoginUserDTO(Long defaultUserId) throws Exception {
        return UserToken.getLoginUserDTO(RequestHolder.getRequest(), defaultUserId);
    }

    public static LoginUserDTO getLoginUserDTO() throws Exception {
        //return UserToken.getLoginUserDTO(RequestHolder.getRequest(), null);
        return UserToken.getJwtIdByToken(RequestHolder.getRequest());
    }

    public static String saveUserToken(LoginUserDTO user, String tokenId) {
        return UserToken.saveLoginUserToken(user, tokenId);
    }

    public static void updateUserToken(LoginUserDTO user, String tokenId) {
        UserToken.updateLoginUserToken(user, tokenId);
    }

    public static String updateUser() throws Exception {
        return UserToken.updateLoginUserToken(RequestHolder.getRequest());
    }

    public static String getLoginUserId() throws Exception {
        return UserToken.getLoginUserDTO(RequestHolder.getRequest(), null).getId();
    }

    public static String getToken(LoginUserDTO user){
        return UserToken.sign(user);
    }

    public static void verityToken(String token){
        UserToken.verity(token);
    }

    public static String getUidByToken(String token){
        return UserToken.getJwtIdByToken(token);
    }

    public static void removeUserToken(String tokenId) {
        UserToken.removeLoginUserToken(tokenId);
    }
}
