package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResultsDAO implements Serializable {
    private final List<Result> results = new ArrayList<Result>();

    public void addResult(Result result) {
        results.add(result);
    }

    public List<Result> getResults() {
        return new ArrayList<>(results);
    }

    public void clearResults() {
        results.clear();
    }
}