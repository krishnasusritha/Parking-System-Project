package edu.du.ict4315.parking.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.commons.lang3.StringUtils;

import edu.du.ict4315.parking.Properties;
import edu.du.ict4315.parking.request.ParkingRequest;
import edu.du.ict4315.parking.response.ParkingResponse;
import edu.du.ict4315.parking.server.ParkingServer;

public class ClientHandler implements Runnable {
	private Socket clientSocket;
	private ParkingServer parkingServer;

	public ClientHandler(Socket socket, ParkingServer parkingServer) {
		this.clientSocket = socket;
		this.parkingServer = parkingServer;
	}

	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);) {
			handleClient(in, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Client handled in " + (endTime - startTime) + " ms");
	}

	private void handleClient(BufferedReader in, PrintWriter out) throws Exception {
		String inputLine;
		while ((inputLine = in.readLine()) != null || (inputLine = in.readLine()) != "exit" ) {
			out.println("Enter the command:");
			String commandName = in.readLine();
			Properties props = new Properties();

			ParkingRequest request;

			if (StringUtils.equalsIgnoreCase(commandName, "CUSTOMER")) {
				out.println("Enter the customer name:");
				props.setName(in.readLine());

				out.println("Enter the customer address line 1:");
				props.setStreetAddress1(in.readLine());

				out.println("Enter the city:");
				props.setCity(in.readLine());

				out.println("Enter the state:");
				props.setState(in.readLine());

				out.println("Enter the zip code:");
				props.setZipCode(in.readLine());

				out.println("Enter the mobile number:");
				props.setPhoneNumber(in.readLine());

				request = new ParkingRequest("CUSTOMER", props);

			} else if (StringUtils.equalsIgnoreCase(commandName, "CAR")) {
				out.println("Enter the customer Id:");
				props.setCustomerId(in.readLine());

				out.println("Enter the license plate number:");
				props.setLicense(in.readLine());

				out.println("Enter the type of the car (SUV or COMPACT) :");
				props.setCarType(in.readLine());

				request = new ParkingRequest("CAR", props);
			} else {
				out.println("Entered invalid command");
				throw new Exception();
			}

			String requestJson = request.toJsonString();
			String responseJson = parkingServer.processRequest(requestJson);
			ParkingResponse response = ParkingResponse.fromJsonString(responseJson);

			out.println("Response Message: " + response.getMessage() + "Type exit to exit the application");
		}
	}
}
