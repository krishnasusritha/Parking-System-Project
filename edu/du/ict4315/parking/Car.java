package edu.du.ict4315.parking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

public class Car {

	private String permitId;

	private String license;

	private CarType carType;

	private String customerId;

	private String lotId;

	private LocalDateTime entryTime;

	private LocalDateTime exitTime;

	private ParkingPermit permit;

	public LocalDateTime getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
	}

	public LocalDateTime getExitTime() {
		return exitTime;
	}

	public void setExitTime(LocalDateTime exitTime) {
		this.exitTime = exitTime;
	}

	public Car(String license, CarType type, String customerId) throws Exception {
		super();
		if (StringUtils.isAnyBlank(license, customerId) || type == null) {
			String msg = "Please enter valid ";
			if (StringUtils.isBlank(customerId)) {
				msg += "customerId ";
			}
			if (StringUtils.isBlank(license)) {
				msg += "license ";
			}
			if (type == null) {
				msg += "CarType ";
			}
			throw new Exception(msg);
		} else {
			this.license = license;
			this.carType = type;
			this.customerId = customerId;

		}
	}

	public String getPermitId() {
		return permitId;
	}

	public void setPermitId(String permitId) {
		this.permitId = permitId;
	}

	public String getLicense() {
		return license;
	}

	public LocalDate getPermitExpiration() {
		return LocalDateTime.ofInstant(this.getPermit().getExpirationDate().toInstant(),
				this.getPermit().getExpirationDate().getTimeZone().toZoneId()).toLocalDate();
	}

	public CarType getType() {
		return carType;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getLotId() {
		return lotId;
	}

	public void setLotId(String lotId) {
		this.lotId = lotId;
	}

	public ParkingPermit getPermit() {
		return permit;
	}

	public void setPermit(ParkingPermit permit) {
		this.permit = permit;
	}

	@Override
	public String toString() {
		return "Car [permit=" + this.getPermit().getId() + ", license=" + this.license + ", type=" + carType
				+ ", customerId=" + customerId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId, license, this.getPermit().getId(), carType);
	}

	@Override
	public boolean equals(Object car) {
		if (this == car)
			return true;
		if (car == null)
			return false;
		if (getClass() != car.getClass())
			return false;
		Car other = (Car) car;
		return Objects.equals(customerId, other.customerId) && Objects.equals(license, other.license)
				&& Objects.equals(this.getPermit().getId(), other.getPermit().getId()) && carType == other.carType;
	}

}
