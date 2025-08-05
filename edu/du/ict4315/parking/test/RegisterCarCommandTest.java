package edu.du.ict4315.parking.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.du.ict4315.parking.ParkingOffice;
import edu.du.ict4315.parking.Properties;
import edu.du.ict4315.parking.RegisterCarCommand;

public class RegisterCarCommandTest extends ApplicationTest{
	
	@Test
	public void testExecuteParams() {
		Properties props = new Properties();
		props.setCustomerId("CustomerId");
		props.setLicense("ABC123");
		props.setCarType("COMPACT");
		
		Assert.assertTrue(carCommand.checkParameters(props));
		String permitId = carCommand.execute(props);
		Assert.assertEquals("Permit 1", permitId);
		
		//If any of the required prop is invalid, the customer Id is returned as null
		props.setCustomerId(null);
		Assert.assertFalse(carCommand.checkParameters(props));
		permitId = carCommand.execute(props);
		Assert.assertNull(permitId);	
	}

}
