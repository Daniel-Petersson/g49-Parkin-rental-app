package se.lexicon.daoImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.Data.impl.ParkingSpotDAOImpl;
import se.lexicon.model.ParkingSpot;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingSpotDAOImplTest {

    private ParkingSpotDAOImpl testObject;
    @BeforeEach
    public void setUp(){
        testObject=new ParkingSpotDAOImpl();
    }

    @AfterEach
    public void tearDown(){
        // Clean up any changes made during the tests
    }

    @Test
    public void testCreateParkingSpotSuccessfully(){
        // Arrange
        // Create a new Parking spot object with the spot number 1 and areacode 36433".
        // This is the object we will be using to test the create method.
        ParkingSpot parkingSpot = new ParkingSpot(1,36433);

        // Act
        // Call the create method on the testObject (which is an instance of CustomerDAOImpl) with the customer object we just created.
        // The create method should add the customer to the storage and return the same customer object with an assigned ID.
        // We store the returned customer in the actualValue variable.
        ParkingSpot actualValue = testObject.create(parkingSpot);
        ParkingSpot expectedValue = parkingSpot;
        // Assert
        // Check if create method creates customer
        assertEquals(expectedValue,actualValue);
        // Check that the create method did not return null. If it did, the test will fail with the message "Created customer should not be null".
        assertNotNull(actualValue, "Created parking spot should not be null");

        // Check that the returned customer has an ID. If it doesn't, the test will fail with the message "Created customer should have an ID".
        //assertNotNull(parkingSpot.getSpotNumber(), "Created parking spot should have an ID");

        // Check that the returned customer is in the storage. If it's not, the test will fail with the message "Created customer should be in the storage".
        assertTrue(testObject.findAll().contains(actualValue), "Created customer should be in the storage");
    }

    @Test
    public void testCreateExistingParkingSpotThrowsException(){
        ParkingSpot parkingSpot = new ParkingSpot(1,36433);
        testObject.create(parkingSpot);

        // Assert that an IllegalArgumentException is thrown when trying to create a customer that already exists
        assertThrows(IllegalArgumentException.class, () -> testObject.create(parkingSpot));
    }

    @Test
    public void testFindBySpotNumberSuccessfully(){
        ParkingSpot parkingSpot = new ParkingSpot(1, 36433);
        testObject.create(parkingSpot);
        Optional<ParkingSpot> foundParkingSpot = testObject.find(1);
        // Assert that the parking spot can be found
        assertTrue(foundParkingSpot.isPresent());
        // Assert that the found parking spot is as expected
        assertEquals(parkingSpot, foundParkingSpot.get());
    }

    @Test
    public void testFindNonExistingCustomerReturnsEmpty(){
        Optional<ParkingSpot> notFoundParkingSpot = testObject.find(1);
        // Assert that the customer cannot be found
        assertFalse(notFoundParkingSpot.isPresent());
    }

    @Test
    public void testRemoveExistingParkingSpotSuccessfully(){
        ParkingSpot parkingSpot = new ParkingSpot(1,36433);
        testObject.create(parkingSpot);
        Optional<ParkingSpot> foundParkingSpot = testObject.find(1);
        // Assert that the customer can be removed
        assertTrue(testObject.remove(1));
        // Assert that the customer cannot be found after removal
        assertFalse(testObject.find(1).isPresent());
    }

    @Test
    public void testRemoveNonExistentParkingSpotReturnsFalse(){
        boolean removed = testObject.remove(12);
        assertFalse(removed);
    }

    @Test
    public void testFindAllParkingSPotsReturnsAllParkingSPots(){
        // Arrange
        ParkingSpot parkingSpot1 = new ParkingSpot(1,36544);
        ParkingSpot parkingSpot2 = new ParkingSpot(2,36544);
        testObject.create(parkingSpot1);
        testObject.create(parkingSpot2);
        // Act
        List<ParkingSpot> result = testObject.findAll();
        // Assert
        assertEquals(2,result.size());
        assertTrue(result.contains(parkingSpot1));
        assertTrue(result.contains(parkingSpot2));
    }

    @Test
    public void testFindAllParkingSpotsEmptyListThrowsError(){
        List<ParkingSpot> result = testObject.findAll();
        // Assert that the find all customers throw error when empty list is returned
        assertTrue(result.isEmpty());
    }


}
