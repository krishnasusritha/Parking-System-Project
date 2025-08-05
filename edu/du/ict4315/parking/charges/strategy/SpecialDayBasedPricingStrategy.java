package edu.du.ict4315.parking.charges.strategy;

/**
 * 
 * If an event is happening in the university, then more people will be willing to park their car
 * and during those days the parking prices are marked a little higher than the regular ones.
 *
 */
public class SpecialDayBasedPricingStrategy implements ParkingChargeStrategy{

	@Override
	public double parkingCharge(double amount) {
		// Increasing parking prices to 20% than the regular days
		return amount*1.2;
	}

}
