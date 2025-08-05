package edu.du.ict4315.parking;

import java.time.DayOfWeek;

import edu.du.ict4315.parking.charges.factory.ParkingChargeStrategyFactory;
import edu.du.ict4315.parking.charges.factory.ParkingChargeStrategyImpl;
import edu.du.ict4315.parking.charges.strategy.ParkingChargeStrategy;

/**
 * 
 * This class is used to implement the strategies and generate the pricing for
 * each car.
 *
 */
public class ParkingCalculator {

	public static double getParkingPricing(double amount, Car car, ParkingOffice office, ParkingLot lot) {
		ParkingChargeStrategy pricing;
		ParkingChargeStrategyFactory factory = new ParkingChargeStrategyImpl();
		double updatedAmount = amount;

		int entryHour = car.getEntryTime().getHour();
		int exitHour = car.getExitTime() != null ? car.getExitTime().getHour() : -1;

		// If the car is parked in special days
		if (office.getSpecialDays().stream().anyMatch(car.getEntryTime().toLocalDate()::equals)) {
			pricing = factory.getStrategy(ParkingChargeStrategyImpl.SPECIAL_DAY);
			updatedAmount = pricing.parkingCharge(updatedAmount);
		} else if (car.getEntryTime().getDayOfWeek() == DayOfWeek.SUNDAY
				|| car.getEntryTime().getDayOfWeek() == DayOfWeek.SATURDAY) {
			// If the car is parked in the weekend
			pricing = factory.getStrategy(ParkingChargeStrategyImpl.DAY_BASED);
			updatedAmount = pricing.parkingCharge(updatedAmount);
		}

		// If the car is parked in the off-peak hour. The peak hours are 8am-8pm
		if (entryHour < 8 || exitHour > 20) {
			int offPeak = 0;
			if (entryHour < 8 && exitHour < 8) {
				offPeak = exitHour - entryHour;
			} else if (entryHour < 8 && exitHour > 8 && exitHour < 20) {
				offPeak = 8 - entryHour;
			} else if (entryHour > 8 && entryHour < 20 && exitHour > 20) {
				offPeak = exitHour - 20;
			} else if (entryHour > 20 && exitHour > 20) {
				offPeak = exitHour - entryHour;
			}
			int totalHours = exitHour - entryHour;
			double pricePerHour = updatedAmount / totalHours;
			pricing = factory.getStrategy(ParkingChargeStrategyImpl.TIME_BASED);
			// Pricing for off-peak hour and the peak hour.
			updatedAmount = pricing.parkingCharge(pricePerHour * offPeak) + pricePerHour * (totalHours - offPeak);
		}

		// If the car is parked in a lot which is not frequently used
		if (!lot.isFrequentlyUsed()) {
			pricing = factory.getStrategy(ParkingChargeStrategyImpl.LOT_USAGE);
			updatedAmount = pricing.parkingCharge(updatedAmount);
		}

		// If the car type is compact
		if (car.getType() == CarType.COMPACT) {
			pricing = factory.getStrategy(ParkingChargeStrategyImpl.CAR_TYPE);
			updatedAmount = pricing.parkingCharge(updatedAmount);
		}

		return updatedAmount;
	}
}
