package edu.du.ict4315.parking.client;

import edu.du.ict4315.parking.ParkingOffice;
import edu.du.ict4315.parking.PermitManager;
import edu.du.ict4315.parking.Properties;
import edu.du.ict4315.parking.TransactionManager;
import edu.du.ict4315.parking.request.ParkingRequest;
import edu.du.ict4315.parking.response.ParkingResponse;
import edu.du.ict4315.parking.server.ParkingServer;

public class ParkingClient {

	public static void main(String[] args) throws Exception {

		Properties customerProps = new Properties();
		customerProps.setName("Name");
		customerProps.setStreetAddress1("Address1");
		customerProps.setCity("city");
		customerProps.setState("state");
		customerProps.setZipCode("1234");
		customerProps.setPhoneNumber("1234567890");

		ParkingRequest request = new ParkingRequest("CUSTOMER", customerProps);
		System.out.println(request.toJsonString());

		Properties carProps = new Properties();
		carProps.setCustomerId("CustomerId");
		carProps.setLicense("ABC123");
		carProps.setCarType("COMPACT");

		ParkingRequest request2 = new ParkingRequest("CAR", carProps);
		System.out.println(request2.toJsonString());

		// Create a ParkingClient instance
		ParkingClient client = new ParkingClient();

		// Send the request and get the response
		String responseMessage = client.sendRequestAndGetResponse(request);

		// Print the response message
		System.out.println("Response Message: " + responseMessage);

		// Send the request and get the response
		responseMessage = client.sendRequestAndGetResponse(request2);

		// Print the response message
		System.out.println("Response Message: " + responseMessage);

	}

	public String sendRequestAndGetResponse(ParkingRequest request) {
		// Serialize the ParkingRequest object to a JSON string
		String requestJson = request.toJsonString();
		TransactionManager tManager = new TransactionManager();
		PermitManager pManager = new PermitManager();
		ParkingOffice office = new ParkingOffice(tManager, pManager);
		ParkingServer server = new ParkingServer(office);

		// Simulate receiving the response JSON string from the server
		String responseJson = server.processRequest(requestJson);

		// Deserialize the response JSON string into a ParkingResponse object
		ParkingResponse response = ParkingResponse.fromJsonString(responseJson);

		// Return the ParkingResponse object
		return response.getMessage();
	}
}
