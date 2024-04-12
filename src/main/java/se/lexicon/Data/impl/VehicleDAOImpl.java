package se.lexicon.Data.impl;

import se.lexicon.Data.IVehicleDAO;
import se.lexicon.model.Vehicle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class VehicleDAOImpl implements IVehicleDAO {

    private List<Vehicle> storage = new ArrayList<>();

    /**
     * Creates a new vehicle and adds it to the storage list.
     * If the vehicle data is null or the license plate already exists in the storage, an IllegalArgumentException is thrown.
     * The existence check is done using the find method, which returns an Optional. If the Optional is present, it means
     * the license plate already exists in the storage.
     * @param vehicle The vehicle to be added.
     * @return The added vehicle.
     */
    @Override
    public Vehicle create(Vehicle vehicle) {
        if (vehicle== null) throw new IllegalArgumentException("vehicle data is null");
        Optional<Vehicle> vehicleOptional = find(vehicle.getLicensePlate());
        if (vehicleOptional.isPresent()) throw new IllegalArgumentException("Vehicle already exist");
        storage.add(vehicle);
        return vehicle;
    }

    /**
     * Finds a vehicle in the storage list by its license plate.
     * If the vehicle is found, it is returned wrapped in an Optional.
     * If the vehicle is not found, an empty Optional is returned.
     * @param licensePlate The license plate of the vehicle to find.
     * @return An Optional containing the found vehicle, or an empty Optional if no vehicle was found.
     */
    @Override
    public Optional<Vehicle> find(String licensePlate) {
        for (Vehicle vehicle: storage){
            if (vehicle.getLicensePlate().equalsIgnoreCase(licensePlate)){
                return Optional.of(vehicle);
            }
        }
        return Optional.empty();
    }

    /**
     * Removes a vehicle from the storage list by its license plate.
     * If the vehicle is found, it is removed from the storage list and true is returned.
     * If the vehicle is not found, false is returned.
     * @param licensePlate The license plate of the vehicle to remove.
     * @return true if the vehicle was successfully removed, false otherwise.
     */
    @Override
    public boolean remove(String licensePlate) {
        Optional<Vehicle> vehicleOptional = find(licensePlate);
        if (!vehicleOptional.isPresent()){
            return false;
        }
        storage.remove(vehicleOptional.get());

        return true;
    }

    /**
     * Finds all vehicles in the storage list.
     * @return A collection of all vehicles in the storage list.
     */
    @Override
    public Collection<Vehicle> findAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public void upDate(Vehicle vehicle) {
        //Todo impl
        //check the method parameter
        if (vehicle==null) throw new IllegalArgumentException("Vehicle data is null");
        //find int by license plate
        Optional<Vehicle> existingVehicleOptional = find(vehicle.getLicensePlate());
        if (!existingVehicleOptional.isPresent()){
            throw new IllegalArgumentException("License plate not found");
        }
        //if vechile exist ---> find index
        Vehicle existingVehicle = existingVehicleOptional.get();
                int index = storage.indexOf(existingVehicle);
        //replace it at existing index
        storage.set(index,vehicle);


    }
}
