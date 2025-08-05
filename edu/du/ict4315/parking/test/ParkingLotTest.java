package edu.du.ict4315.parking.test;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;

import edu.du.ict4315.parking.ParkingCalculator;
import edu.du.ict4315.parking.ParkingLot;
import edu.du.ict4315.parking.ParkingLotEntryExit;
import edu.du.ict4315.parking.ParkingPermit;

public class ParkingLotTest extends ApplicationTest{
		
	@Test
	public void testParkingLot() throws Exception {
		
		office.register(customer, car);
		ParkingLot lot = new ParkingLot("lot1", "address1", 50);
		Assert.assertEquals("ParkingLot [lotId=lot1, address=address1, capacity=50]", lot.toString());
		
		office.addLot(lot);
		
		lot.entry(car);
		Assert.assertEquals(Integer.valueOf(49), lot.getCapacity());
		Assert.assertEquals(lot.getLotId(), car.getLotId());
		
		Assert.assertEquals(1, office.getCharges().size());
		
	}
	
	@Test
	public void testParkingLotEntryExit() throws Exception {
		office.register(customer, car);
		ParkingLotEntryExit lot = new ParkingLotEntryExit("lot1", "address1", 50);
		Assert.assertEquals("ParkingLot [lotId=lot1, address=address1, capacity=50]", lot.toString());
		
		office.addLot(lot);
		
		lot.entry(car);
		Assert.assertEquals(Integer.valueOf(49), lot.getCapacity());
		Assert.assertEquals(lot.getLotId(), car.getLotId());
		
		car.setEntryTime(LocalDateTime.of(2023, 10, 15, 0, 0));
		
		lot.exit(car);
		Assert.assertEquals(null, car.getLotId());
		Assert.assertNotNull(car.getExitTime());
	}
	
	@Test
	public void testInvalidParkingLot() {
		try {
			//Invalid lotId
			ParkingLot lot = new ParkingLot("", "address1", 50);
		} catch(Exception ex) {
			Assert.assertEquals("Please enter valid lotId ", ex.getMessage());
		}
		try {
			//Invalid address
			ParkingLot lot = new ParkingLot("lot1", "", 50);
		} catch(Exception ex) {
			Assert.assertEquals("Please enter valid address ", ex.getMessage());
		}
		try {
			//Invalid capacity
			ParkingLot lot = new ParkingLot("lot1", "address1", -50);
		} catch(Exception ex) {
			Assert.assertEquals("Please enter valid capacity ", ex.getMessage());
		}
	}
	
	@Test
	public void testParkingLotEntryInvalid() throws Exception {
		ParkingLot lot = new ParkingLot("lot1", "address1", 50);
		Assert.assertEquals("ParkingLot [lotId=lot1, address=address1, capacity=50]", lot.toString());
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.add(Calendar.YEAR, -2);
		car.setPermit(new ParkingPermit());
		car.getPermit().setExpirationDate(now);
		try {
			lot.entry(car);
		} catch(Exception ex) {
			Assert.assertEquals("Permit is expired for the car with license: CAR456", ex.getMessage());
		}
	}
	
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
