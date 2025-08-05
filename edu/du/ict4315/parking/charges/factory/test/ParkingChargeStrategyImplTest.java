package edu.du.ict4315.parking.charges.factory.test;

import org.junit.Assert;
import org.junit.Test;

import edu.du.ict4315.parking.charges.factory.ParkingChargeStrategyImpl;
import edu.du.ict4315.parking.charges.strategy.CarTypeBasedPricingStrategy;
import edu.du.ict4315.parking.charges.strategy.DayBasedPricingStrategy;
import edu.du.ict4315.parking.charges.strategy.LotUsageBasedPricingStrategy;
import edu.du.ict4315.parking.charges.strategy.ParkingChargeStrategy;
import edu.du.ict4315.parking.charges.strategy.SpecialDayBasedPricingStrategy;
import edu.du.ict4315.parking.charges.strategy.TimeBasedPricingStrategy;

public class ParkingChargeStrategyImplTest {

	@Test
	public void testStrategy() {
		ParkingChargeStrategy strategy= null;
		ParkingChargeStrategyImpl factoryImpl = new ParkingChargeStrategyImpl();
		
		strategy = factoryImpl.getStrategy(ParkingChargeStrategyImpl.CAR_TYPE);
		Assert.assertEquals(CarTypeBasedPricingStrategy.class , strategy.getClass());
		
		strategy = factoryImpl.getStrategy(ParkingChargeStrategyImpl.DAY_BASED);
		Assert.assertEquals(DayBasedPricingStrategy.class , strategy.getClass());
		
		strategy = factoryImpl.getStrategy(ParkingChargeStrategyImpl.LOT_USAGE);
		Assert.assertEquals(LotUsageBasedPricingStrategy.class , strategy.getClass());
		
		strategy = factoryImpl.getStrategy(ParkingChargeStrategyImpl.SPECIAL_DAY);
		Assert.assertEquals(SpecialDayBasedPricingStrategy.class , strategy.getClass());
		
		strategy = factoryImpl.getStrategy(ParkingChargeStrategyImpl.TIME_BASED);
		Assert.assertEquals(TimeBasedPricingStrategy.class , strategy.getClass());
	}
}
