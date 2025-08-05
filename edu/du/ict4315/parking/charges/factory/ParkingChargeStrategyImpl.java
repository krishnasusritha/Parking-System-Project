package edu.du.ict4315.parking.charges.factory;

import edu.du.ict4315.parking.charges.strategy.CarTypeBasedPricingStrategy;
import edu.du.ict4315.parking.charges.strategy.DayBasedPricingStrategy;
import edu.du.ict4315.parking.charges.strategy.LotUsageBasedPricingStrategy;
import edu.du.ict4315.parking.charges.strategy.ParkingChargeStrategy;
import edu.du.ict4315.parking.charges.strategy.SpecialDayBasedPricingStrategy;
import edu.du.ict4315.parking.charges.strategy.TimeBasedPricingStrategy;

public class ParkingChargeStrategyImpl implements ParkingChargeStrategyFactory{
	
	public static String CAR_TYPE = "CarType";
	
	public static String DAY_BASED = "DayBased";
	
	public static String LOT_USAGE = "LotUsage";
	
	public static String SPECIAL_DAY = "SpecialDay";
	
	public static String TIME_BASED = "TimeBased";
	

	@Override
	public ParkingChargeStrategy getStrategy(String strategy) {
		//Return appropriate Strategy objects based on the string
		ParkingChargeStrategy instance = null;
		if (CAR_TYPE.equals(strategy)) {
			instance = new CarTypeBasedPricingStrategy();
		} else if (DAY_BASED.equals(strategy)) {
			instance = new DayBasedPricingStrategy();
		} else if (LOT_USAGE.equals(strategy)) {
			instance = new LotUsageBasedPricingStrategy();
		} else if (SPECIAL_DAY.equals(strategy)) {
			instance = new SpecialDayBasedPricingStrategy();
		} else if (TIME_BASED.equals(strategy)) {
			instance = new TimeBasedPricingStrategy();
		} else {
			instance = null;
		}
		return instance;
	}

}
