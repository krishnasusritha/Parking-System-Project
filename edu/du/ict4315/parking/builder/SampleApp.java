package edu.du.ict4315.parking.builder;

import java.util.Calendar;

import edu.du.ict4315.parking.Address;
import edu.du.ict4315.parking.Customer;
import edu.du.ict4315.parking.ParkingTransaction;

public class SampleApp {

	public static void main(String[] args) {
		AddressBuilder addressBuilder = new AddressBuilder();
		CustomerBuilder customerBuilder = new CustomerBuilder();
		TransactionBuilder transactionBuilder = new TransactionBuilder();
		try {
			Address address = addressBuilder.setCity("city").setStreetAddress1("address1").setStreetAddress2("address2").setState("state").setZipCode("12345").build();
			Customer customer = customerBuilder.setName("Customer").setAddress(address).setPhoneNumber("748829991").build();
			ParkingTransaction transaction = transactionBuilder.setCustomerId(customer.getCustomerId()).setTransactionDate(Calendar.getInstance()).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
