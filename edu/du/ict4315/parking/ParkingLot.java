package edu.du.ict4315.parking;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import edu.du.ict4315.parking.observer.ParkingAction;

public class ParkingLot {

	protected String lotId;

	protected String address;

	protected Integer capacity;

	protected ParkingOffice office;

	private boolean isParkingOvernight = false;

	private boolean isFrequentlyUsed = true;

	private List<ParkingAction> observers = new ArrayList<>();

	public ParkingLot(String lotId, String address, Integer capacity) throws Exception {
		super();
		if (StringUtils.isAnyBlank(lotId, address) || capacity < 0) {
			String msg = "Please enter valid ";
			if (StringUtils.isBlank(lotId)) {
				msg += "lotId ";
			}
			if (StringUtils.isBlank(address)) {
				msg += "address ";
			}
			if (capacity < 0) {
				msg += "capacity ";
			}
			throw new Exception(msg);
		} else {
			this.lotId = lotId;
			this.address = address;
			this.capacity = capacity;
		}
	}

	public ParkingOffice getOffice() {
		return office;
	}

	public void setOffice(ParkingOffice office) {
		this.office = office;
	}

	public String getLotId() {
		return lotId;
	}

	public String getAddress() {
		return address;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public boolean isParkingOvernight() {
		return isParkingOvernight;
	}

	public void setParkingOvernight(boolean isParkingOvernight) {
		this.isParkingOvernight = isParkingOvernight;
	}

	/**
	 * This method handles the entry of a car into the parking lot, checking the
	 * permit expiration and updating the capacity accordingly.
	 * 
	 * @param car the car attempting to enter the parking lot
	 * @throws Exception if the car permit is expired
	 */
	public void entry(Car car) throws Exception {
		if (car.getPermitExpiration() == null || car.getPermitExpiration().compareTo(LocalDate.now()) > 0) {
			car.setLotId(this.lotId);
			car.setEntryTime(LocalDateTime.now());
			this.capacity -= 1;
			getDailyParkingRate(car);
		} else {
			throw new Exception("Permit is expired for the car with license: " + car.getLicense());
		}
	}
	
	/**
	 * This method notifies the observer when a car is entered into the parking lot, after checking the
	 * permit expiration and updating the capacity accordingly.
	 * 
	 * @param car the car attempting to enter the parking lot
	 * @throws Exception if the car permit is expired
	 */
	public void entryEvent(Car car) throws Exception {
		if (car.getPermitExpiration() == null || car.getPermitExpiration().compareTo(LocalDate.now()) > 0) {
			car.setLotId(this.lotId);
			car.setEntryTime(LocalDateTime.now());
			this.capacity -= 1;
			ParkingEvent event = new ParkingEvent(car, this, "entry");
			notifyObservers(event);
		} else {
			throw new Exception("Permit is expired for the car with license: " + car.getLicense());
		}
	}

	/**
	 * This method is used to calculate the daily parking charge for each car.
	 * 
	 * @param car
	 */
	public void getDailyParkingRate(Car car) {
		ParkingCharge charge = new ParkingCharge();
		charge.setCustomerId(car.getCustomerId());
		charge.setPermitId(car.getPermitId());
		charge.setIncurred(Instant.now());
		charge.setLotId(this.lotId);
		Money money = new Money();
		if (isParkingOvernight()) {
			// $25 is charged if its parked overnight
			money.setCents(2500);
		} else {
			// $10 is charged if its an entry only parking
			money.setCents(1000);
		}
		money.setDollars(ParkingCalculator.getParkingPricing(money.getDollars(), car, office, this));
		charge.setMoney(money);
		office.park(Calendar.getInstance(), car, this, money);
		office.addCharge(charge);
	}

	@Override
	public String toString() {
		return "ParkingLot [lotId=" + lotId + ", address=" + address + ", capacity=" + capacity + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(isParkingOvernight, lotId);
	}

	@Override
	public boolean equals(Object lot) {
		if (this == lot)
			return true;
		if (lot == null)
			return false;
		if (getClass() != lot.getClass())
			return false;
		ParkingLot other = (ParkingLot) lot;
		return isParkingOvernight == other.isParkingOvernight && Objects.equals(lotId, other.lotId);
	}

	public boolean isFrequentlyUsed() {
		return isFrequentlyUsed;
	}

	public void setFrequentlyUsed(boolean isFrequentlyUsed) {
		this.isFrequentlyUsed = isFrequentlyUsed;
	}

	public void registerObserver(ParkingAction observer) {
		observers.add(observer);
	}

	public void unregisterObserver(ParkingAction observer) {
		observers.remove(observer);
	}

	public void notifyObservers(ParkingEvent event) {
		for (ParkingAction observer : observers) {
			observer.update(event);
		}
	}

	public List<ParkingAction> getObservers() {
		return observers;
	}
	
	

}
