package edu.du.ict4315.parking.test;

import org.junit.Assert;
import org.junit.Test;

import edu.du.ict4315.parking.Address;


public class AddressTest {
	
	@Test
	public void testAddress() throws Exception {
		Address add = new Address("address1", "address2", "city", "state", "zipcode");
		Assert.assertEquals("Address [streetAddress1=address1, streetAddress2=address2, city=city, state=state, zipCode=zipcode]", add.getAddressInfo());
	}
	
	@Test
	public void testInvalidAddress() {
		try {
			//When Address1 is empty
			Address add = new Address("", "address2", "city", "state", "zipcode");
		} catch(Exception ex) {
			Assert.assertEquals("Please enter valid streetAddress1 ", ex.getMessage());
		}
		
		try {
			//When City is empty
			Address add = new Address("address1", "address2", "", "state", "zipcode");
		} catch(Exception ex) {
			Assert.assertEquals("Please enter valid city ", ex.getMessage());
		}
		
		try {
			//When State is empty
			Address add = new Address("address1", "address2", "city", "", "zipcode");
		} catch(Exception ex) {
			Assert.assertEquals("Please enter valid state ", ex.getMessage());
		}
		
		try {
			//When Zipcode is empty
			Address add = new Address("address1", "address2", "city", "state", "");
		} catch(Exception ex) {
			Assert.assertEquals("Please enter valid zipCode ", ex.getMessage());
		}
		
		try {
			//When Address1, City, State and ZipCode are empty
			Address add = new Address("", "address2", "", "", "");
		} catch(Exception ex) {
			Assert.assertEquals("Please enter valid streetAddress1 city state zipCode ", ex.getMessage());
		}
	}

}
