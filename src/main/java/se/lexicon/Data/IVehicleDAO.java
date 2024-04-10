package se.lexicon.Data;

import se.lexicon.model.Vehicle;

import java.util.Collection;
import java.util.Optional;

/**
 * IVehicleDAO is an interface for performing CRUD operations on Vehicle objects.
 */
public interface IVehicleDAO {

    /**
     * Creates a new vehicle and adds it to the data store.
     * @param vehicle The vehicle to be added.
     * @return The added vehicle.
     */
    Vehicle create(Vehicle vehicle);

    /**
     * Finds a vehicle in the data store by its license plate.
     * @param licensePlate The license plate of the vehicle to find.
     * @return An Optional containing the found vehicle, or an empty Optional if no vehicle was found.
     */
    Optional<Vehicle> find(String licensePlate);

    /**
     * Removes a vehicle from the data store by its license plate.
     * @param licensePlate The license plate of the vehicle to remove.
     * @return true if the vehicle was successfully removed, false otherwise.
     */
    boolean remove(String licensePlate);

    /**
     * Finds all vehicles in the data store.
     * @return A collection of all vehicles.
     */
    Collection<Vehicle> findAll();

    void upDate(Vehicle vehicle);
}