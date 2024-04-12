package se.lexicon.Data.impl;

import se.lexicon.Data.IReservationDAO;
import se.lexicon.Data.sequencer.ReservationSequencer;
import se.lexicon.model.Customer;
import se.lexicon.model.Reservation;
import se.lexicon.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationDAOImpl implements IReservationDAO {

    List<Reservation> reservations = new ArrayList<>();

    @Override
    public Reservation create(Reservation reservation) {
        if (reservation == null) throw new IllegalArgumentException("Reservation cannot be null");
        if (reservation.getParkingSpot().isOccupied()) throw new IllegalArgumentException("Parking spot is already occupied!");
        Optional<Reservation> reservationOptional = find(reservation.getId());
        if(reservationOptional.isPresent()) throw new IllegalArgumentException("Reservation already exist");
        reservation.reserve();
        String id = ReservationSequencer.nextId();
        reservation.setId(id);
        reservations.add(reservation);
        return reservation;
    }

    @Override
    public Optional<Reservation> find(String id) {
        for (Reservation itemId : reservations) {
            if (itemId.getId().equalsIgnoreCase(id)) {
                return Optional.of(itemId);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean remove(String id) {
        Optional<Reservation> reservationOptional = find(id);
        if (!reservationOptional.isPresent()) {
            return false;
        }
        reservations.remove(reservationOptional.get());
        return true;
    }

    @Override
    public List<Reservation> findByCustomerId(int customerId) {
        List<Reservation> customerReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().getId() == customerId) {
                customerReservations.add(reservation);
            }

        }
        return customerReservations;
    }

    @Override
    public Reservation findByVehicleLicensePlate(String licensePlate) {
        Optional<Reservation> optionalReservation = find(licensePlate);
        if (!optionalReservation.isPresent()) {
            throw new IllegalArgumentException("License plate not found");
        }
        return optionalReservation.get();

    }

    @Override
    public Reservation findByParkingSpotNumber(int spotNumber) {
        for (Reservation reservation : reservations) {
            if (reservation.getParkingSpot().getSpotNumber() == spotNumber) ;
            return reservation;
        }
        throw new IllegalArgumentException("Spot number not found");
    }
}
