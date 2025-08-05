package edu.du.ict4315.parking.decorator;

import edu.du.ict4315.parking.Car;
import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.Money;
import edu.du.ict4315.parking.ParkingLot;

public class CompactCarDiscountDecorator extends ParkingChargeCalculatorDecorator {

	public CompactCarDiscountDecorator(ParkingChargeCalculator charge) {
		super(charge);
	}
	
	@Override
	public Money getParkingCharge(Car car, ParkingLot lot) {
		Money money = new Money();
		money = charge.getParkingCharge(car, lot);
		if(car.getType() == CarType.COMPACT) {
			// Reducing the parking charge by 20% if the car is compact.
			money.setDollars(money.getDollars() * 0.8);
		}
		return money;
	}

}
