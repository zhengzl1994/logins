package com.kido.domain;

import lombok.Data;

@Data
public class Book {
    private Integer id;//书的id
    private String bookName;//书名
    private Integer number;//数量
    private String describes;//描述

}
