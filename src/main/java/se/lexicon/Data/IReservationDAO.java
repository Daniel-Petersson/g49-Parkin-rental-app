package se.lexicon.Data;

import se.lexicon.model.ParkingSpot;
import se.lexicon.model.Reservation;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * IReservationDAO is an interface for performing CRUD operations on Reservation objects.
 */
public interface IReservationDAO {

    /**
     * Creates a new reservation and adds it to the data store.
     * @param reservation The reservation to be added.
     * @return The added reservation.
     */
    Reservation create(Reservation reservation);

    /**
     * Finds a reservation in the data store by its id.
     * @param id The id of the reservation to find.
     * @return An Optional containing the found reservation, or an empty Optional if no reservation was found.
     */
    Optional<Reservation> find(String id);

    /**
     * Removes a reservation from the data store by its id.
     * @param id The id of the reservation to remove.
     * @return true if the reservation was successfully removed, false otherwise.
     */
    boolean remove(String id);

    /**
     * Finds a reservation by the customer's id.
     * @param customerId The id of the customer.
     * @return The found reservation.
     */
    Reservation findByCustomerId(int customerId);

    /**
     * Finds a reservation by the vehicle's license plate.
     * @param licensePlate The license plate of the vehicle.
     * @return The found reservation.
     */
    Reservation findByVehicleLicensePlate(String licensePlate);

    /**
     * Finds a reservation by the parking spot's number.
     * @param spotNumber The number of the parking spot.
     * @return The found reservation.
     */
    Reservation findByParkingSpotNumber(int spotNumber);

    /**
     * Finds all reservations in the data store.
     * @return A collection of all reservations.
     */
    Collection<Reservation> findAll();

    /**
     * Finds parking spots by their area code.
     * @param areaCode The area code of the parking spots.
     * @return A list of parking spots with the given area code.
     */
    List<ParkingSpot> findByAreaCode(int areaCode);
}