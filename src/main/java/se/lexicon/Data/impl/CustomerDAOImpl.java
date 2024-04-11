package se.lexicon.Data.impl;

import se.lexicon.Data.ICustomerDAO;
import se.lexicon.Data.sequencer.CustomerSequencer;
import se.lexicon.model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CustomerDAOImpl implements ICustomerDAO {

    private List<Customer> storage = new ArrayList<>();

    @Override
    public Customer create(Customer customer) {
        if (customer == null) throw new IllegalArgumentException("Customer cannot be null");
        int id = CustomerSequencer.nextId();
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
    public Collection<Customer> findAll() {
        return new ArrayList<>(storage);
    }
}
