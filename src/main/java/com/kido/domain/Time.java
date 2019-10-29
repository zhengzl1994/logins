package com.kido.domain;

import java.text.SimpleDateFormat;

public class Time {

    private String timeStr;

    public String getTimeStr() {
        return  new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").format(this.timeStr);
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }
}
