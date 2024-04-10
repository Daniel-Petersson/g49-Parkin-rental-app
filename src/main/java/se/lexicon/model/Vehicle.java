package se.lexicon.model;

public class Vehicle {
    private String licensePlate;
    private final VehicleType type;

    public Vehicle(String licensePlate, VehicleType type) {
        setLicensePlate(licensePlate);//reuse setter for impl validation
        this.type = type;
    }

    public Vehicle(String licensePlate) {
        this(licensePlate, VehicleType.CAR); //Chaining constructor
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleType getType() {
        return type;
    }

    public void setLicensePlate(String licensePlate) {
        if (licensePlate == null || licensePlate.isEmpty()) {
            throw new IllegalArgumentException("License plate cannot be null");
        }
        this.licensePlate = licensePlate;
    }

    public String getDescription() {
        return "this is a " + type + " with license plate " + licensePlate;
    }
}
