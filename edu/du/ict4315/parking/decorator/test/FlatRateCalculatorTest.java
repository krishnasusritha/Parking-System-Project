package edu.du.ict4315.parking.decorator.test;

import org.junit.Assert;
import org.junit.Test;

import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.ParkingLot;
import edu.du.ict4315.parking.decorator.CompactCarDiscountDecorator;
import edu.du.ict4315.parking.decorator.FlatRateCalculatorDecorator;
import edu.du.ict4315.parking.decorator.LotUsageDiscountDecorator;
import edu.du.ict4315.parking.decorator.ParkingChargeCalculator;
import edu.du.ict4315.parking.test.ApplicationTest;

public class FlatRateCalculatorTest extends ApplicationTest{
	
	@Test
	public void testParkingCharge() throws Exception {
		car = customer.register("CAR456", CarType.COMPACT);
		office.register(customer, car);
		ParkingLot lot = new ParkingLot("lot1", "address1", 50);
		Assert.assertEquals("ParkingLot [lotId=lot1, address=address1, capacity=50]", lot.toString());
		office.addLot(lot);
		
		lot.entry(car);
		
		ParkingChargeCalculator calculator = new CompactCarDiscountDecorator(new LotUsageDiscountDecorator(new FlatRateCalculatorDecorator(new ParkingChargeCalculator())));
		
		Assert.assertEquals(Integer.valueOf(49), lot.getCapacity());
		// If the lot is frequently used and the car is Compact
		Assert.assertEquals(8, calculator.getParkingCharge(car, lot).getDollars(), 0.5);
		
		
		lot.setParkingOvernight(true);
		lot.setFrequentlyUsed(false);
		lot.entry(car);
		// If the lot is not frequently used and the car is Compact
		Assert.assertEquals(18, calculator.getParkingCharge(car, lot).getDollars(), 0.5);
		
		car = customer.register("CAR123", CarType.SUV);
		office.register(customer, car);
		
		lot.setParkingOvernight(true);
		lot.setFrequentlyUsed(false);
		lot.entry(car);
		// If the lot is not frequently used and the car is SUV
		Assert.assertEquals(22.5, calculator.getParkingCharge(car, lot).getDollars(), 0.5);
		

		lot.setParkingOvernight(true);
		lot.setFrequentlyUsed(true);
		lot.entry(car);
		// If the lot is not frequently used and the car is SUV
		Assert.assertEquals(25, calculator.getParkingCharge(car, lot).getDollars(), 0.5);

	}

}
