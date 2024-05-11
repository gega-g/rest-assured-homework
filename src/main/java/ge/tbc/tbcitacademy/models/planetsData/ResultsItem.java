package ge.tbc.tbcitacademy.models.planetsData;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;

@JsonPropertyOrder({
		"films",
		"edited",
		"created",
		"climate",
		"rotationPeriod",
		"url",
		"population",
		"orbitalPeriod",
		"surfaceWater",
		"diameter",
		"gravity",
		"name",
		"residents",
		"terrain"
})
public record ResultsItem(
		@NonNull
		@JsonProperty("films") List<String> films,
		@NonNull
		@JsonProperty("edited") String edited,
		@NonNull
		@JsonProperty("created")
		@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZZZZZ")
		LocalDateTime created,
		@JsonProperty("climate") String climate,
		@JsonProperty("rotation_period") String rotationPeriod,
		@JsonProperty("url") String url,
		@JsonProperty("population") String population,
		@JsonProperty("orbital_period") String orbitalPeriod,
		@JsonProperty("surface_water") String surfaceWater,
		@JsonProperty("diameter") String diameter,
		@JsonProperty("gravity") String gravity,
		@JsonProperty("name") String name,
		@JsonProperty("residents") List<String> residents,
		@JsonProperty("terrain") String terrain
) {
}
