package edu.du.ict4315.parking;

import java.util.Objects;
import java.util.stream.Stream;

public class RegisterCustomerCommand implements Command {

	private ParkingOffice parkingOffice;

	/**
	 * This method is to get the Command Name
	 */
	@Override
	public String getCommandName() {
		return "RegisterCustomerCommand";
	}

	/**
	 * This method is to get the Display Name
	 */
	@Override
	public String getDisplayName() {
		return "RegisterCustomerCommand";
	}

	/**
	 * execute method checks if the required properties are present or not and 
	 * assigns a customer id to the customer.
	 * @param Properties these are the required props for the customer to get registered.
	 */
	@Override
	public String execute(Properties params) {
		if (checkParameters(params)) {
			int size = parkingOffice.getCustomers().size();
			String customerId = "Customer " + (size+1);
			parkingOffice.getCustomerIds().add(customerId);
			return customerId;
		}
		return null;
	}

	/**
	 * create method is used to set the ParkingOffice object to the field.
	 * @param parkingOffice
	 */
	public void createParkingOffice(ParkingOffice parkingOffice) {
		setParkingOffice(parkingOffice);
	}

	public ParkingOffice getParkingOffice() {
		return parkingOffice;
	}

	public void setParkingOffice(ParkingOffice parkingOffice) {
		this.parkingOffice = parkingOffice;
	}

	/**
	 * checkParameters method checks if all the required properties are not null.
	 * @param Properties
	 */
	@Override
	public boolean checkParameters(Properties params) {
		return Stream.of(params.getStreetAddress1(), params.getCity(), params.getState(), params.getZipCode(), params.getName(),
				params.getPhoneNumber()).allMatch(Objects::nonNull);
		
	}

}
