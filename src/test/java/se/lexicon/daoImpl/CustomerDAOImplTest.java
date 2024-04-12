package se.lexicon.daoImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.Data.impl.CustomerDAOImpl;
import se.lexicon.model.Customer;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerDAOImplTest {

    private CustomerDAOImpl testObject;

    @BeforeEach
    public void setUp() {
        testObject = new CustomerDAOImpl();
    }

    @AfterEach
    public void tearDown() {
        // Clean up any changes made during the tests
    }

    @Test
    public void testCreateCustomerSuccessfully() {
        // Arrange
        // Create a new Customer object with the name "Test" and phone number "0123456789".
        // This is the object we will be using to test the create method.
        Customer customer = new Customer("Test", "0123456789");

        // Act
        // Call the create method on the testObject (which is an instance of CustomerDAOImpl) with the customer object we just created.
        // The create method should add the customer to the storage and return the same customer object with an assigned ID.
        // We store the returned customer in the actualValue variable.
        Customer actualValue = testObject.create(customer);
        Customer expectedValue = customer;


        // Assert
        // Check if create method creates customer
        assertEquals(expectedValue,actualValue);
        // Check that the create method did not return null. If it did, the test will fail with the message "Created customer should not be null".
        assertNotNull(actualValue, "Created customer should not be null");

        // Check that the returned customer has an ID. If it doesn't, the test will fail with the message "Created customer should have an ID".
        assertNotNull(actualValue.getId(), "Created customer should have an ID");

        // Check that the returned customer is in the storage. If it's not, the test will fail with the message "Created customer should be in the storage".
        assertTrue(testObject.findAll().contains(actualValue), "Created customer should be in the storage");
    }

    @Test
    public void testCreateExistingCustomerThrowsException() {
        Customer customer = new Customer("Test", "0123456789");
        testObject.create(customer);
        // Assert that an IllegalArgumentException is thrown when trying to create a customer that already exists
        assertThrows(IllegalArgumentException.class, () -> testObject.create(customer));
    }

    @Test
    public void testFindByIdSuccessfully() {
        Customer customer = new Customer("Test", "0123456789");
        testObject.create(customer);
        Optional<Customer> foundCustomer = testObject.find(customer.getId());
        // Assert that the customer can be found
        assertTrue(foundCustomer.isPresent());
        // Assert that the found customer is as expected
        assertEquals(customer, foundCustomer.get());
    }

    @Test
    public void testFindNonExistentCustomerReturnsEmpty() {
        Optional<Customer> notFoundCustomer = testObject.find(123);
        // Assert that the customer cannot be found
        assertFalse(notFoundCustomer.isPresent());
    }

    @Test
    public void testRemoveCustomerSuccessfully() {
        Customer customer = new Customer("Test", "0123456789");
        testObject.create(customer);
        Optional<Customer> foundCustomer = testObject.find(customer.getId());
        // Assert that the customer can be removed
        assertTrue(testObject.remove(customer.getId()));
        // Assert that the customer cannot be found after removal
        assertFalse(testObject.find(customer.getId()).isPresent());
    }

    @Test
    public void testRemoveNonExistentCustomerReturnsFalse() {
        // Assert that the remove operation returns false when trying to remove a customer that does not exist
        boolean removed = testObject.remove(212);
        assertFalse(removed);
    }

    @Test
    public void testFindAllCustomersReturnsAllCustomers() {
        // Arrange
        Customer customer1 = new Customer("Test1", "0123456789");
        Customer customer2 = new Customer("Test2", "9876543210");
        testObject.create(customer1);
        testObject.create(customer2);

        // Act
        List<Customer> result = testObject.findAll();

        // Assert
        assertEquals(2, result.size(), "findAll should return all customers in storage");
        assertTrue(result.contains(customer1), "findAll should include customer1");
        assertTrue(result.contains(customer2), "findAll should include customer2");

    }

    @Test
    public void testFindAllCustomersEmptyListThrowsError() {
        List<Customer> result = testObject.findAll();
        // Assert that the find all customers throw error when empty list is returned
        assertTrue(result.isEmpty());
    }
}