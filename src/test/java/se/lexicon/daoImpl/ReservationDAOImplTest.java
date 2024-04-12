package se.lexicon.daoImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.Data.impl.ReservationDAOImpl;
import se.lexicon.model.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationDAOImplTest {
    ReservationDAOImpl testObject;
    private Customer customer;
    private ParkingSpot parkingSpot;
    private Vehicle vehicle;

    @BeforeEach
    public void setUp(){
        testObject=new ReservationDAOImpl();
        customer = new Customer("John Doe", "123456789");
        parkingSpot = new ParkingSpot(1, 204060);
        vehicle = new Vehicle("ABC123", VehicleType.CAR);
    }

    @Test
    public void testCreateReservationSuccessfully() {
        // Arrange
        Reservation reservation = new Reservation(customer, parkingSpot, 4, vehicle);

        // Act
        Reservation createdReservation = testObject.create(reservation);

        // Assert
       // assertNotNull(createdReservation);
        assertEquals(reservation, createdReservation);
        assertNotNull(createdReservation.getId());
    }


    @Test
    public void testCreateExistingReservationThrowsException(){
        Reservation reservation = new Reservation(customer,parkingSpot,4,vehicle);
        testObject.create(reservation);
        // Assert that an IllegalArgumentException is thrown when trying to create a customer that already exists
        assertThrows(IllegalArgumentException.class, () -> testObject.create(reservation));
    }
    @Test
    public void testCreateReservationWithNullThrowsException() {
        // Assert
        assertThrows(IllegalArgumentException.class, () -> testObject.create(null));
    }

    @Test
    public void testCreateReservationWithOccupiedParkingSpotThrowsException() {
        // Arrange
        parkingSpot.occupy();
        Reservation reservation = new Reservation(customer, parkingSpot, 4, vehicle);

        // Assert
        assertThrows(IllegalArgumentException.class, () -> testObject.create(reservation));
    }


    @Test
    public void testFindByIdSuccessfully(){
        Reservation reservation = new Reservation(customer,parkingSpot,4,vehicle);
        testObject.create(reservation);
        Optional<Reservation> foundReservation = testObject.find(reservation.getId());
        // Assert that the customer can be found
        assertTrue(foundReservation.isPresent());
        // Assert that the found customer is as expected
        assertEquals(reservation, foundReservation.get());
    }
    @Test
    public void testFindNonExistentReservationReturnsEmpty() {
        Optional<Reservation> notFoundReservation = testObject.find("1356");
        // Assert that the customer cannot be found
        assertFalse(notFoundReservation.isPresent());
    }

    @Test
    public void testRemoveCustomerSuccessfully(){
        Reservation reservation = new Reservation(customer,parkingSpot,4,vehicle);
        testObject.create(reservation);
        Optional<Reservation> foundReservation = testObject.find(reservation.getId());
        assertTrue(testObject.remove(reservation.getId()));
        assertFalse(testObject.find(reservation.getId()).isPresent());

    }

    @Test
    public void testRemoveNonExistentReservationReturnsFalse() {
        // Assert that the remove operation returns false when trying to remove a customer that does not exist
        boolean removed = testObject.remove("212");
        assertFalse(removed);
    }
}

