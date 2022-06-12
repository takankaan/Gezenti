package com.sarigulsoftware.gezenti;

public class Utility {
    public static boolean isEmpty(String email,String password){
        return email.isEmpty() || password.isEmpty();
    }
    public static boolean isEmpty(String username, String email,String password,String passwordConfirm){
        return (username.isEmpty() || email.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty());
    }
}
