package ge.tbc.tbcitacademy.models.planetsData;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
@JsonPropertyOrder({
		"count",
		"next",
		"previous",
		"results",
})
 public record PlanetsData(
	@JsonProperty("count") int count,
	@JsonProperty("next") String next,
	@JsonProperty("previous") Object previous,
	@JsonProperty("results") List<ResultsItem> results
) {

}
