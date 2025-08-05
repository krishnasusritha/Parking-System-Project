package edu.du.ict4315.parking;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionManager {
    private ArrayList<ParkingTransaction> transactions = new ArrayList<>();

    /**
     * This method will create a parking transaction and will add it to the transactions list.
     */
    public ParkingTransaction park(Calendar date, Car car, ParkingLot lot, Money money) {
    	ParkingTransaction transaction = new ParkingTransaction();
    	transaction.setPermit(car.getPermit());
    	transaction.setLot(lot);
    	transaction.setTransactionDate(date);
    	transaction.setFeeCharged(money);
    	transaction.setCustomerId(car.getCustomerId());
    	transactions.add(transaction);
    	return transaction;
    }

    public Money getParkingCharges(ParkingPermit permit) {
    	List<ParkingTransaction> permitTransactions = new ArrayList<>();
    	permitTransactions = transactions.stream().filter(transaction -> transaction.getPermit().equals(permit)).collect(Collectors.toList());
    	Money totalCharges = new Money();
    	permitTransactions.stream().forEach(transaction -> totalCharges.setCents(totalCharges.getCents() + transaction.getFeeCharged().getCents()));
		return totalCharges;
    }
    
    public Money getParkingChargesForCustomer(String customerId) {
    	List<ParkingTransaction> permitTransactions = new ArrayList<>();
    	permitTransactions = transactions.stream().filter(transaction -> transaction.getCustomerId().equals(customerId)).collect(Collectors.toList());
    	Money totalCharges = new Money();
    	permitTransactions.stream().forEach(transaction -> totalCharges.setCents(totalCharges.getCents() + transaction.getFeeCharged().getCents()));
		return totalCharges;
    }

	public ArrayList<ParkingTransaction> getTransactions() {
		return transactions;
	}

	public void addTransaction(ParkingTransaction transaction) {
		this.transactions.add(transaction);
	}
    
    

}
