package edu.du.ict4315.parking;

import java.util.Objects;
import java.util.stream.Stream;

public class RegisterCarCommand implements Command {

	private ParkingOffice parkingOffice;

	/**
	 * This method is to get the Command Name
	 */
	@Override
	public String getCommandName() {
		return "RegisterCarCommand";
	}

	/**
	 * This method is to get the Display name
	 */
	@Override
	public String getDisplayName() {
		return "RegisterCarCommand";
	}

	/**
	 * This method is to validate the params and provide the permit Id.
	 * 
	 */
	@Override
	public String execute(Properties params) {
		if (checkParameters(params)) {
			int size = parkingOffice.getCars().size();
			String permitId = "Permit " + (size+1);
			return permitId;
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
		return Stream.of(params.getLicense(), params.getCarType(), params.getCustomerId()).allMatch(Objects::nonNull);
	}

}
