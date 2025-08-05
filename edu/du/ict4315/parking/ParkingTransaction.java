package edu.du.ict4315.parking;

import java.util.Calendar;

public class ParkingTransaction {

	private Calendar transactionDate;
	private ParkingPermit permit;
	private ParkingLot lot;
	private Money feeCharged;
	private String customerId;

	public Calendar getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Calendar transactionDate) {
		this.transactionDate = transactionDate;
	}

	public ParkingPermit getPermit() {
		return permit;
	}

	public void setPermit(ParkingPermit permit) {
		this.permit = permit;
	}

	public ParkingLot getLot() {
		return lot;
	}

	public void setLot(ParkingLot lot) {
		this.lot = lot;
	}

	public Money getFeeCharged() {
		return feeCharged;
	}

	public void setFeeCharged(Money feeCharged) {
		this.feeCharged = feeCharged;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public ParkingTransaction(Calendar transactionDate, ParkingPermit permit, ParkingLot lot, Money feeCharged,
			String customerId) {
		super();
		this.transactionDate = transactionDate;
		this.permit = permit;
		this.lot = lot;
		this.feeCharged = feeCharged;
		this.customerId = customerId;
	}

	public ParkingTransaction() {
		super();
	}

}
