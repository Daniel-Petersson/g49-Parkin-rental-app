package se.lexicon.model;

import se.lexicon.Data.sequencer.CustomerSequencer;

public class Customer {

    private int id;
    private final String name;
    private final String phoneNumber;
    private Reservation reservation;


    //create customer
    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Reservation getReservation() {
        return reservation;
    }

    //Setters
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public void setId(int id) {
        CustomerSequencer.nextId();
    }

    public String getDescription() {
        StringBuilder builder = new StringBuilder();
        builder.append("Customer: ").append(id)
                .append(", Name: ").append(name)
                .append(", PhoneNumber: ").append(phoneNumber);
        if (reservation != null) {
            builder.append(", Reservation: Yes");
        } else {
            builder.append(", Reservation: No");
        }
        ;
        return builder.toString();
    }
}
