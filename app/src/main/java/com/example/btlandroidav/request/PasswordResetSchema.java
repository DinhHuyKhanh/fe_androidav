package com.example.btlandroidav.request;

public class PasswordResetSchema {
    private String reset_password_token;
    private String new_password;

    public PasswordResetSchema(String reset_password_token, String new_password){
        this.reset_password_token = reset_password_token;
        this.new_password = new_password;
    }
}
