package ru.se.web.lab2.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req);
        if ((Objects.equals(req.getMethod(), "GET")) && (req.getParameter("x") != null) && (req.getParameter("y") != null) && (req.getParameterValues("r") != null)) {
            req.getRequestDispatcher("/area").include(req, resp);
        } else {
            String message = "Wrong data! Go away from command line";
            req.setAttribute("message", message);
        }
        System.out.println("hello");
    }
}