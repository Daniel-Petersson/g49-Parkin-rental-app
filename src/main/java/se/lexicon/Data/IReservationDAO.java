package se.lexicon.Data;


import se.lexicon.model.ParkingSpot;
import se.lexicon.model.Reservation;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IReservationDAO {
    //todo: impl abstract methods
    Reservation create(Reservation reservation);

    Optional<Reservation> find(String id);


    boolean remove(String id);

    Reservation findByCustomerId(int customerId);
    Reservation findByVehicleLicensePlate(String licensePlate);
    Reservation findByParkingSpotNumber(int spotNumber);

    Collection<Reservation> findAll();

    List<ParkingSpot> findByAreaCode(int areaCode);



}
