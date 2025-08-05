package edu.du.ict4315.parking.server;

import com.google.inject.Inject;

import edu.du.ict4315.parking.Command;
import edu.du.ict4315.parking.ParkingOffice;
import edu.du.ict4315.parking.ParkingService;
import edu.du.ict4315.parking.request.ParkingRequest;
import edu.du.ict4315.parking.response.ParkingResponse;

public class ParkingServer {

	private ParkingOffice office;

	private ParkingService service;
	
	@Inject
	public ParkingServer(ParkingOffice office) {
		super();
		if(this.office == null) {
			this.office = office;
			this.service = new ParkingService(office);
		}
		
	}

	public synchronized String processRequest(String requestJson) {
		// Parse the JSON string into a ParkingRequest object
		ParkingRequest request = ParkingRequest.fromJsonString(requestJson);

		// Process the request and generate a response
		ParkingResponse response = processParkingRequest(request);

		// Serialize the ParkingResponse object to a JSON string
		return response.toJsonString();
	}

	private ParkingResponse processParkingRequest(ParkingRequest request) {
		Command command = service.getCommands().get(request.getCommandName());
		String objectId = command.execute(request.getProperties());
		if(objectId == null) {
			return new ParkingResponse(500, "Required Parameters are missing");
		}
		return new ParkingResponse(200, "Request processed successfully. Object registered with id " + objectId);
	}
	
	public ParkingOffice getOffice() {
		return office;
	}

	public ParkingService getService() {
		return service;
	}
}
