package edu.du.ict4315.parking.observer;

import edu.du.ict4315.parking.ParkingEvent;
import edu.du.ict4315.parking.ParkingLotEntryExit;

public class EntryParkingObserver extends ParkingObserver {

	@Override
	public void update(ParkingEvent event) {
		if (event.getEventAction() == "entry") {
			if (! (event.getLot() instanceof ParkingLotEntryExit)) {
				event.getLot().getDailyParkingRate(event.getCar());
			} else {
				ParkingObserver.addEvent(event);
			}
		}
	}




}
