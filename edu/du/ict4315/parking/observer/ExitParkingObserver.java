package edu.du.ict4315.parking.observer;

import edu.du.ict4315.parking.ParkingEvent;

public class ExitParkingObserver extends ParkingObserver {

	@Override
	public void update(ParkingEvent event) {
		
		if (event.getEventAction() == "exit" && ParkingObserver.isCarParked(event)) {
			event.getLot().getDailyParkingRate(event.getCar());
			ParkingObserver.removeEvent(event);
		} else {
			System.out.println("Something went wrong");
		}

	}

}
