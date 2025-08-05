package edu.du.ict4315.parking.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.du.ict4315.parking.Address;
import edu.du.ict4315.parking.Car;
import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.Customer;
import edu.du.ict4315.parking.ParkingOffice;
import edu.du.ict4315.parking.ParkingService;

public class CustomerTest extends ApplicationTest{
	
	@Test
	public void testCustomer() throws Exception {
		Customer customer = new Customer("customerName", add, "1234567890");
		office.register(customer);
		// Check if customer details are assigned successfully
		Assert.assertEquals("Customer [customerId=Customer 2, name=customerName, address=Address [streetAddress1=address1, streetAddress2=address2, city=city, state=state, zipCode=zipcode], phoneNumber=1234567890]", customer.toString());

		Car car = customer.register("CAR012", CarType.COMPACT);
		office.register(customer, car);
		// Check if car object is created properly
		Assert.assertEquals("Car [permit=Permit 1, license=CAR012, type=COMPACT, customerId=Customer 2]", car.toString());
		// Check if Car is assigned to the customerId correctly
		Assert.assertEquals(customer.getCustomerId(), car.getCustomerId());
		// Check if the cars are assigned successfully
		Assert.assertEquals(1, customer.getCars().size());

		Car car1 = customer.register("CAR234", CarType.SUV);
		office.register(customer, car1);
		// Check if car object is created properly
		Assert.assertEquals("Car [permit=Permit 2, license=CAR234, type=SUV, customerId=Customer 2]", car1.toString());
		// Check if Car is assigned to the customerId correctly
		Assert.assertEquals(customer.getCustomerId(), car1.getCustomerId());
		// Check if the cars are assigned successfully
		Assert.assertEquals(2, customer.getCars().size());
	}
	
	@Test
	public void testInvalidCustomer() {
		try {
			//When invalid customerName is given
			Customer customer = new Customer("", add, "1234567890");
		} catch(Exception ex) {
			Assert.assertEquals("Please enter valid name ", ex.getMessage());
		}
		try {
			//When invalid phoneNumber is given
			Customer customer = new Customer("customerName", add, "");
		} catch(Exception ex) {
			Assert.assertEquals("Please enter valid phoneNumber ", ex.getMessage());
		}
		try {
			//When invalid address is given
			Customer customer = new Customer("customerName", null, "1234567890");
		} catch(Exception ex) {
			Assert.assertEquals("Please enter valid address ", ex.getMessage());
		}
		try {
			//When invalid customerId, customerName, address, phoneNumber are given
			Customer customer = new Customer("", null, "");
		} catch(Exception ex) {
			Assert.assertEquals("Please enter valid name phoneNumber address ", ex.getMessage());
		}
		
	}
	
	@Test
	public void testInvalidCarRegistration() {
		try {
		Customer customer = new Customer("customerName", add, "1234567890");
		office.register(customer);
		customer.register("", CarType.COMPACT);
		} catch(Exception ex) {
			Assert.assertEquals("Please enter valid license ", ex.getMessage());
		}
	}
}
