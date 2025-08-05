package edu.du.ict4315.parking.test;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import edu.du.ict4315.parking.Address;
import edu.du.ict4315.parking.Car;
import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.Customer;
import edu.du.ict4315.parking.Money;
import edu.du.ict4315.parking.ParkingLotEntryExit;
import edu.du.ict4315.parking.ParkingService;
import edu.du.ict4315.parking.TransactionManager;

public class TransactionManagerTest extends ApplicationTest{
	
	@Test
	public void testPark() throws Exception {
		ParkingLotEntryExit lot = new ParkingLotEntryExit("lot1", "address1", 50);
		Assert.assertNotNull(lot);
		// Create an address object
		Address address = new Address("address1", "address2", "city", "state", "zipcode");
		Assert.assertNotNull(address);
		office.setName("Office1");
		office.setAddress("Address 1");
		ParkingService service = new ParkingService(office);

		Customer cust = office.register("customer 1", address, "123456789");
		Assert.assertNotNull(cust);

		Car car = office.register(cust, "CAR123", CarType.COMPACT);
		Assert.assertNotNull(car);
		
		Car car1 = office.register(cust, "CAR345", CarType.SUV);
		Assert.assertNotNull(car1);
		
		office.addLot(lot);
		office.register(cust, car);
		office.register(cust, car1);
		
		Money money = new Money();
		money.setCents(1000);
		
		TransactionManager manager = new TransactionManager();
		manager.park(Calendar.getInstance(), car, lot, money);
		
		manager.park(Calendar.getInstance(), car1, lot, money);
		
		// Parking Charges based on customer. Since two cars have entered the lot it sums up to $20
		Assert.assertEquals(2000, manager.getParkingChargesForCustomer(cust.getCustomerId()).getCents());

		// Parking Charges based on the car. For car using parking permit
		Assert.assertEquals(1000, manager.getParkingCharges(car.getPermit()).getCents());
		
		// Parking Charges based on the car. For car1 using parking permit
		Assert.assertEquals(1000, manager.getParkingCharges(car1.getPermit()).getCents());
	}
}
