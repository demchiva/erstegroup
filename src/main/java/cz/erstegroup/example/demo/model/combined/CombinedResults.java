package cz.erstegroup.example.demo.model.combined;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(
        title = "Erste Combined Accounts with Products Response Body",
        description = "Returns details about Erste transparent accounts combined with product."
)
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
