package beans;

import javax.annotation.PostConstruct;
import java.io.Serializable;

public class AreaCheckBean implements Serializable {
    private ResultsDAO resultsDAO;
    private double x;
    private double y;
    private double r;

    public void checkPoint() {
        long startTime = System.nanoTime();
        int result = checkArea(x, y, r);
        long executionTime = System.nanoTime() - startTime;

        Result resultObj = new Result(x, y, r, result, executionTime);
        resultsDAO.addResult(resultObj);
    }

    private int checkArea(double x, double y, double r) {
        if (x <= 0 && y >= 0 && x >= -r/2 && y <= r) {
            return 1;
        }
        if (x >= 0 && y <= 0 && y >= x - r/2) {
            return 1;
        }
        if (x >= 0 && y >= 0 && x*x + y*y <= r*r) {
            return 1;
        }
        return 0;
    }

    // Getters and Setters
    public ResultsDAO getResultsDAO() { return resultsDAO; }
    public void setResultsDAO(ResultsDAO resultsDAO) { this.resultsDAO = resultsDAO; }
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    public double getR() { return r; }
    public void setR(double r) { this.r = r; }
}