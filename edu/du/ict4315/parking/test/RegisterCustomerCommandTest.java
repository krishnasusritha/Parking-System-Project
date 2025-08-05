package edu.du.ict4315.parking.test;

import org.junit.Assert;
import org.junit.Test;

import edu.du.ict4315.parking.Properties;


public class RegisterCustomerCommandTest extends ApplicationTest{
		
	@Test
	public void testExecuteParams() {
		Properties props = new Properties();
		props.setName("Name");
		props.setStreetAddress1("Address1");
		props.setCity("city");
		props.setState("state");
		props.setZipCode("1234");
		props.setPhoneNumber("1234567890");
		
		Assert.assertTrue(customerCommand.checkParameters(props));
		String customerId = customerCommand.execute(props);
		Assert.assertEquals("Customer 2", customerId);
		
		//If any of the required prop is invalid, the customer Id is returned as null
		props.setName(null);
		Assert.assertFalse(customerCommand.checkParameters(props));
		customerId = customerCommand.execute(props);
		Assert.assertNull(customerId);	
	}
}
