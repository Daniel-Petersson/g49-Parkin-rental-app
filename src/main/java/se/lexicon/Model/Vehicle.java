package se.lexicon.Model;

public class Vehicle {
    private String licensePlate;
    private final VehicleType type;

    public Vehicle(String licensePlate, VehicleType type) {
        setLicensePlate(licensePlate);//reuse setter for impl validation
        this.type = type;
    }

    public Vehicle(String licensePlate) {
        this(licensPlate, VehicleType.CAR); //Chaining constructor
    }

    public String getLicensePlate() {
        return licensPlate;
    }

    public VehicleType getType() {
        return type;
    }

    public void setLicensePlate(String licensePlate) {
        if (licensPlate == null || licensPlate.isEmpty()) {
            throw new IllegalArgumentException("License plate cannot be null");
        }
        this.licensPlate = licensPlate;
    }

    public String getDescription() {
        return "this is a " + type + " with license plate " + licensePlate;
    }
}
