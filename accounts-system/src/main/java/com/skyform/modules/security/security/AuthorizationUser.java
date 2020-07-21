package com.skyform.modules.security.security;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AuthorizationUser {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String code;

    private String uuid = "";

    private String phone;

    @Override
    public String toString() {
        return "{username=" + username  + ", password= ******}";
    }
}
