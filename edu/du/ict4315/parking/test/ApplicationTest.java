package edu.du.ict4315.parking.test;

import java.time.Duration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import edu.du.ict4315.parking.Address;
import edu.du.ict4315.parking.Car;
import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.Customer;
import edu.du.ict4315.parking.Money;
import edu.du.ict4315.parking.ParkingLot;
import edu.du.ict4315.parking.ParkingLotEntryExit;
import edu.du.ict4315.parking.ParkingModule;
import edu.du.ict4315.parking.ParkingOffice;
import edu.du.ict4315.parking.ParkingService;
import edu.du.ict4315.parking.RegisterCarCommand;
import edu.du.ict4315.parking.RegisterCustomerCommand;

public class ApplicationTest {
	
	protected Injector injector = Guice.createInjector(new ParkingModule());
	protected ParkingOffice office;
	
	protected Customer customer;	
	
	protected Address add;
	
	protected Car car;
	protected ParkingService service;
	
	protected RegisterCarCommand carCommand = new RegisterCarCommand();
	protected RegisterCustomerCommand customerCommand = new RegisterCustomerCommand();
	
	@Before
	public void setup() throws Exception {
		injector.injectMembers(this);
		office = injector.getInstance(ParkingOffice.class);
		add = new Address("address1", "address2", "city", "state", "zipcode");
		customer = new Customer("customerName", add, "1234567890");
		office.register(customer);
		
		car = customer.register("CAR456", CarType.SUV);
		
		service = new ParkingService(office);
		carCommand.createParkingOffice(office);
		customerCommand.createParkingOffice(office);
	}

	@Test
	public void testSuccess() throws Exception {
		ParkingOffice office = injector.getInstance(ParkingOffice.class);
		// Create an ParkingLot object
		ParkingLot lot = new ParkingLot("lot1", "address1", 50);
		Assert.assertNotNull(lot);
		
		office.addLot(lot);

		// Create an address object
		Address address = new Address("address1", "address2", "city", "state", "zipcode");
		Assert.assertNotNull(address);

		// Create a Customer object
		Customer customer = new Customer("customerName", address, "1234567890");
		customer.setCustomerId("Customer 1");
		Assert.assertNotNull(customer);

		// Register a car
		Car car = customer.register("CAR789", CarType.COMPACT);
		Assert.assertNotNull(car);
		office.register(customer, car);
		// Car entrying a park lot
		lot.entry(car);
		Assert.assertEquals(lot.getLotId(), car.getLotId());
		Assert.assertEquals(Integer.valueOf(49), lot.getCapacity());

	}

	@Test
	public void testWithParkingOffice() throws Exception {
		// Create an ParkingLot object
		ParkingLotEntryExit lot = new ParkingLotEntryExit("lot1", "address1", 50);
		Assert.assertNotNull(lot);

		ParkingLot lot2 = new ParkingLot("lot2", "address2", 50);
		Assert.assertNotNull(lot2);

		// Create an address object
		Address address = new Address("address1", "address2", "city", "state", "zipcode");
		Assert.assertNotNull(address);

		Customer cust = office.register("customer 1", address, "123456789");
		Assert.assertNotNull(cust);

		Car car = office.register(cust, "CAR123", CarType.COMPACT);
		Assert.assertNotNull(car);
		
		office.addLot(lot);
		office.addLot(lot2);
		office.register(cust, car);
		lot.entry(car);
		Assert.assertEquals(lot.getLotId(), car.getLotId());
		Assert.assertEquals(Integer.valueOf(49), lot.getCapacity());
		
		lot.exit(car);
		Assert.assertEquals(null, car.getLotId());
		Assert.assertEquals(Integer.valueOf(50), lot.getCapacity());
		
		Money money = office.monthlyBill(cust.getCustomerId());
		Assert.assertEquals(Duration.between(car.getEntryTime(), car.getExitTime()).getSeconds(), money.getCents());
	}
}
