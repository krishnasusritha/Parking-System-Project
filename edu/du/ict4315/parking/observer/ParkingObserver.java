package edu.du.ict4315.parking.observer;

import java.util.HashMap;
import java.util.Map;

import edu.du.ict4315.parking.ParkingEvent;

public class ParkingObserver implements ParkingAction{
	
	private static Map<String, ParkingEvent> eventMap = new HashMap<>();

	@Override
	public void update(ParkingEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	public static Map<String, ParkingEvent> getEventMap() {
		return eventMap;
	}
	
	public static void addEvent(ParkingEvent event) {
		eventMap.put(event.getCar().getLicense(), event);
	}
	
	public static void removeEvent(ParkingEvent event) {
		eventMap.remove(event.getCar().getLicense());
	}
	
	public static boolean isCarParked(ParkingEvent event) {
		return eventMap.containsKey(event.getCar().getLicense());
	}

}
