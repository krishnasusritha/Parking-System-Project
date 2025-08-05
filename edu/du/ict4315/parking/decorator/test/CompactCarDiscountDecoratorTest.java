package edu.du.ict4315.parking.decorator.test;

import org.junit.Assert;
import org.junit.Test;

import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.ParkingLot;
import edu.du.ict4315.parking.decorator.CompactCarDiscountDecorator;
import edu.du.ict4315.parking.decorator.FlatRateCalculatorDecorator;
import edu.du.ict4315.parking.decorator.ParkingChargeCalculator;
import edu.du.ict4315.parking.test.ApplicationTest;

public class CompactCarDiscountDecoratorTest extends  ApplicationTest{
	
	@Test
	public void testParkingCharge() throws Exception {
		car = customer.register("CAR456", CarType.COMPACT);
		office.register(customer, car);
		ParkingLot lot = new ParkingLot("lot1", "address1", 50);
		Assert.assertEquals("ParkingLot [lotId=lot1, address=address1, capacity=50]", lot.toString());
		office.addLot(lot);
		
		lot.entry(car);
		
		ParkingChargeCalculator calculator = new CompactCarDiscountDecorator(new FlatRateCalculatorDecorator(new ParkingChargeCalculator()));
		
		Assert.assertEquals(Integer.valueOf(49), lot.getCapacity());
		// If the car is compact and is not parked overnight
		Assert.assertEquals(8, calculator.getParkingCharge(car, lot).getDollars(), 0.5);
		
		
		lot.setParkingOvernight(true);
		lot.entry(car);
		// If the car is compact and is parked overnight
		Assert.assertEquals(20, calculator.getParkingCharge(car, lot).getDollars(), 0.5);
		
		car = customer.register("CAR123", CarType.SUV);
		office.register(customer, car);
		
		lot.setParkingOvernight(false);
		lot.entry(car);
		// If the car is SUV and is not parked overnight
		Assert.assertEquals(10, calculator.getParkingCharge(car, lot).getDollars(), 0.5);
		
		
		lot.setParkingOvernight(true);
		lot.entry(car);
		// If the car is SUV and is parked overnight
		Assert.assertEquals(25, calculator.getParkingCharge(car, lot).getDollars(), 0.5);
		
		

	}

}
