package se.lexicon;

import se.lexicon.model.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ParkingSpot parkingSpot1 = new ParkingSpot(1, 36433);
        ParkingSpot parkingSpot2 = new ParkingSpot(2, 36433);

        Vehicle car1 = new Vehicle("ABC321", VehicleType.CAR);
        Vehicle car2 = new Vehicle("ABC123", VehicleType.VAN);

        Customer customer1 = new Customer("Daniel", "0123456789");
        Customer customer2 = new Customer("Elin", "9876543210");

        Reservation reservation = new Reservation(customer1, parkingSpot1, 4, car1);

        System.out.println(reservation);


    }
}
