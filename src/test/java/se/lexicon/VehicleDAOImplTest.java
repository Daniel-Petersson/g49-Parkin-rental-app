package se.lexicon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.Data.impl.VehicleDAOImpl;
import se.lexicon.model.Vehicle;
import se.lexicon.model.VehicleType;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleDAOImplTest {
    // Instance of VehicleDAOImpl to be used in the tests
    private VehicleDAOImpl vehicleDAO;

    // Set up method to initialize the vehicleDAO before each test
    @BeforeEach
    public void setUp() {
        vehicleDAO = new VehicleDAOImpl();
    }

    // Test case for creating a new vehicle
    @Test
    public void testCreate() {
        Vehicle vehicle = new Vehicle("ABC123", VehicleType.CAR);
        Vehicle actualValue = vehicleDAO.create(vehicle);
        Vehicle expectedValue = vehicle;

        // Assert that the created vehicle is as expected
        assertEquals(expectedValue, actualValue);
        // Assert that the vehicle can be found in the storage after creation
        assertTrue(vehicleDAO.find("ABC123").isPresent());
    }

    // Test case for attempting to create a vehicle that already exists
    @Test
    public void testCreateExistingVehicle() {
        Vehicle vehicle = new Vehicle("ABC123", VehicleType.CAR);
        vehicleDAO.create(vehicle);

        // Assert that an IllegalArgumentException is thrown when trying to create a vehicle that already exists
        assertThrows(IllegalArgumentException.class, () -> vehicleDAO.create(vehicle));
    }

    // Test case for finding an existing vehicle
    @Test
    public void testFindExistingVehicle() {
        Vehicle vehicle = new Vehicle("ABC123", VehicleType.CAR);
        vehicleDAO.create(vehicle);

        Optional<Vehicle> foundVehicle = vehicleDAO.find("ABC123");

        // Assert that the vehicle can be found
        assertTrue(foundVehicle.isPresent());
        // Assert that the found vehicle is as expected
        assertEquals(vehicle, foundVehicle.get());
    }

    // Test case for attempting to find a vehicle that does not exist
    @Test
    public void testFindNonExistingVehicle() {
        Optional<Vehicle> notFoundVehicle = vehicleDAO.find("ABC123");

        // Assert that the vehicle cannot be found
        assertFalse(notFoundVehicle.isPresent());
    }

    // Test case for removing an existing vehicle
    @Test
    public void testRemoveExistingVehicle() {
        Vehicle vehicle = new Vehicle("ABC123", VehicleType.CAR);
        vehicleDAO.create(vehicle);

        // Assert that the vehicle can be removed
        assertTrue(vehicleDAO.remove("ABC123"));
        // Assert that the vehicle cannot be found after removal
        assertFalse(vehicleDAO.find("ABC123").isPresent());
    }

    // Test case for attempting to remove a vehicle that does not exist
    @Test
    public void testRemoveNonExistingVehicle() {
        // Assert that the remove operation returns false when trying to remove a vehicle that does not exist
       boolean removed = vehicleDAO.remove("ABC123");
        assertFalse(removed);
    }

    // Test case update vechilve
    @Test
    public void testUpdateExistingVehicle(){
        //create new vehicle
        Vehicle vehicle = new Vehicle("ABC123", VehicleType.CAR);
        //add this to DAO
        vehicleDAO.create(vehicle);
        //create an update vehicle object with the same licensplate but different type
        Vehicle upDatedVehicle = new Vehicle("ABC123",VehicleType.EL);
        //Updateing vehicle in DAO
        vehicleDAO.upDate(upDatedVehicle);
        //find vehicle from DAO
        Optional<Vehicle> result = vehicleDAO.find("ABC123");

        //test that vehicle is found
        assertTrue(result.isPresent());

        //assert that the type of the found vehicle is updated.
        assertEquals(result.get().getType(),VehicleType.EL);
    }
}