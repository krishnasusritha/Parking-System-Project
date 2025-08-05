package edu.du.ict4315.parking;

import java.time.Instant;
import java.util.Objects;

public class ParkingCharge {
	
	private String permitId;
	
	private String customerId;
	
	private String lotId;
	
	private Instant incurred;
	
	private Money money;

	public String getPermitId() {
		return permitId;
	}

	public void setPermitId(String permitId) {
		this.permitId = permitId;
	}

	public String getLotId() {
		return lotId;
	}

	public void setLotId(String lotId) {
		this.lotId = lotId;
	}

	public Instant getIncurred() {
		return incurred;
	}

	public void setIncurred(Instant incurred) {
		this.incurred = incurred;
	}

	public Money getMoney() {
		return money;
	}

	public void setMoney(Money money) {
		this.money = money;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "ParkingCharge [permitId=" + permitId + ", lotId=" + lotId + ", incurred=" + incurred + ", money="
				+ money + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId, lotId, permitId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParkingCharge other = (ParkingCharge) obj;
		return Objects.equals(customerId, other.customerId) && Objects.equals(lotId, other.lotId)
				&& Objects.equals(permitId, other.permitId);
	}

}
