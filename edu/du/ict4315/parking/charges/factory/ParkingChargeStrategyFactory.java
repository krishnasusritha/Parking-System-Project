package edu.du.ict4315.parking.charges.factory;

import edu.du.ict4315.parking.charges.strategy.ParkingChargeStrategy;

public interface ParkingChargeStrategyFactory {

	public ParkingChargeStrategy getStrategy(String strategy);
}
