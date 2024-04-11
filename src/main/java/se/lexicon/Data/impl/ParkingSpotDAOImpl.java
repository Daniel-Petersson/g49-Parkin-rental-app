package se.lexicon.Data.impl;

import se.lexicon.Data.IParkingSpotDAO;
import se.lexicon.model.ParkingSpot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingSpotDAOImpl implements IParkingSpotDAO {


    List<ParkingSpot> parkingSpots = new ArrayList<>();
    //todo:impl Methods

    @Override
    public ParkingSpot create(ParkingSpot parkingSpot) {
        if (parkingSpot == null) throw new IllegalArgumentException("Parking spot cannot be null");
        Optional<ParkingSpot> parkingSpotOptional = find(parkingSpot.getSpotNumber());
        if (parkingSpotOptional.isPresent()) throw new IllegalArgumentException("Parking spot is not available");

        parkingSpots.add(parkingSpot);
        return parkingSpot;
    }

    @Override
    public Optional<ParkingSpot> find(int spotNumber) {
        for (ParkingSpot numberOfSpot : parkingSpots) {
            if (numberOfSpot.getSpotNumber() == spotNumber) {
                return Optional.of(numberOfSpot);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean remove(int spotNumber) {
        Optional<ParkingSpot> parkingSpotOptional = find(spotNumber);
        if (!parkingSpotOptional.isPresent()) {
            throw new IllegalArgumentException("Parking spot not found");
        }
        parkingSpots.remove(parkingSpotOptional.get());
        return true;
    }

    @Override
    public List<ParkingSpot> findAll() {
        return new ArrayList<>(parkingSpots);
    }

    @Override
    public List<ParkingSpot> findByAreaCode(int areaCode) {
        List<ParkingSpot> listAreaCode = new ArrayList<>();
        for (ParkingSpot area : parkingSpots) {
            if (area.getAreaCode() == areaCode) {
                listAreaCode.add(area);
            }
        }
        return listAreaCode;
    }

    @Override
    public void occupyParkingSPot(int spotNumber) {
        Optional<ParkingSpot> parkingSpotOptional = find(spotNumber);
        if (!parkingSpotOptional.isPresent()) {
            throw new IllegalArgumentException("Parking spot not found");
        }
        if (parkingSpotOptional.get().isOccupied()) {
            throw new IllegalArgumentException("Parking spot already occupied");
        }
        parkingSpotOptional.get().occupy();

    }

    @Override
    public void vacateParkingSPot(int spotNumber) {
        Optional<ParkingSpot> parkingSpotOptional = find(spotNumber);
        if (!parkingSpotOptional.isPresent()) {
            throw new IllegalArgumentException("Parking spot not found");
        }
        if (!parkingSpotOptional.get().isOccupied()) {
            throw new IllegalArgumentException("Parking spot already vacate");
        }
        parkingSpotOptional.get().vacate();
    }
}
