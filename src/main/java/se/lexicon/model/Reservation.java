package se.lexicon.model;

import java.time.LocalDateTime;

public class Reservation {
    private String id;
    private Customer customer;
    private ParkingSpot parkingSpot;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Vehicle associatedVehicle;

    //Constructor
    public Reservation(Customer customer, ParkingSpot parkingSpot, int hours, Vehicle associatedVehicle) {
        this.customer = customer;
        this.parkingSpot = parkingSpot;
        this.startTime = LocalDateTime.now();
        setEndTime(hours);
        this.associatedVehicle = associatedVehicle;
    }


    //Getters
    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Vehicle getAssociatedVehicle() {
        return associatedVehicle;
    }

    //Setters


    public void setId(String id) {
        this.id = id;
    }

    public void setAssociatedVehicle(Vehicle associatedVehicle) {
        this.associatedVehicle = associatedVehicle;
    }

    public void setEndTime(int hours) {
        if (hours <= 0) throw new IllegalArgumentException("Hours cannot be negative or 0");
        if (hours > 48) throw new IllegalArgumentException("Maximum hours of parking is 48");
        this.endTime = startTime.plusHours(hours);
    }


    //Methods
    public String getDescription() {
        StringBuilder builder = new StringBuilder();
        builder.append("ReservationId: ").append(id)
                .append("Customer: ").append(customer.getName())
                .append("Parking Spot: ").append(parkingSpot.getSpotNumber())
                .append("Start time: ").append(startTime)
                .append("End time: ").append(endTime)
                .append("Associated Vehicle: ").append(associatedVehicle.getLicensePlate());
        return builder.toString();
    }

    public void reserve() {
        if (customer.getReservation() != null)
            throw new IllegalArgumentException("Customer has already a reserved parking spot.");
        if (parkingSpot == null) throw new IllegalArgumentException("Parking Spot should not be null.");
        if (parkingSpot.isOccupied()) throw new IllegalArgumentException("Parking spot is already occupied!");
        parkingSpot.occupy();
        customer.setReservation(this);
    }

    public void cancel() {
        if (!parkingSpot.isOccupied()) throw new IllegalArgumentException("Parking spot is already available");
        parkingSpot.vacate();
        customer.setReservation(null);
    }
}
