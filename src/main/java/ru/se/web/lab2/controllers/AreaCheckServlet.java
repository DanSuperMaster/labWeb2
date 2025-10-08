package ru.se.web.lab2.controllers;

import beans.Result;
import beans.ResultsDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import utils.Calculator;




//@WebServlet("/area")

public class AreaCheckServlet extends HttpServlet {
    Calculator calculator = new Calculator();
    Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        double x = Double.parseDouble(req.getParameter("x"));
        double y = Double.parseDouble(req.getParameter("y"));

        String rParam = req.getParameter("r");
        String[] r = rParam.split(",");

        ResultsDAO resultsDAO = (ResultsDAO) getServletContext().getAttribute("resultsDAO");

        logFunc("r: " + Arrays.toString(r));
        for (int i = 0; i < r.length; i++) {
            logFunc("i: " + i);
            double r_i = Double.parseDouble(r[i]);
            long endTime = System.currentTimeMillis();
            if ((x == 1000) && (y == 1000) && (r_i == 1000)){
                break;
            }
            if (calculator.calculating(x, y, r_i)) {
                resultsDAO.addResult(new Result(x, y, r_i, 1, endTime - startTime));
            }
            else {
                resultsDAO.addResult(new Result(x, y, r_i, 0, endTime - startTime));
            }
        }
        List<Result> results = resultsDAO.getResults();
        if (results.isEmpty()) {
            /*resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("[]");
            resp.getWriter().flush();*/
        }
        else if (results.size() == 1) {
            String httpResponse = String.format(
                    "{\"x\": %s, \"y\": %s, \"r\": %s, \"control\": %d, \"timeOfProcess\": %d}",
                    results.get(0).getX(), results.get(0).getY(), results.get(0).getR(), results.get(0).getHit(), results.get(0).getExecutionTime());
            /*resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("[" + httpResponse + "]");
            resp.getWriter().flush();*/
        } else {

            StringBuilder ans = new StringBuilder();
            for (int i = 0; i < results.size(); i++) {

                String httpResponse = String.format(
                        "{\"x\": %s, \"y\": %s, \"r\": %s, \"control\": %d, \"timeOfProcess\": %d}",
                        results.get(i).getX(), results.get(i).getY(), results.get(i).getR(), results.get(i).getHit(), results.get(i).getExecutionTime());
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
            logFunc("ans.send: " + ans.toString());
            /*resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(ans.toString());
            resp.getWriter().flush();*/
        }

        req.setAttribute("x", x);
        req.setAttribute("y", y);
        req.setAttribute("r", r);
        req.setAttribute("result", 1);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/result.jsp");
        dispatcher.forward(req, resp);



    }

    public void logFunc(String st) {
        logger.info(st);
    }
}
