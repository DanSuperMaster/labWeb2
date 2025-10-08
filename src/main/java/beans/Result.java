package beans;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Result implements Serializable {
    private double x;
    private double y;
    private double r;
    private int hit;
    private LocalDateTime timestamp;
    private long executionTime;

    public Result() {}

    public Result(double x, double y, double r, int hit, long executionTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
        this.timestamp = LocalDateTime.now();
        this.executionTime = executionTime;
    }

    // Getters and Setters
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    public double getR() { return r; }
    public void setR(double r) { this.r = r; }

    public int getHit() { return hit; }
    public void setHit(int hit) { this.hit = hit; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public long getExecutionTime() { return executionTime; }
    public void setExecutionTime(long executionTime) { this.executionTime = executionTime; }
}