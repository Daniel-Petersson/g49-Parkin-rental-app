package se.lexicon.Data;

import se.lexicon.model.ParkingSpot;
import se.lexicon.model.Vehicle;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * IParkingSpotDAO is an interface for performing CRUD operations on ParkingSpot objects.
 */
public interface IParkingSpotDAO {

    /**
     * Creates a new parking spot and adds it to the data store.
     * @param parkingSpot The parking spot to be added.
     * @return The added parking spot.
     */
    ParkingSpot create(ParkingSpot parkingSpot);

    /**
     * Finds a parking spot in the data store by its spot number.
     * @param spotNumber The spot number of the parking spot to find.
     * @return An Optional containing the found parking spot, or an empty Optional if no parking spot was found.
     */
    Optional<ParkingSpot> find(String spotNumber);

    /**
     * Removes a parking spot from the data store by its spot number.
     * @param spotNumber The spot number of the parking spot to remove.
     * @return true if the parking spot was successfully removed, false otherwise.
     */
    boolean remove(int spotNumber);

    /**
     * Finds all parking spots in the data store.
     * @return A List of all parking spots.
     */
    List<ParkingSpot> findAll();

    /**
     * Finds parking spots by their area code.
     * @param areaCode The area code of the parking spots.
     * @return A list of parking spots with the given area code.
     */
    List<ParkingSpot> findByAreaCode(int areaCode);

    /**
     * Occupies a parking spot by its spot number.
     * @param spotNumber The spot number of the parking spot to occupy.
     */
    void occupyParkingSPot(int spotNumber);

    /**
     * Vacates a parking spot by its spot number.
     * @param spotNumber The spot number of the parking spot to vacate.
     */
    void vacateParkingSPot(int spotNumber);
}