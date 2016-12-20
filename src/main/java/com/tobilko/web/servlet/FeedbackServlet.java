package com.tobilko.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tobilko.web.Feedback;
import com.tobilko.web.FeedbackParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@WebServlet(urlPatterns = "/api/feedback")
public class FeedbackServlet extends HttpServlet {

    private String MESSAGE_PARAM = "message";
    private FeedbackParser parser = FeedbackParser.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(APPLICATION_JSON_VALUE);
        PrintWriter writer = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, new Feedback(request.getParameter(MESSAGE_PARAM)));
        writer.flush();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(parser.parse(request.getReader()));
    }

}