package com.fjnu.fjnu.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/*用户类*/
@TableName("user")
public class User<to> {
    //主键
    @TableId(value = "ID",type = IdType.AUTO)
    private Integer ID;
    private String uname;
    private String upass;
    //头像
    private String headpic;

    public User() {
    }

    public User(Integer ID, String uname, String upassword) {
        this.ID = ID;
        this.uname = uname;
        this.upass = upassword;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpassword() {
        return upass;
    }

    public void setUpassword(String upassword) {
        this.upass = upassword;
    }

    public void setHeadpic(String headpic){
        this.headpic=headpic;
    }
    public  String getHeadpic(){
        return headpic;
    }
    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", uname='" + uname + '\'' +
                ", upassword='" + upass + '\'' +
                '}';
    }



}
