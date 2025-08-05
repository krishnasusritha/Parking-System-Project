package edu.du.ict4315.parking.test;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import edu.du.ict4315.parking.ParkingCalculator;
import edu.du.ict4315.parking.ParkingLotEntryExit;

public class ParkingCalculatorTest extends ApplicationTest{


	@Test
	public void testParkingPricing() throws Exception {
		ParkingLotEntryExit lot = new ParkingLotEntryExit("lot1", "address1", 50);
		Assert.assertEquals("ParkingLot [lotId=lot1, address=address1, capacity=50]", lot.toString());
		
		office.addLot(lot);
		car.setLotId(lot.getLotId());
		car.setEntryTime(LocalDateTime.parse("2024-01-02T01:00:00"));
		car.setExitTime(LocalDateTime.parse("2024-01-02T05:00:00"));
		
		//Parking price will be decreased by 10% due to off-peak times
		Assert.assertEquals(18, ParkingCalculator.getParkingPricing(20, car, office, lot), 0);
		
		car.setEntryTime(LocalDateTime.parse("2024-01-01T08:00:00"));
		car.setExitTime(LocalDateTime.parse("2024-01-01T10:00:00"));
		
		//Parking price will be increased due to special days
		Assert.assertEquals(24, ParkingCalculator.getParkingPricing(20, car, office, lot), 0.5);
		
		car.setEntryTime(LocalDateTime.parse("2024-04-14T08:00:00"));
		car.setExitTime(LocalDateTime.parse("2024-04-14T10:00:00"));
		
		//Parking price will be decreased by 10% because it is parked on the weekend
		Assert.assertEquals(18, ParkingCalculator.getParkingPricing(20, car, office, lot), 0);
		
		lot.setFrequentlyUsed(false);
		car.setEntryTime(LocalDateTime.parse("2024-01-02T08:00:00"));
		car.setExitTime(LocalDateTime.parse("2024-01-02T10:00:00"));
		
		//Parking price will be decreased by 10% due to less frequently used lot
		Assert.assertEquals(18, ParkingCalculator.getParkingPricing(20, car, office, lot), 0);
		
		
	}
}
