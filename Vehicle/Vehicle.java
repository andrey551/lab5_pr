package Vehicle;

import java.util.Date;

/**
 * Vehicle class
 */
public class Vehicle implements Comparable<Vehicle> {
    private Integer id;
    private String name; 
    private Coordinates coordinates; 
    private Date creationDate;
    private Double enginePower;
    private Long capacity;
    private int fuelConsumption;
    private FuelType fuelType;

    public Vehicle(Integer id,String name,Coordinates coordinates,Date creationDate,
                    Double enginePower,Long capacity,int fuelConsumption,FuelType fuelType){
        this.id = id;
        this.name = name; 
        this.coordinates = coordinates; 
        this.creationDate = creationDate;
        this.enginePower = enginePower;
        this.capacity = capacity;
        this.fuelConsumption = fuelConsumption;
        this.fuelType = fuelType;
    }

    /**
     * @return id itself
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * @return name itself
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return coordinates itself
     */
    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    /**
     * @return date itself
     */
    public Date getCreationDate() {
        return this.creationDate;
    }

    /**
     * @return engine power itself
     */
    public Double getEnginePower() {
        return this.enginePower;
    }

    /**
     * @return capacity itself
     */
    public Long getCapacity() {
        return this.capacity;
    }

    /**
     * @return fuel consumption itself
     */
    public int getFuelConsumption() {
        return this.fuelConsumption;
    }

    /**
     * @return fuel type itself
     */
    public FuelType getFuelType() {
        return this.fuelType;
    }

    @Override
    public int compareTo(Vehicle vehicleObj) {
        return id.compareTo(vehicleObj.getId());
    }

    @Override
    public String toString() {
        String info = "";
        info += "Id: " + this.getId();
        info += "\n Name:" + this.getName();
        info += "\n Coordinate: " + this.getCoordinates().toString();
        info += "\n Date: " + creationDate.toString();
        info += "\n Engine Power: " + this.getEnginePower();
        info += "\n Capacity: " + this.getCapacity();
        info += "\n Fuel Consumption: " + this.getFuelConsumption();
        info += "\n Fuel Type: " + this.getFuelType();

        return info;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + coordinates.hashCode() + creationDate.hashCode() + 
                fuelType.hashCode() + enginePower.hashCode() +  capacity.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        
        if( obj instanceof Vehicle) {
            Vehicle vehicleObj = (Vehicle) obj;
            return name.equals(vehicleObj.getName()) && coordinates.equals(vehicleObj.getCoordinates()) && 
                    creationDate.equals(vehicleObj.getCreationDate()) && enginePower.equals(vehicleObj.getEnginePower()) &&
                    capacity.equals(vehicleObj.getCapacity()) && (fuelConsumption==vehicleObj.getFuelConsumption()) &&
                    fuelType.equals(vehicleObj.getFuelType());
        }

        return false;
    }
}
