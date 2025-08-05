package edu.du.ict4315.parking.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import edu.du.ict4315.parking.Address;
import edu.du.ict4315.parking.Car;
import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.Customer;
import edu.du.ict4315.parking.ParkingModule;
import edu.du.ict4315.parking.ParkingOffice;
import edu.du.ict4315.parking.ParkingService;


public class CarTest extends ApplicationTest{
				
	@Test
	public void testCar() throws Exception {
		Car car = new Car("CAR566", CarType.COMPACT, customer.getCustomerId());
		office.register(customer, car);
		Assert.assertEquals("Car [permit=Permit 1, license=CAR566, type=COMPACT, customerId=Customer 1]", car.toString());
	}
	
	@Test
	public void testInvalid() {
		try {
			//invalid license
			Car car = new Car("", CarType.COMPACT, customer.getCustomerId());
		} catch(Exception ex) {
			Assert.assertEquals("Please enter valid license ", ex.getMessage());
		}
		try {
			//invalid carType
			Car car = new Car("CAR566", null, customer.getCustomerId());
		} catch(Exception ex) {
			Assert.assertEquals("Please enter valid CarType ", ex.getMessage());
		}
		try {
			//invalid customerid
			Car car = new Car("CAR566", CarType.COMPACT, "");
		} catch(Exception ex) {
			Assert.assertEquals("Please enter valid customerId ", ex.getMessage());
		}
		try {
			//invalid license, cartype and customerId
			Car car = new Car("", null, "");
		} catch(Exception ex) {
			Assert.assertEquals("Please enter valid customerId license CarType ", ex.getMessage());
		}
	}

}
