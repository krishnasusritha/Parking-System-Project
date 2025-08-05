package edu.du.ict4315.parking.charges.strategy;
/**
 * If the lot is not frequently used by everyone, then the prices are reduced a little
 * so that everyone will get used to park the cars in this lot.
 */
public class LotUsageBasedPricingStrategy implements ParkingChargeStrategy{

	@Override
	public double parkingCharge(double amount) {
		// A 10% discount is given if the lot is not frequently used.
		return amount*0.9;
	}

}
