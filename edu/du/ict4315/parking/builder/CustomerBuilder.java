package edu.du.ict4315.parking.builder;

import edu.du.ict4315.parking.Address;
import edu.du.ict4315.parking.Customer;

public class CustomerBuilder {

	private String name;

	private Address address;

	private String phoneNumber;


	public CustomerBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public CustomerBuilder setAddress(Address address) {
		this.address = address;
		return this;
	}

	public CustomerBuilder setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	public Customer build() throws Exception {
		return new Customer(name, address, phoneNumber);
	}
}
