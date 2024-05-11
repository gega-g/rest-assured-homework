package ge.tbc.tbcitacademy.models.bookingData;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BookingData(

	@JsonProperty("booking") Booking booking,

	@JsonProperty("bookingid") int bookingid
){

}