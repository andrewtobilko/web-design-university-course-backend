package com.tobilko.web;

import java.io.Serializable;

public class Feedback implements Serializable {

    private String message;

    public Feedback() {}

    public Feedback(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Feedback[" + message + "]";
    }

}
