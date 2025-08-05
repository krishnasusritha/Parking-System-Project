package edu.du.ict4315.parking.test;

import java.time.Instant;

import org.junit.Assert;
import org.junit.Test;

import edu.du.ict4315.parking.Address;
import edu.du.ict4315.parking.Car;
import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.Customer;
import edu.du.ict4315.parking.Money;
import edu.du.ict4315.parking.ParkingCharge;
import edu.du.ict4315.parking.ParkingLot;
import edu.du.ict4315.parking.ParkingService;

public class ParkingChargeTest extends ApplicationTest{
	

	@Test
	public void testCharge() throws Exception {
		ParkingService service = new ParkingService(office);
		ParkingLot lot = new ParkingLot("lot1", "address1", 50);
		Assert.assertNotNull(lot);

		// Create an address object
		Address address = new Address("address1", "address2", "city", "state", "zipcode");
		Assert.assertNotNull(address);

		Customer cust = new Customer("customerName", address, "1234567890");
		office.register(cust);
		Assert.assertNotNull(cust);

		// Register a car
		Car car = cust.register("CAR789", CarType.COMPACT);
		office.register(cust, car);
		Assert.assertNotNull(car);

		ParkingCharge charge = new ParkingCharge();
		charge.setCustomerId(cust.getCustomerId());
		Assert.assertEquals(cust.getCustomerId(), charge.getCustomerId());
		charge.setLotId(lot.getLotId());
		Assert.assertEquals(lot.getLotId(), charge.getLotId());
		charge.setPermitId(car.getPermitId());
		Assert.assertEquals(car.getPermit().getId(), charge.getPermitId());
		charge.setIncurred(Instant.now());
		Assert.assertNotNull(charge.getIncurred());

		Money money = new Money();
		money.setCents(150);
		charge.setMoney(money);
		Assert.assertEquals(money.getCents(), charge.getMoney().getCents());

	}

}
