package edu.du.ict4315.parking.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.inject.Inject;

import edu.du.ict4315.parking.ParkingOffice;
import edu.du.ict4315.parking.PermitManager;
import edu.du.ict4315.parking.TransactionManager;
import edu.du.ict4315.parking.client.ClientHandler;

public class MultiThreadedParkingServer {

	private static final int PORT = 12345;
	private ParkingServer parkingServer;

	@Inject
	public MultiThreadedParkingServer(ParkingServer server) {
		parkingServer = server;
	}

	public void start() {
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println("Server is listening on port " + PORT);
			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("New client connected");
				ClientHandler clientHandler = new ClientHandler(clientSocket, parkingServer);
				Thread thread = new Thread(clientHandler);
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TransactionManager tManager = new TransactionManager();
		PermitManager pManager = new PermitManager();
		ParkingOffice office = new ParkingOffice(tManager, pManager);
		ParkingServer pServer = new ParkingServer(office);
		MultiThreadedParkingServer server = new MultiThreadedParkingServer(pServer);
		server.start();
	}
}
