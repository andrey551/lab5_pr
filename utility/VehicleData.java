package utility;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.HashMap;

import Vehicle.FuelType;
import Vehicle.Vehicle;

/**
 * Operates the collection itself.
 */
public class VehicleData {
    private ArrayDeque<Vehicle> vehicleCollection = new ArrayDeque<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private FileManager fileManager;

    public VehicleData(FileManager fileManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileManager = fileManager;

        loadCollection();
    }
    /**
     * @return The collecton itself.
     */
    public ArrayDeque<Vehicle> getCollection() {
        return vehicleCollection;
    }
    /**
     * @return Last initialization time or null if there wasn't initialization.
     */
    public LocalDateTime getLastInitTime() {
        return this.lastInitTime;
    }
    /**
     * @return Last save time or null if there wasn't saving.
     */
    public LocalDateTime getLastSaveTime() {
        return this.lastSaveTime;
    }
    /**
     * @return Name of the collection's type.
     */
    public String collectionType() {
        return vehicleCollection.getClass().getName();
    }
    /**
     * @return Size of the collection.
     */
    public Integer collectionSize() {
        return vehicleCollection.size();
    }

    /**
     * add element to collection
     * @param vehicle
     */
    public void addElement(Vehicle vehicle) {
        vehicleCollection.add(vehicle);
    }

    /**
     * get vehicle by id itself
     * @param id
     * @return vehicle
     */
    public Vehicle getById(int id) {

        for(Vehicle veh : vehicleCollection) {
            if(veh.getId() == id) {
                return veh;
            }
        }

        return null;
        
    }

    /**
     * return vehicle that equal itself
     * @param vehicle
     * @return vehicle
     */
    public Vehicle getByValue(Vehicle vehicle) {
        for(Vehicle veh : vehicleCollection) {
            if((veh.equals(vehicle))) return veh;
        }

        return null;
    }

    /**
     * Update vehicle by ID itself
     * @param vehicle
     */
    public void updateById(Vehicle vehicle) {
        for(Vehicle veh : vehicleCollection) {
            if(veh.getId() == vehicle.getId()) {
                veh = vehicle;

                break;
            }
        }
    }

    /**
     * Counting group that have the same capacity
     * @return map <capacity, value>
     */
    public HashMap<Long, Integer> groupCoutingByCapacity() {
        HashMap<Long,Integer> result = new HashMap<Long, Integer>();
        ArrayDeque<Vehicle> temp = vehicleCollection.clone();
        while(!temp.isEmpty()){
            Long key = temp.getFirst().getCapacity();
            int val = 0;
            for(Vehicle vehicle : temp) {
                if(vehicle.getCapacity() == key) {
                    ++val;
                    temp.remove(vehicle);
                }
            }

            result.put(key, val);
        }
        
        return result;
    }

    /**
     * Count the number is vehicle that have lower fuel type than the given
     * @param fuelType
     * @return number of vehicle
     */
    public Integer CountLessThanFuelType(FuelType fuelType) {
        Integer result = 0;

        for(Vehicle vehicle : vehicleCollection) {
            if(vehicle.getFuelType().compareTo(fuelType) < 0) ++result;
        }

        return result;
    }

    /**
     * Save collection to file
     */
    public void saveCollection() {
        fileManager.writeCollection(vehicleCollection);
        lastSaveTime = LocalDateTime.now();
    }

    /**
     * return list of vehicle that have smaller consumption than the given
     * @param conSumptionToCompare
     * @return ArrayDeque of vehicle
     */
    public ArrayDeque<Vehicle> FilterLessThanFuelConsumption(int conSumptionToCompare) {
        ArrayDeque<Vehicle> result = new ArrayDeque<>();

        for(Vehicle vehicle : vehicleCollection) {
            if(vehicle.getFuelConsumption() < conSumptionToCompare) {
                result.add(vehicle);
            }
        }

        return result;
    }

    /**
     * get last vehicle in the list
     * @return vehicle
     */
    public Vehicle getLast() {
        if(vehicleCollection.isEmpty()) return null;
        return vehicleCollection.peekLast();
    }

    /**
     * remove first element in collection
     */
    public void removeFirst() {
        vehicleCollection.removeFirst();
    }

    /**
     * clear collection
     */
    public void clearCollection() {
        vehicleCollection.clear();
    }

    /**
     * remove vehicle is given from collection
     * @param veh
     */
    public void removeFromCollection(Vehicle veh) {
                vehicleCollection.remove(veh);
    }

    /**
     * load collection from file
     */
    public void loadCollection() {
        vehicleCollection = fileManager.readCollection();
        lastInitTime = LocalDateTime.now();
    }

    /**
     * auto generate next id when vehicle is created
     * @return next ID
     */
    public Integer generateNextId() {
        if(vehicleCollection.isEmpty()) return 1;
        return vehicleCollection.getLast().getId() + 1;
    }

    /**
     * remove vehicle form list if the give is better
     * @param vehicleToCompare
     */
    public void removeGreater(Vehicle vehicleToCompare) {
        vehicleCollection.removeIf(vehicle ->vehicle.compareTo(vehicleToCompare) > 0);
    }

    @Override
    public String toString() {
        if(vehicleCollection.isEmpty()) return "Collection is empty!";

        String result = "";
        for(Vehicle vehicle : vehicleCollection) {
            result += vehicle + "\n";
        }

        return result;
    }
}
