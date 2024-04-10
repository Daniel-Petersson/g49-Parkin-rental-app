package se.lexicon.Data;

import se.lexicon.model.ParkingSpot;
import se.lexicon.model.Vehicle;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IParkingSpotDAO {

    ParkingSpot create(ParkingSpot parkingSpot);

    Optional<ParkingSpot> find(String spotNumber);


    boolean remove(String spotNumber);

    Collection<ParkingSpot> findAll();

    List<ParkingSpot> findByAreaCode(int areaCode);

    void occupyParkingSPot(int spotNumber);
    void vacateParkingSPot(int spotNumber);

}
