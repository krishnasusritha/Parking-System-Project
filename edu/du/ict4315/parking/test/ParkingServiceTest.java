package edu.du.ict4315.parking.test;

import org.junit.Assert;
import org.junit.Test;

import edu.du.ict4315.parking.Address;
import edu.du.ict4315.parking.Car;
import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.Customer;

public class ParkingServiceTest extends ApplicationTest {
	
	@Test
	public void testPerformCommand() throws Exception {
		Address address = new Address("address1", "address2", "city", "state", "1234");
		Customer customer = new Customer("name", address, "1234567890");
		String[] customerProps = {"name=" + customer.getName(), "streetAddress1=" + customer.getAddress().getStreetAddress1(), "streetAddress2=" + customer.getAddress().getStreetAddress2(), "city=" + customer.getAddress().getCity(), "state=" + customer.getAddress().getState(), "zipCode=" + customer.getAddress().getZipCode(), "phone="+ customer.getPhoneNumber()};
		String customerId = service.performCommand("CUSTOMER", customerProps);
		customer.setCustomerId(customerId);
		Assert.assertEquals("Customer 2", customerId);
		
		Car car = new Car("CAR123", CarType.COMPACT, customer.getCustomerId());
		String[] carProps = {"license=" + car.getLicense(), "customerId=" + car.getCustomerId(), "carType=" + car.getType().toString()};
		String permitId = service.performCommand("CAR", carProps);
		Assert.assertEquals("Permit 1", permitId);
	}
}
