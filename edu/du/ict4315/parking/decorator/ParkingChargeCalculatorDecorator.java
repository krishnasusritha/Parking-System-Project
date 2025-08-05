package edu.du.ict4315.parking.decorator;

import edu.du.ict4315.parking.Car;
import edu.du.ict4315.parking.Money;
import edu.du.ict4315.parking.ParkingLot;

public class ParkingChargeCalculatorDecorator extends ParkingChargeCalculator {
	
	protected ParkingChargeCalculator charge;

	public ParkingChargeCalculatorDecorator(ParkingChargeCalculator charge) {
		super();
		this.charge = charge;
	}

	@Override
	public Money getParkingCharge(Car car, ParkingLot lot) {
		// TODO Auto-generated method stub
		return charge.getParkingCharge(car, lot);
	}
	

}
