package edu.du.ict4315.parking.charges.strategy;

/**
 * 
 * If the car type is compact then the parking fee is reduced.
 *
 */
public class CarTypeBasedPricingStrategy implements ParkingChargeStrategy{

	@Override
	public double parkingCharge(double amount) {
		// Decreased parking prices to 20% for the Compact cars
		return amount*0.8;
	}

}
