package com.kido.domain;

import lombok.Data;

@Data
public class Message {
       private String id;
       private String msg;
       private String from;
       private String to;
}
