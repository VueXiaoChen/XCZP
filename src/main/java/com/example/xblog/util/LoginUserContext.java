package com.example.xblog.util;



import com.example.xblog.resp.UsersLoadingResp;

import java.io.Serializable;

public class LoginUserContext implements Serializable {

    private static ThreadLocal<UsersLoadingResp> user = new ThreadLocal<>();

    public static UsersLoadingResp getUser() {
        return user.get();
    }

    public static void setUser(UsersLoadingResp user) {
        LoginUserContext.user.set(user);
    }

}
