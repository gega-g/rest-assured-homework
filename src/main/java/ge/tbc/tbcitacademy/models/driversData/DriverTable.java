package ge.tbc.tbcitacademy.models.driversData;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DriverTable{

	@JsonProperty("DriverTable")
	private DriverTable driverTable;

	@JsonProperty("Drivers")
	private List<DriversItem> drivers;

	@JsonProperty("season")
	private String season;

	public void setDriverTable(DriverTable driverTable){
		this.driverTable = driverTable;
	}

	public DriverTable getDriverTable(){
		return driverTable;
	}

	public void setDrivers(List<DriversItem> drivers){
		this.drivers = drivers;
	}

	public List<DriversItem> getDrivers(){
		return drivers;
	}

	public void setSeason(String season){
		this.season = season;
	}

	public String getSeason(){
		return season;
	}

	@Override
 	public String toString(){
		return 
			"DriverTable{" + 
			"driverTable = '" + driverTable + '\'' + 
			",drivers = '" + drivers + '\'' + 
			",season = '" + season + '\'' + 
			"}";
		}
}