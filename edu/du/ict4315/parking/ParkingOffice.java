package edu.du.ict4315.parking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.google.inject.Inject;

public class ParkingOffice {

	private String name;

	private String address;

	private List<Customer> customers = new ArrayList<>();

	private List<Car> cars = new ArrayList<>();

	private List<ParkingLot> lots = new ArrayList<>();

	private List<ParkingCharge> charges = new ArrayList<>();

	private TransactionManager transactionManager;

	private ParkingService parkingService = new ParkingService(this);

	private PermitManager permitManager;

	@Inject
	public ParkingOffice(TransactionManager transactionManager, PermitManager permitManager) {
		super();
		this.transactionManager = transactionManager;
		if (this.permitManager == null) {
			this.permitManager = permitManager;
		}
	}

	public ParkingService getParkingService() {
		return parkingService;
	}

	public void setParkingService(ParkingService parkingService) {
		this.parkingService = parkingService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<ParkingLot> getLots() {
		return lots;
	}

	public void addLot(ParkingLot lot) {
		this.lots.add(lot);
		lot.setOffice(this);
	}

	public List<ParkingCharge> getCharges() {
		return charges;
	}

	public void setCharges(List<ParkingCharge> charges) {
		this.charges = charges;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void addCustomer(Customer customer) {
		this.customers.add(customer);
	}

	public List<Car> getCars() {
		return cars;
	}

	public void addCar(Car car) {
		this.cars.add(car);
	}

	/**
	 * This method is used to register the customer to the ParkingOffice
	 * 
	 * @param customer the Customer object
	 * @return
	 * @throws Exception exception is thrown if one or more fields are invalid
	 */
	public String register(Customer customer) {
		String[] customerProps = { "name=" + customer.getName(),
				"streetAddress1=" + customer.getAddress().getStreetAddress1(),
				"streetAddress2=" + customer.getAddress().getStreetAddress2(),
				"city=" + customer.getAddress().getCity(), "state=" + customer.getAddress().getState(),
				"zipCode=" + customer.getAddress().getZipCode(), "phone=" + customer.getPhoneNumber() };
		customer.setCustomerId(parkingService.performCommand("CUSTOMER", customerProps));
		customers.add(customer);
		return customer.getCustomerId();
	}

	/**
	 * This method is used to register the car to the ParkingOffice
	 * 
	 * @param customer the Customer object
	 * @param car      the Car object
	 * @return
	 * @throws Exception exception is thrown if one or more fields are invalid
	 */
	public String register(Customer customer, Car car) {
		String[] carProps = { "license=" + car.getLicense(), "customerId=" + car.getCustomerId(),
				"carType=" + car.getType().toString() };
		String permitId = parkingService.performCommand("CAR", carProps);
		car.setPermitId(permitId);
		ParkingPermit permit = permitManager.register(car);
		car.setPermit(permit);
		this.addCar(car);
		return car.getPermitId();
	}

	/**
	 * This method is used to register the customer to the ParkingOffice
	 * 
	 * @param name    the name of the customer
	 * @param address the address of the customer
	 * @param phone   the phone number of the customer
	 * @return
	 * @throws Exception exception is thrown if one or more fields are invalid
	 */
	public Customer register(String name, Address address, String phone) throws Exception {
		Customer customer = new Customer(name, address, phone);
		customer.setCustomerId("Customer " + (customers.size() + 1));
		customers.add(customer);
		return customer;
	}

	/**
	 * This method is used to register the Car
	 * 
	 * @param customer the customer the car is registered to
	 * @param license  the license number of the car
	 * @param type     the type of car: COMPACT or SUV
	 * @return
	 * @throws Exception exception is thrown if one or more fields are invalid
	 */
	public Car register(Customer customer, String license, CarType type) throws Exception {
		Car car = customer.register(license, type);
		String permitId = "Permit " + (cars.size() + 1);
		car.setPermitId(permitId);
		ParkingPermit permit = permitManager.register(car);
		car.setPermit(permit);
		this.addCar(car);
		return car;
	}

	/**
	 * This method returns the Customer object
	 * 
	 * @param name the name of the customer
	 * @return
	 */
	public Customer getCustomer(String name) {
		Optional<Customer> cust = customers.stream().filter(customer -> StringUtils.equals(customer.getName(), name))
				.findFirst();
		return cust.orElse(null);
	}

	/**
	 * This method is used to add charges
	 * 
	 * @param charge the ParkingCharge object
	 * @return
	 */
	public Money addCharge(ParkingCharge charge) {
		charges.add(charge);
		return charge.getMoney();
	}

	/**
	 * This method is used to generate monthly bill for each customer
	 * 
	 * @param customerId The customer Id
	 * @return
	 */
	public Money monthlyBill(String customerId) {
		List<ParkingCharge> totalCharges = charges.stream()
				.filter(charge -> StringUtils.equals(charge.getCustomerId(), customerId)).collect(Collectors.toList());
		long totalCents = 0;
		for (ParkingCharge charge : totalCharges) {
			totalCents += charge.getMoney().getCents();
		}
		Money money = new Money();
		money.setCents(totalCents);
		return money;

	}

	public ParkingTransaction park(Calendar date, Car car, ParkingLot lot, Money money) {
		return transactionManager.park(date, car, lot, money);
	}

	public Money getParkingCharges(ParkingPermit permit) {
		return transactionManager.getParkingCharges(permit);
	}

	public Money getParkingCharges(Customer customer) {
		List<Car> cars = customer.getCars();
		Money money = new Money();
		cars.stream().forEach(car -> money
				.setCents(money.getCents() + transactionManager.getParkingCharges(car.getPermit()).getCents()));
		return money;
	}

	/**
	 * This method returns the list of CustomerIds
	 * 
	 * @return
	 */
	public List<String> getCustomerIds() {
		return customers.stream().map(Customer::getCustomerId).collect(Collectors.toList());
	}

	/**
	 * This method returns the list of Permit ids.
	 * 
	 * @return
	 */
	public List<String> getPermitIds() {
		return customers.stream().flatMap(cust -> cust.getCars().stream().map(Car::getPermitId))
				.collect(Collectors.toList());
	}

	/**
	 * This method returns the list of Permit ids for a customer
	 * 
	 * @param customer the customer object
	 * @return
	 */
	public List<String> getPermitIds(Customer customer) {
		return customer.getCars().stream().map(Car::getPermitId).collect(Collectors.toList());
	}

	/**
	 * This method returns the list of important/special days in the university
	 * 
	 * @return List<LocalDate>
	 */
	public List<LocalDate> getSpecialDays() {
		List<LocalDate> dates = new ArrayList<>();
		dates.add(LocalDateTime.parse("2024-01-01T12:00:00").toLocalDate());
		dates.add(LocalDateTime.parse("2024-01-15T12:00:00").toLocalDate());
		dates.add(LocalDateTime.parse("2024-02-19T12:00:00").toLocalDate());
		return dates;
	}
}
