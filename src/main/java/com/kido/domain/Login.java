package com.kido.domain;

import lombok.Data;

import java.io.Serializable;
@Data
public class Login implements Serializable{
    private String userId;
    private String name;
    private String passWard;
}
