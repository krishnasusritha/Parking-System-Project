package edu.du.ict4315.parking.observer.test;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import edu.du.ict4315.parking.ParkingLot;
import edu.du.ict4315.parking.ParkingLotEntryExit;
import edu.du.ict4315.parking.observer.EntryParkingObserver;
import edu.du.ict4315.parking.observer.ExitParkingObserver;
import edu.du.ict4315.parking.observer.ParkingObserver;
import edu.du.ict4315.parking.test.ApplicationTest;

public class ParkingLotObserverTest extends ApplicationTest{

	private ParkingObserver observer;

	@Test
	public void testParkingLot() throws Exception {

		office.register(customer, car);
		ParkingLot lot = new ParkingLot("lot1", "address1", 50);
		Assert.assertEquals("ParkingLot [lotId=lot1, address=address1, capacity=50]", lot.toString());
		office.addLot(lot);
		
		observer = new EntryParkingObserver();
		lot.registerObserver(observer);
		observer = new ExitParkingObserver();
		lot.registerObserver(observer);
		
		Assert.assertEquals(2, lot.getObservers().size());

		lot.entryEvent(car);
		
		Assert.assertEquals(Integer.valueOf(49), lot.getCapacity());
		Assert.assertEquals(lot.getLotId(), car.getLotId());

		Assert.assertEquals(1, office.getCharges().size());

	}
	
	@Test
	public void testParkingLotEntryExit() throws Exception {
		office.setName("Office1");
		office.setAddress("Address 1");
		office.register(customer, car);
		ParkingLotEntryExit lot = new ParkingLotEntryExit("lot1", "address1", 50);
		Assert.assertEquals("ParkingLot [lotId=lot1, address=address1, capacity=50]", lot.toString());
		
		office.addLot(lot);
		
		observer = new EntryParkingObserver();
		lot.registerObserver(observer);
		observer = new ExitParkingObserver();
		lot.registerObserver(observer);
		
		Assert.assertEquals(2, lot.getObservers().size());
		
		lot.entryEvent(car);
		Assert.assertEquals(Integer.valueOf(49), lot.getCapacity());
		Assert.assertEquals(lot.getLotId(), car.getLotId());
		
		car.setEntryTime(LocalDateTime.of(2023, 10, 15, 0, 0));
		
		lot.exitEvent(car);
		Assert.assertEquals(null, car.getLotId());
		Assert.assertNotNull(car.getExitTime());
		
		Assert.assertEquals(1, office.getCharges().size());
	}

}
