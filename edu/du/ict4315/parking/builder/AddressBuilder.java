package edu.du.ict4315.parking.builder;

import edu.du.ict4315.parking.Address;

public class AddressBuilder {
	
	private String streetAddress1;

	private String streetAddress2;

	private String city;

	private String state;

	private String zipCode;

	public AddressBuilder setStreetAddress1(String streetAddress1) {
		this.streetAddress1 = streetAddress1;
		return this;
	}

	public AddressBuilder setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
		return this;
	}

	public AddressBuilder setCity(String city) {
		this.city = city;
		return this;
	}

	public AddressBuilder setState(String state) {
		this.state = state;
		return this;
	}

	public AddressBuilder setZipCode(String zipCode) {
		this.zipCode = zipCode;
		return this;
	}
	
	public Address build() throws Exception {
		return new Address(streetAddress1, streetAddress2, city, state, zipCode);
	}
}
