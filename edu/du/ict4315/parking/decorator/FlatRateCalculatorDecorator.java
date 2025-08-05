package edu.du.ict4315.parking.decorator;

import java.time.Duration;

import edu.du.ict4315.parking.Car;
import edu.du.ict4315.parking.Money;
import edu.du.ict4315.parking.ParkingLot;
import edu.du.ict4315.parking.ParkingLotEntryExit;

public class FlatRateCalculatorDecorator extends ParkingChargeCalculatorDecorator {

	public FlatRateCalculatorDecorator(ParkingChargeCalculator charge) {
		super(charge);
	}
	
	@Override
	public Money getParkingCharge(Car car, ParkingLot lot) {
		Money money = charge.getParkingCharge(car, lot);
		if(lot instanceof ParkingLotEntryExit) {
			Duration duration = Duration.between(car.getEntryTime(), car.getExitTime());
			double hoursCalculated = duration.getSeconds() / 60;
			long hours = Math.round(hoursCalculated);
			// Charging $10/hour
			money.setCents(hours * 10);
		} else {
			if (lot.isParkingOvernight()) {
				// $25 is charged if its parked overnight
				money.setCents(2500);
			} else {
				// $10 is charged if its an entry only parking
				money.setCents(1000);
			}
		}
		return money;
	}

}
