package com.wgbt.wgbtapi;

import com.wgbt.wgbtapi.domain.User;

import javax.servlet.http.HttpSession;

public class HttpSessionUtills {
    // 유틸 클래스는 보통 상수선언
    public static final String USER_SESSION_KEY = "me";

    public static boolean isLoginUser(HttpSession session){
        Object me = session.getAttribute(USER_SESSION_KEY);
        if(me == null){
            return  false;
        }
        return true;
    }

    public static User getUserFromSession(HttpSession session){
        if(!isLoginUser(session)){
            return null;
        }
        return (User) session.getAttribute(USER_SESSION_KEY);
    }
}
