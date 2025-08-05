package edu.du.ict4315.parking;

import org.apache.commons.lang3.StringUtils;

import edu.du.ict4315.parking.Address.Builder;

public class Address {

	private String streetAddress1;

	private String streetAddress2;

	private String city;

	private String state;

	private String zipCode;

	public Address(String streetAddress1, String streetAddress2, String city, String state, String zipCode)
			throws Exception {
		super();
		if (StringUtils.isAnyBlank(streetAddress1, city, state, zipCode)) {
			String msg = "Please enter valid ";
			if (StringUtils.isBlank(streetAddress1)) {
				msg += "streetAddress1 ";
			}
			if (StringUtils.isBlank(city)) {
				msg += "city ";
			}
			if (StringUtils.isBlank(state)) {
				msg += "state ";
			}
			if (StringUtils.isBlank(zipCode)) {
				msg += "zipCode ";
			}
			throw new Exception(msg);
		} else {
			this.streetAddress1 = streetAddress1;
			this.streetAddress2 = streetAddress2;
			this.city = city;
			this.state = state;
			this.zipCode = zipCode;
		}
	}

	public Address() {
		// TODO Auto-generated constructor stub
	}

	public String getStreetAddress1() {
		return streetAddress1;
	}

	public String getStreetAddress2() {
		return streetAddress2;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getAddressInfo() {
		return "Address [streetAddress1=" + streetAddress1 + ", streetAddress2=" + streetAddress2 + ", city=" + city
				+ ", state=" + state + ", zipCode=" + zipCode + "]";
	}

	public static class Builder {
		private String streetAddress1;
		private String streetAddress2;
		private String city;
		private String state;
		private String zip;

		public Builder() {
		}

		public Builder withStreetAddress1(String street1) {
			this.streetAddress1 = street1;

			return this;
		}

		public Builder withStreetAddress2(String street) {
			this.streetAddress2 = street;

			return this;
		}

		public Builder withCity(String city) {
			this.city = city;

			return this;
		}

		public Builder withState(String state) {
			this.state = state;

			return this;
		}

		public Builder withZip(String zip) {
			this.zip = zip;

			return this;
		}

		public Address build() {
			Address address = new Address();
			address.streetAddress1 = this.streetAddress1;
			address.streetAddress2 = this.streetAddress2;
			address.city = this.city;
			address.state = this.state;
			address.zipCode = this.zip;

			return address;

		}

	}
}
