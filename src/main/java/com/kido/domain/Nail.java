package com.kido.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Nail {
    /*
     美甲活动
     */
    private  Integer id;    //编号
    private  String userName;   //姓名
    private  String tel;    //电话
    private  Date startTime;     //报名时间
    private  String place;  //工作地点
    private  String information; //信息获取渠道
    private  String kind;   // 参赛类别
}
