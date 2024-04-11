package se.lexicon.Data;

import se.lexicon.model.Customer;

import java.util.Collection;
import java.util.Optional;

public interface ICustomerDAO {
    /**
     * Creates a new customer and adds it to the data store.
     *
     * @param customer The customer to be added.
     * @return The added customer.
     */
    Customer create(Customer customer);

    /**
     * Finds a customer in the data store by its id.
     *
     * @param id The id of the customer to find.
     * @return An Optional containing the found customer, or an empty Optional if no customer was found.
     */
    Optional<Customer> find(int id);

    /**
     * Removes a customer from the data store by its id.
     *
     * @param id The id of the customer to remove.
     * @return true if the customer was successfully removed, false otherwise.
     */
    boolean remove(int id);

    /**
     * Finds all customers in the data store.
     *
     * @return A collection of all customers.
     */
    Collection<Customer> findAll();
}
