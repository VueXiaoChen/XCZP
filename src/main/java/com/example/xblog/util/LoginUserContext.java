package com.example.xblog.util;



import com.example.xblog.resp.UserLoadingResp;

import java.io.Serializable;

public class LoginUserContext implements Serializable {

    private static ThreadLocal<UserLoadingResp> user = new ThreadLocal<>();

    public static UserLoadingResp getUser() {
        return user.get();
    }

    public static void setUser(UserLoadingResp user) {
        LoginUserContext.user.set(user);
    }

}
