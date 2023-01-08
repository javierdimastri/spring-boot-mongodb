package com.javierdimastri.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailDTO {
    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;
}
