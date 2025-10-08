package ru.se.web.lab2.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import beans.ResultsDAO;


public class ControllerServlet extends HttpServlet {

    private ResultsDAO resultsDAO;

    @Override
    public void init() throws ServletException {
        resultsDAO = new ResultsDAO();
        getServletContext().setAttribute("resultsDAO", resultsDAO);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req);
        if ((Objects.equals(req.getMethod(), "GET")) && (req.getParameter("x") != null) && (req.getParameter("y") != null) && (req.getParameterValues("r") != null)) {
           // req.getRequestDispatcher("/area").include(req, resp);
            System.out.println("chlen");

            RequestDispatcher dispatcher = req.getRequestDispatcher("/area");
            dispatcher.forward(req, resp);

        } else {
            String message = "Wrong data! Go away from command line";
            req.setAttribute("message", message);
        }
        System.out.println("hello");
    }
}