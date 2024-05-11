package ge.tbc.tbcitacademy.models.bookingData;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Bookingdates(

	@JsonProperty("checkin")  String checkin,

	@JsonProperty("checkout") String checkout
){

}