package edu.du.ict4315.parking;

import java.util.Calendar;

/**
 * This class maintain all the Parking permit related information like its it,
 * vehicle, and expiration date details. Using this class we can create, modify,
 * and print parking permit information.
 *
 */
public class ParkingPermit {
	private String id;
	private Car car;
	private Calendar expirationDate;
	private Calendar registrationDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Calendar getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Calendar expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Calendar getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Calendar registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "ParkingPermit [id=" + id + ", car=" + car + ", expirationDate=" + expirationDate + ", registrationDate="
				+ registrationDate + "]";
	}
}
