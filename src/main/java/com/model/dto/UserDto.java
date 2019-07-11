package com.model.dto;

import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String login;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
