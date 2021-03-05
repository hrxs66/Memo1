package com.memo;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.Date;

public class Text extends LitePalSupport implements Serializable {
    private int id;
    private String title;
    private String time;
    private String text;
    private String kind;

    public Text(int id, String title, String time, String text, String kind) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.text = text;
        this.kind = kind;
    }

    public Text() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }


}
