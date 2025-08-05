package edu.du.ict4315.parking;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;

public class ParkingService {

	private final ParkingOffice parkingOffice;
	
	private Map<String, Command> commands = new HashMap<>();
	
	@Inject
	public ParkingService(ParkingOffice parkingOffice) {
		super();
		this.parkingOffice = parkingOffice;
		RegisterCarCommand carCommand = new RegisterCarCommand();
		carCommand.createParkingOffice(parkingOffice);
		register(carCommand);
		RegisterCustomerCommand customerCommand = new RegisterCustomerCommand();
		customerCommand.createParkingOffice(parkingOffice);
		register(customerCommand);
	}

	public void register(RegisterCarCommand command) {
		commands.put("CAR", command);
	}
	
	public void register(RegisterCustomerCommand command) {
		commands.put("CUSTOMER", command);
	}

	public String performCommand(String string, String[] customerProps) {
		Properties properties = new Properties();
		for(String prop : customerProps) {
			String[] keyValue = prop.split("=");
			switch(keyValue[0]) {
			case "customerId":
				properties.setCustomerId(keyValue[1]);
				break;
			case "name":
				properties.setName(keyValue[1]);
				break;
			case "phone":
				properties.setPhoneNumber(keyValue[1]);
				break;
			case "streetAddress1":
				properties.setStreetAddress1(keyValue[1]);
				break;
			case "streetAddress2":
				properties.setStreetAddress2(keyValue[1]);
			case "city":
				properties.setCity(keyValue[1]);;
				break;
			case "state":
				properties.setState(keyValue[1]);
				break;
			case "zipCode":
				properties.setZipCode(keyValue[1]);
				break;
			case "license":
				properties.setLicense(keyValue[1]);
				break;
			case "carType":
				properties.setCarType(keyValue[1]);
				break;
			}
		}
		Command command= commands.get(string);
		return command.execute(properties);
	}

	public ParkingOffice getParkingOffice() {
		return parkingOffice;
	}

	public Map<String, Command> getCommands() {
		return commands;
	}

	public void setCommands(Map<String, Command> commands) {
		this.commands = commands;
	}
}
