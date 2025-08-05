package edu.du.ict4315.parking.charges.strategy;

/**
 * 
 * During weekends very few students will be coming to the college and to
 * encourage them to park the cars the parking charges are reduced.
 *
 */
public class DayBasedPricingStrategy implements ParkingChargeStrategy {

	@Override
	public double parkingCharge(double amount) {
		//Decrease the parking charges by 10%
		return amount * 0.9;
	}

}
