package edu.du.ict4315.parking.observer;

import edu.du.ict4315.parking.ParkingEvent;

public interface ParkingAction {
	
	void update(ParkingEvent event);
}
