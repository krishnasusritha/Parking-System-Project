package edu.du.ict4315.parking.test;

import org.junit.Assert;
import org.junit.Test;

import edu.du.ict4315.parking.Money;


public class MoneyTest {
	
	@Test
	public void testMoney() {
		Money money = new Money();
		money.setCents(150);
		Assert.assertEquals(1.5, money.getDollars(), 0);
	}

}
