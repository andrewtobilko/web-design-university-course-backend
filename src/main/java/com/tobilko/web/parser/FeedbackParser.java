package com.tobilko.web.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tobilko.web.entity.Feedback;

import java.io.IOException;
import java.io.Reader;

public class FeedbackParser implements Parser<Feedback> {

    private FeedbackParser() {}

    private static FeedbackParser instance;

    public static FeedbackParser getInstance() {
        return instance == null ? (instance = new FeedbackParser()) : instance;
    }

    @Override
    public Feedback parse(Reader reader) throws IOException {
        String jsonString = "";
        int tmp;
        while((tmp = reader.read()) != -1) {
            jsonString = jsonString + (char)tmp;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, Feedback.class);
    }

}
