package edu.du.ict4315.parking;

public class Money {
	
	private long cents;
	
	public double getDollars() {
		return cents/100.0;
	}

	public void setCents(long cents) {
		this.cents = cents;
	}
	
	public long getCents() {
		return this.cents;
	}

	@Override
	public String toString() {
		return "Money [cents=" + cents + "]";
	}
	
	public void setDollars(double dollars) {
		this.cents = (long) (dollars * 100);
	}	
	
}
