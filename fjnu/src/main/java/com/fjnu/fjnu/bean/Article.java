package com.fjnu.fjnu.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.sql.Date;

@TableName("t_article")

public class Article {
    //主键
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String title;
    private String scontent;
    private int pinglun;
    private int glance;
    private String author;
    private String hpic;

    public Article(Integer id, String title, String scontent, int pinglun, int glance, String author, String hpic, Date published) {
        this.id = id;
        this.title = title;
        this.scontent = scontent;
        this.pinglun = pinglun;
        this.glance = glance;
        this.author = author;
        this.hpic = hpic;
        this.published = published;
    }

    public String getHpic() {
        return hpic;
    }

    public void setHpic(String hpic) {
        this.hpic = hpic;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    private Date published;



    public int getPinglun() {
        return pinglun;
    }

    public void setPinglun(int pinglun) {
        this.pinglun = pinglun;
    }

    public int getGlance() {
        return glance;
    }

    public void setGlance(int glance) {
        this.glance = glance;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }





    public Article() {

    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScontent() {
        return scontent;
    }

    public void setScontent(String scontent) {
        this.scontent = scontent;
    }
}
