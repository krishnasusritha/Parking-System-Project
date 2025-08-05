package edu.du.ict4315.parking;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

public class ParkingLotEntryExit extends ParkingLot {

	public ParkingLotEntryExit(String lotId, String address, Integer capacity) throws Exception {
		super(lotId, address, capacity);
	}

	/**
	 * * This method handles the entry of a car into the parking lot, checking the
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
		} else {
			throw new Exception("Permit is expired for the car with license: " + car.getLicense());
		}
	}

	/**
	 * * This method notifies the observers when a car is exited from the parking lot after updating the
	 * capacity accordingly.
	 * 
	 * @param car the car attempting to exit the parking lot
	 */
	public void exitEvent(Car car) throws Exception {
		car.setLotId(null);
		car.setExitTime(LocalDateTime.now());
		this.capacity += 1;
		ParkingEvent event = new ParkingEvent(car, this, "exit");
		notifyObservers(event);
	}

	/**
	 * * This method handles the exit of a car into the parking lot and updating the
	 * capacity accordingly.
	 * 
	 * @param car the car attempting to exit the parking lot
	 */
	public void exit(Car car) {
		car.setLotId(null);
		car.setExitTime(LocalDateTime.now());
		getDailyParkingRate(car);
		this.capacity += 1;
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
		Duration duration = Duration.between(car.getEntryTime(), car.getExitTime());

		Money money = new Money();
		double hoursCalculated = duration.getSeconds() / 60;
		long hours = Math.round(hoursCalculated);
		// Charging $10/hour
		money.setCents(hours * 10);
		money.setDollars(ParkingCalculator.getParkingPricing(money.getDollars(), car, office, this));
		charge.setMoney(money);
		office.park(Calendar.getInstance(), car, this, money);
		office.addCharge(charge);
	}

}
