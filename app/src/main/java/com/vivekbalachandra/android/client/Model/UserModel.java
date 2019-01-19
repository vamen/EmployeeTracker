package com.vivekbalachandra.android.client.Model;

public class UserModel {

    private String user;
    private String token;

    public UserModel(String user){
        this.user=user;
        token=null;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
