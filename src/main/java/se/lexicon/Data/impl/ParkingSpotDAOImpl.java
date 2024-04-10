package se.lexicon.Data.impl;

import se.lexicon.Data.IParkingSpotDAO;
import se.lexicon.model.ParkingSpot;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ParkingSpotDAOImpl implements IParkingSpotDAO {

    //todo:impl Methods

    @Override
    public ParkingSpot create(ParkingSpot parkingSpot) {
        return null;
    }

    @Override
    public Optional<ParkingSpot> find(String spotNumber) {
        return Optional.empty();
    }

    @Override
    public boolean remove(String spotNumber) {
        return false;
    }

    @Override
    public Collection<ParkingSpot> findAll() {
        return List.of();
    }

    @Override
    public List<ParkingSpot> findByAreaCode(int areaCode) {
        return List.of();
    }

    @Override
    public void occupyParkingSPot(int spotNumber) {

    }

    @Override
    public void vacateParkingSPot(int spotNumber) {

    }
}
