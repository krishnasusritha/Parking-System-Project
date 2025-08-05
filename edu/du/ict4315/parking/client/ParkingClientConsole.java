package edu.du.ict4315.parking.client;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import edu.du.ict4315.parking.Properties;
import edu.du.ict4315.parking.request.ParkingRequest;

public class ParkingClientConsole {

	public static void main(String[] args) throws Exception {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter the command:");

			String commandName = sc.nextLine();
			Properties props = new Properties();
			
			ParkingRequest request;

			if (StringUtils.equalsIgnoreCase(commandName, "CUSTOMER")) {
				System.out.println("Enter the customer name:");
				props.setName(sc.nextLine());

				System.out.println("Enter the customer address line 1:");
				props.setStreetAddress1(sc.nextLine());

				System.out.println("Enter the city:");
				props.setCity(sc.nextLine());

				System.out.println("Enter the state:");
				props.setState(sc.nextLine());

				System.out.println("Enter the zip code:");
				props.setZipCode(sc.nextLine());

				System.out.println("Enter the mobile number:");
				props.setPhoneNumber(sc.nextLine());
				
				request = new ParkingRequest("CUSTOMER", props);

			} else if (StringUtils.equalsIgnoreCase(commandName, "CAR")) {
				System.out.println("Enter the customer Id:");
				props.setCustomerId(sc.nextLine());
				
				System.out.println("Enter the license plate number:");
				props.setLicense(sc.nextLine());

				System.out.println("Enter the type of the car (SUV or COMPACT) :");
				props.setCarType(sc.nextLine());
				
				request = new ParkingRequest("CAR", props);
			} else {
				System.out.println("Entered invalid command");
				throw new Exception();
			}

			// Create a ParkingClient instance
			ParkingClient client = new ParkingClient();
			// Send the request and get the response
			String responseMessage = client.sendRequestAndGetResponse(request);

			// Print the response message
			System.out.println("Response Message: " + responseMessage);
		}
	}
}
