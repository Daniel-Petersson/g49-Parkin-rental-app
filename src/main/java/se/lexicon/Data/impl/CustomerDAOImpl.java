package se.lexicon.Data.impl;

import se.lexicon.Data.ICustomerDAO;
import se.lexicon.Data.sequencer.CustomerSequencer;
import se.lexicon.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CustomerDAOImpl implements ICustomerDAO {

    private List<Customer> storage = new ArrayList<>();

    @Override
    public Customer create(Customer customer) {
        if (customer == null) throw new IllegalArgumentException("Customer cannot be null");

        int id = CustomerSequencer.nextId();

        Optional<Customer> customerOptional = find(id);
        if(customerOptional.isPresent()) throw new IllegalArgumentException("Customer's Id is duplicate");

        // Set the ID just before storing the customer
        customer.setId(id);
        storage.add(customer);
        return customer;
    }

    @Override
    public Optional<Customer> find(int id) {
        for (Customer customer : storage) {
            if (customer.getId() == id) {
                return Optional.of(customer);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean remove(int id) {
        Optional<Customer> customerOptional = find(id);
        if (!customerOptional.isPresent()) {
            return false;
        }
        storage.remove(customerOptional.get());
        return true;
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(storage);
    }

}
