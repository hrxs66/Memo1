package com.memo;

import java.io.Serializable;
import java.util.Date;

public class Display implements Serializable {
    private String title;
    private Date date;

    public Display(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    public Display() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
