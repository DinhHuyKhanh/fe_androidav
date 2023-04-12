package com.example.btlandroidav.request;

public class UpdatePasswordSchema {
    private String old_password;
    private String new_password;

    public UpdatePasswordSchema(String old_password, String new_password){
        this.old_password = old_password;
        this.new_password = new_password;
    }
}
