package cz.erstegroup.example.demo.model.combined;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class CombinedResults {
    private List<CombinedResult> combinedResults;

    public List<CombinedResult> getCombinedResults() {
        return combinedResults;
    }

    public void setCombinedResults(List<CombinedResult> combinedResults) {
        this.combinedResults = combinedResults;
    }

    @Override
    public String toString() {
        return "CombinedResults{" +
                "combinedResults=" + combinedResults +
                '}';
    }
}
