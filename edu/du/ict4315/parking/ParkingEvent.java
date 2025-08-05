package edu.du.ict4315.parking;

public class ParkingEvent {

	private Car	car;
	
	private ParkingLot lot;
	
	private String eventAction;

	public ParkingEvent(Car car, ParkingLot lot, String eventAction) {
		super();
		this.car = car;
		this.lot = lot;
		this.eventAction = eventAction;
	}

	public Car getCar() {
		return car;
	}

	public ParkingLot getLot() {
		return lot;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public void setLot(ParkingLot lot) {
		this.lot = lot;
	}

	public String getEventAction() {
		return eventAction;
	}

	public void setEventAction(String event) {
		this.eventAction = event;
	}

}
