package edu.du.ict4315.parking.test;

import java.time.Duration;

import org.junit.Assert;
import org.junit.Test;

import edu.du.ict4315.parking.Address;
import edu.du.ict4315.parking.Car;
import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.Customer;
import edu.du.ict4315.parking.Money;
import edu.du.ict4315.parking.ParkingLotEntryExit;
import edu.du.ict4315.parking.ParkingService;

public class ParkingOfficeTest extends ApplicationTest{

	@Test
	public void testParkingOffice() throws Exception {
		// Create an ParkingLot object
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

		office.addLot(lot);
		office.register(cust, car);
		lot.entry(car);
		Assert.assertEquals(lot.getLotId(), car.getLotId());
		Assert.assertEquals(Integer.valueOf(49), lot.getCapacity());

		lot.exit(car);
		Assert.assertEquals(null, car.getLotId());
		Assert.assertEquals(Integer.valueOf(50), lot.getCapacity());

		Money money = office.monthlyBill(cust.getCustomerId());
		Assert.assertEquals(Duration.between(car.getEntryTime(), car.getExitTime()).getSeconds(), money.getCents());

		Assert.assertNotNull(office.getCustomer("customer 1"));
		Assert.assertEquals(1, office.getCharges().size());

		// When invalid customer name is given
		Assert.assertNull(office.getCustomer("abc"));

		Assert.assertEquals(2, office.getCustomerIds().size());

		Assert.assertEquals(1, office.getPermitIds(cust).size());

		Assert.assertEquals(2, office.getPermitIds().size());

	}

	@Test
	public void testRegisterFunctions() throws Exception {
		// Create an ParkingLot object
		ParkingLotEntryExit lot = new ParkingLotEntryExit("lot1", "address1", 50);
		Assert.assertNotNull(lot);
		// Create an address object
		Address address = new Address("address1", "address2", "city", "state", "zipcode");
		Assert.assertNotNull(address);
		//Creating an object for Parking Office
		office.setName("Office1");
		office.setAddress("Address 1");
		//Creating an object for Parking Service
		ParkingService service = new ParkingService(office);
		
		//Creating a customer object
		Customer cust = new Customer("customer 1", address, "123456789");
		//Registering the Customer with Office
		Assert.assertEquals("Customer 2", office.register(cust));
		Assert.assertNotNull(cust);

		//Creating a Car object
		Car car = new Car("CAR566", CarType.COMPACT, cust.getCustomerId());
		//Registering the Car with the Ofiice
		office.register(cust, car);
		Assert.assertNotNull(car);
		Assert.assertEquals("Permit 1", car.getPermit().getId());
		
		//Adding ParkingLot to the Office
		office.addLot(lot);
		// The registered car has entered the Parking Lot
		lot.entry(car);
		Assert.assertEquals(lot.getLotId(), car.getLotId());
		Assert.assertEquals(Integer.valueOf(49), lot.getCapacity());

		// The registered Car has exited the Parking Lot
		lot.exit(car);
		Assert.assertEquals(null, car.getLotId());
		Assert.assertEquals(Integer.valueOf(50), lot.getCapacity());

		//Monthly Bill generation
		Money money = office.monthlyBill(cust.getCustomerId());
		Assert.assertEquals(Duration.between(car.getEntryTime(), car.getExitTime()).getSeconds(), money.getCents());

	}

}
