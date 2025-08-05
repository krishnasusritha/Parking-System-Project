package edu.du.ict4315.parking.response;

import com.google.gson.Gson;

public class ParkingResponse {
	private int statusCode;
	private String message;

	public ParkingResponse(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getMessage() {
		return message;
	}

	// Method to convert ParkingResponse to JSON string
	public String toJsonString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	// Method to convert JSON string to ParkingResponse
	public static ParkingResponse fromJsonString(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, ParkingResponse.class);
	}

	@Override
	public String toString() {
		return "ParkingResponse{" + "statusCode=" + statusCode + ", message='" + message + '\'' + '}';
	}
}
