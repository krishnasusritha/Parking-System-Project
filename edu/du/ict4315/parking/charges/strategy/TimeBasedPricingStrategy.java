package edu.du.ict4315.parking.charges.strategy;

/**
 * 
 * If the car is parked in off-peak hours from 8 pm - 8 am, then the charges are reduced. 
 *
 */
public class TimeBasedPricingStrategy implements ParkingChargeStrategy{

	@Override
	public double parkingCharge(double amount) {
		// The charges are reduced by 10%
		return amount * 0.9;
	}

}
