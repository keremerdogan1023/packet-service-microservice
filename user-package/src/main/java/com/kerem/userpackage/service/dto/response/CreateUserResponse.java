package com.kerem.userpackage.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponse {
    private int id;
    private String name;
    private String surname;
    private String username;
    private int age;
}

