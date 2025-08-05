package edu.du.ict4315.parking.server.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import edu.du.ict4315.parking.ParkingModule;
import edu.du.ict4315.parking.server.ParkingServer;

public class ParkingServerTest {
	
	protected Injector injector = Guice.createInjector(new ParkingModule());
	
	@Before
	public void setup() throws Exception {
		injector.injectMembers(this);
		
	}

	@Test
	public void testParkingRequestAndResponse() {
		// Sample JSON request string to register a customer
		String requestJson = "{\"commandName\":\"CUSTOMER\",\"properties\":{\"name\":\"Name\",\"streetAddress1\":\"Address1\",\"city\":\"city\",\"state\":\"state\",\"zipCode\":\"1234\",\"phoneNumber\":\"1234567890\",\"cars\":[]}}\r\n";

		// Create a ParkingServer instance
		ParkingServer server = injector.getInstance(ParkingServer.class);

		// Process the request and get the response as a JSON string
		String responseJson = server.processRequest(requestJson);
		
		Assert.assertEquals("{\"statusCode\":200,\"message\":\"Request processed successfully. Object registered with id Customer 1\"}", responseJson);

		// Sample JSON request string to register a customer with missing properties
		requestJson = "{\"commandName\":\"CUSTOMER\",\"properties\":{\"streetAddress1\":\"Address1\",\"city\":\"city\",\"state\":\"state\",\"zipCode\":\"1234\",\"phoneNumber\":\"1234567890\",\"cars\":[]}}\r\n";

		// Process the request and get the response as a JSON string
		responseJson = server.processRequest(requestJson);

		Assert.assertEquals("{\"statusCode\":500,\"message\":\"Required Parameters are missing\"}", responseJson);

		// Sample JSON request string to register a car
		requestJson = "{\"commandName\":\"CAR\",\"properties\":{\"customerId\":\"Customer1\",\"cars\":[],\"license\":\"ABC123\",\"carType\":\"COMPACT\"}}";

		// Process the request and get the response as a JSON string
		responseJson = server.processRequest(requestJson);

		Assert.assertEquals("{\"statusCode\":200,\"message\":\"Request processed successfully. Object registered with id Permit 1\"}", responseJson);

		// Sample JSON request string to register a car with missing properties
		requestJson = "{\"commandName\":\"CAR\",\"properties\":{\"cars\":[],\"license\":\"ABC123\",\"carType\":\"COMPACT\"}}";

		// Process the request and get the response as a JSON string
		responseJson = server.processRequest(requestJson);

		Assert.assertEquals("{\"statusCode\":500,\"message\":\"Required Parameters are missing\"}", responseJson);

	}
}
