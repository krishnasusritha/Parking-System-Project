package edu.du.ict4315.parking;

import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

/**
 * This is the manager class which manages all the parking permits.
 */
public class PermitManager {
	private HashMap<String, ParkingPermit> permits = new HashMap<>();

	/**
	   * This method will create an object of ParkingPermit class and will add it
	   * to the permits collection.
	   * Note: Assume that the expiration date will be one year from the current date.
	   */
	   public ParkingPermit register(Car car) {
		   ParkingPermit permit = new ParkingPermit();
			permit.setId(car.getPermitId());
			permit.setCar(car);
			Calendar now = Calendar.getInstance(TimeZone.getDefault());
			permit.setRegistrationDate(now);
			now.add(Calendar.YEAR, 1);
			permit.setExpirationDate(now);
			permits.put(car.getPermitId(), permit);
		   return permit;
	   }
}
