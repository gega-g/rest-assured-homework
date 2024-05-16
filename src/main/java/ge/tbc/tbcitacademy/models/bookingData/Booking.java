package ge.tbc.tbcitacademy.models.bookingData;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"bookingId",
		"bookingDates",
		"lastName",
		"totalPrice",
		"firstName",
		"depositPaid",
		"additionalNeeds"
})public record Booking (
	@JsonProperty("bookingId") int bookingId,
	@JsonProperty("bookingdates") Bookingdates bookingDates,
	@JsonProperty("lastname") String lastName,
	@JsonProperty("totalprice") int totalPrice,
	@JsonProperty("firstname") String firstName,
	@JsonProperty("depositpaid") boolean depositPaid,
	@JsonProperty("additionalneeds") String additionalNeeds,
	@JsonIgnore
//	@JsonProperty("saleprice") double salePrice,
	@JsonProperty("passportNo") String passportNo
){

}
