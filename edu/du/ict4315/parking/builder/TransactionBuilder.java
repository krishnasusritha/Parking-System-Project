package edu.du.ict4315.parking.builder;

import java.util.Calendar;

import edu.du.ict4315.parking.Money;
import edu.du.ict4315.parking.ParkingLot;
import edu.du.ict4315.parking.ParkingPermit;
import edu.du.ict4315.parking.ParkingTransaction;

public class TransactionBuilder {

	private Calendar transactionDate;
	private ParkingPermit permit;
	private ParkingLot lot;
	private Money feeCharged;
	private String customerId;


	public TransactionBuilder setTransactionDate(Calendar transactionDate) {
		this.transactionDate = transactionDate;
		return this;
	}

	public TransactionBuilder setPermit(ParkingPermit permit) {
		this.permit = permit;
		return this;
	}

	public TransactionBuilder setLot(ParkingLot lot) {
		this.lot = lot;
		return this;
	}

	public TransactionBuilder setFeeCharged(Money feeCharged) {
		this.feeCharged = feeCharged;
		return this;
	}

	public TransactionBuilder setCustomerId(String customerId) {
		this.customerId = customerId;
		return this;
	}
	
	public ParkingTransaction build() {
		return new ParkingTransaction(transactionDate, permit, lot, feeCharged, customerId);
	}

}
