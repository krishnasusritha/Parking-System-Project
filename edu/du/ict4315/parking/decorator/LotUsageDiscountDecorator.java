package edu.du.ict4315.parking.decorator;

import edu.du.ict4315.parking.Car;
import edu.du.ict4315.parking.Money;
import edu.du.ict4315.parking.ParkingLot;

public class LotUsageDiscountDecorator extends ParkingChargeCalculatorDecorator {

	public LotUsageDiscountDecorator(ParkingChargeCalculator charge) {
		super(charge);
	}

	@Override
	public Money getParkingCharge(Car car, ParkingLot lot) {
		Money money = new Money();
		money = charge.getParkingCharge(car, lot);
		if(!lot.isFrequentlyUsed()) {
			// Reducing the parking charge by 10% if the lot is not frequently used.
			money.setDollars(money.getDollars() * 0.9);
		}
		return money;
	}
}
