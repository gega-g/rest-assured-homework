package ge.tbc.tbcitacademy.models.bookingData;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingData{

	@JsonProperty("booking")
	private Booking booking;

	@JsonProperty("bookingid")
	private int bookingid;

	public void setBooking(Booking booking){
		this.booking = booking;
	}

	public Booking getBooking(){
		return booking;
	}

	public void setBookingid(int bookingid){
		this.bookingid = bookingid;
	}

	public int getBookingid(){
		return bookingid;
	}

	@Override
 	public String toString(){
		return 
			"BookingData{" + 
			"booking = '" + booking + '\'' + 
			",bookingid = '" + bookingid + '\'' + 
			"}";
		}
}