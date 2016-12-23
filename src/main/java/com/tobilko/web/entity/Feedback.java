package com.tobilko.web.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Feedback implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String message;
    private String author;
    private Date postedBy;

    public Feedback() {
        postedBy = new Date();
    }

    public Feedback(String message, String author) {
        this();
        this.message = message;
        this.author = author;
    }

}