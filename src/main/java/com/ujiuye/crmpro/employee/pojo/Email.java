package com.ujiuye.crmpro.employee.pojo;

import java.util.Date;

public class Email {
    private int eid;//收件人id
    private String receiver;//收件人邮箱号
    private String title;//主题
    private String content;//正文件
    private String isTime;//是否定时
    private Date time;//定时时间


    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsTime() {
        return isTime;
    }

    public void setIsTime(String isTime) {
        this.isTime = isTime;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "Email{" +
                "eid=" + eid +
                ", receiver='" + receiver + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", isTime='" + isTime + '\'' +
                ", time=" + time +
                '}';
    }
}
