package edu.du.ict4315.parking.decorator.test;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.ParkingLot;
import edu.du.ict4315.parking.ParkingLotEntryExit;
import edu.du.ict4315.parking.decorator.FlatRateCalculatorDecorator;
import edu.du.ict4315.parking.decorator.LotUsageDiscountDecorator;
import edu.du.ict4315.parking.decorator.ParkingChargeCalculator;
import edu.du.ict4315.parking.test.ApplicationTest;

public class LotUsageDiscountDecoratorTest extends ApplicationTest{
	
	@Test
	public void testParkingCharge() throws Exception {
		car = customer.register("CAR456", CarType.COMPACT);
		office.register(customer, car);
		ParkingLot lot = new ParkingLot("lot1", "address1", 50);
		Assert.assertEquals("ParkingLot [lotId=lot1, address=address1, capacity=50]", lot.toString());
		office.addLot(lot);
		
		lot.entry(car);
		
		ParkingChargeCalculator calculator = new LotUsageDiscountDecorator(new FlatRateCalculatorDecorator(new ParkingChargeCalculator()));
		
		Assert.assertEquals(Integer.valueOf(49), lot.getCapacity());
		// If the lot is frequently used and is not parked overnight
		Assert.assertEquals(10, calculator.getParkingCharge(car, lot).getDollars(), 0.5);
		
		
		lot.setParkingOvernight(true);
		lot.setFrequentlyUsed(false);
		lot.entry(car);
		// If the lot is not frequently used and is parked overnight
		Assert.assertEquals(22.5, calculator.getParkingCharge(car, lot).getDollars(), 0.5);
		
		lot.setParkingOvernight(false);
		
		lot.entry(car);
		// If the lot is not frequently used and is not parked overnight
		Assert.assertEquals(9, calculator.getParkingCharge(car, lot).getDollars(), 0.5);
		
		
		lot.setParkingOvernight(true);
		lot.setFrequentlyUsed(true);
		lot.entry(car);
		// If the lot is not frequently used and is parked overnight
		Assert.assertEquals(25, calculator.getParkingCharge(car, lot).getDollars(), 0.5);
		
		

	}
	
	@Test
	public void testParkingChargeForEntryExitLot() throws Exception {
		
		office.register(customer, car);
		ParkingLotEntryExit lot = new ParkingLotEntryExit("lot1", "address1", 50);
		Assert.assertEquals("ParkingLot [lotId=lot1, address=address1, capacity=50]", lot.toString());
		office.addLot(lot);
		
		lot.entry(car);
		
		ParkingChargeCalculator calculator = new LotUsageDiscountDecorator(new FlatRateCalculatorDecorator(new ParkingChargeCalculator()));
		
		Assert.assertEquals(Integer.valueOf(49), lot.getCapacity());
		
		car.setEntryTime(LocalDateTime.parse("2024-04-14T08:00:00"));
		car.setExitTime(LocalDateTime.parse("2024-04-14T10:00:00"));
		
		// If the lot is frequently used and is not parked overnight
		Assert.assertEquals(12, calculator.getParkingCharge(car, lot).getDollars(), 0.5);
		
		
		lot.setFrequentlyUsed(false);
		// If the lot is not frequently used and is parked overnight
		Assert.assertEquals(10.8, calculator.getParkingCharge(car, lot).getDollars(), 0.5);

	}

}
