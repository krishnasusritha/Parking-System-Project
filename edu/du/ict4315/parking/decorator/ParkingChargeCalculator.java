package edu.du.ict4315.parking.decorator;

import edu.du.ict4315.parking.Car;
import edu.du.ict4315.parking.Money;
import edu.du.ict4315.parking.ParkingLot;

public class ParkingChargeCalculator implements ParkingCharge{

	public Money getParkingCharge(Car car, ParkingLot lot) {
		return new Money();
	}
	
}
