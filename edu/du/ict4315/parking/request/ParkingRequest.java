package edu.du.ict4315.parking.request;

import com.google.gson.Gson;

import edu.du.ict4315.parking.Properties;

public class ParkingRequest {
	private String commandName;
	private Properties properties;

	public ParkingRequest(String commandName, Properties customerProps) {
		this.commandName = commandName;
		this.properties = customerProps;
	}

	public String getCommandName() {
		return commandName;
	}

	public Properties getProperties() {
		return properties;
	}

	// Method to convert ParkingRequest to JSON string
	public String toJsonString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	// Method to convert JSON string to ParkingRequest
	public static ParkingRequest fromJsonString(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, ParkingRequest.class);
	}

	@Override
	public String toString() {
		return "ParkingRequest{" + "commandName='" + commandName + '\'' + ", properties=" + properties + '}';
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
}
