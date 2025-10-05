package beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class ResultsDAO implements Serializable {
    private final List<beans.Result> results = new ArrayList<Result>();

    public void addResult(beans.Result result) {
        results.add(result);
    }

    public List<Result> getResults() {
        return new ArrayList<>(results);
    }

    public void clearResults() {
        results.clear();
    }


}