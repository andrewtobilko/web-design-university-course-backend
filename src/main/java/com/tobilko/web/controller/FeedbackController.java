package com.tobilko.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tobilko.web.entity.Feedback;
import com.tobilko.web.parser.FeedbackParser;
import com.tobilko.web.repository.NativeFeedbackRepository;
import com.tobilko.web.repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@WebServlet(urlPatterns = "/api/feedback/*")
public class FeedbackController extends HttpServlet {

    private FeedbackParser parser = FeedbackParser.getInstance();
    private Repository<Feedback> repository = new NativeFeedbackRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getWriter(), repository.getAll());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(201);

        Feedback givenFeedback = parser.parse(request.getReader());

        repository.save(givenFeedback);
        new ObjectMapper().writeValue(response.getWriter(), givenFeedback);
    }

}