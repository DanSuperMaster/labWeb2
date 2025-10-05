package ru.se.web.lab2.controllers;

import beans.Result;
import beans.ResultsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import utils.Calculator;




@WebServlet("/area")

public class AreaCheckServlet extends HttpServlet {
    ResultsDAO resultsDAO = new ResultsDAO();
    Calculator calculator = new Calculator();
    Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        Double x = Double.valueOf(req.getParameter("x"));
        Double y = Double.valueOf(req.getParameter("y"));

        String rParam = req.getParameter("r");
        String[] r = rParam.split(",");


        logFunc("r: " + Arrays.toString(r));
        for (int i = 0; i < r.length; i++) {
            logFunc("i: " + i);
            Double r_i = Double.valueOf(r[i]);
            long endTime = System.currentTimeMillis();
            if ((x == 1000) && (y == 1000) && (r_i == 1000)){
                break;
            }
            if (calculator.calculating(x, y, r_i)) {
                resultsDAO.addResult(new Result(x, y, r_i, 1, endTime - startTime));
                /*httpResponse = String.format(
                        "{\"x\": %s, \"y\": %s, \"r\": %s, \"control\": %d, \"timeOfProcess\": %d}",
                        x, y, r_i, 1, endTime - startTime
                );*/
            }
            else {
                resultsDAO.addResult(new Result(x, y, r_i, 0, endTime - startTime));
                /*httpResponse = String.format(
                        "{\"x\": %s, \"y\": %s, \"r\": %s, \"control\": %d, \"timeOfProcess\": %d}",
                        x, y, r_i, 0, endTime - startTime
                );*/
            }
            /*if ((i == 0) && (i != r.length - 1)) {
                ans.append("[").append(httpResponse).append(",\n");
            }
            else if (i != r.length - 1) {
                ans.append(httpResponse).append(",\n");
            }
            else if ((i == 0) && (i == r.length - 1)) {
                ans.append("[");
            }
            if (i == r.length - 1){
                ans.append(httpResponse).append("]");
            }*/
/*            logFunc("httpResponse: " + httpResponse);
            logFunc("ans: " + ans);*/
        }
        List<Result> results = resultsDAO.getResults();
        if (results.size() == 0) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("[]");
            resp.getWriter().flush();
        }
        else if (results.size() == 1) {
            String httpResponse = String.format(
                    "{\"x\": %s, \"y\": %s, \"r\": %s, \"control\": %d, \"timeOfProcess\": %d}",
                    results.get(0).getX(), results.get(0).getY(), results.get(0).getR(), results.get(0).isHit(), results.get(0).getExecutionTime());
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("[" + httpResponse + "]");
            resp.getWriter().flush();
        } else {

            StringBuilder ans = new StringBuilder();
            for (int i = 0; i < results.size(); i++) {

                String httpResponse = String.format(
                        "{\"x\": %s, \"y\": %s, \"r\": %s, \"control\": %d, \"timeOfProcess\": %d}",
                        results.get(i).getX(), results.get(i).getY(), results.get(i).getR(), results.get(i).isHit(), results.get(i).getExecutionTime());
                if ((i == 0) && (i != results.size() - 1)) {
                    ans.append("[").append(httpResponse).append(",\n");
                }
                else if (i != results.size() - 1) {
                    ans.append(httpResponse).append(",\n");
                }
                if (i == results.size() - 1){
                    ans.append(httpResponse).append("]");
                }
            }
            logFunc(ans.toString());
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(ans.toString());
            resp.getWriter().flush();
        }
    }

    public void logFunc(String st) {
        logger.info(st);
    }
}
