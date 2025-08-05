package edu.du.ict4315.parking;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

public class Customer {

	private String customerId;

	private String name;

	private Address address;

	private String phoneNumber;

	private List<Car> cars = new ArrayList<>();

	public Customer(String name, Address address, String phoneNumber) throws Exception {
		super();
		if (StringUtils.isAnyBlank(name, phoneNumber) || address == null) {
			String msg = "Please enter valid ";
			if (StringUtils.isBlank(name)) {
				msg += "name ";
			}
			if (StringUtils.isBlank(phoneNumber)) {
				msg += "phoneNumber ";
			}
			if (address == null) {
				msg += "address ";
			}
			throw new Exception(msg);
		} else {
			this.name = name;
			this.address = address;
			this.phoneNumber = phoneNumber;
		}
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * This method is used to register a car to the customer
	 * @param license the license number of the car
	 * @param type    the type of car
	 * @return Car
	 * @throws Exception if the license number is invalid
	 */
	public Car register(String license, CarType type) throws Exception {
		Car car = null;
		// If type is given as null, the car is taken as compact size by default
		type = type == null ? CarType.COMPACT : type;
		car = new Car(license, type, this.customerId);
		cars.add(car);
		return car;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", address=" + address.getAddressInfo()
				+ ", phoneNumber=" + phoneNumber + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId, name, phoneNumber);
	}

	@Override
	public boolean equals(Object customer) {
		if (this == customer)
			return true;
		if (customer == null)
			return false;
		if (getClass() != customer.getClass())
			return false;
		Customer other = (Customer) customer;
		return Objects.equals(customerId, other.customerId) && Objects.equals(name, other.name)
				&& Objects.equals(phoneNumber, other.phoneNumber);
	}

}
