package Vehicle;
/**
 * Enumeration with vehicle fuel type constants.
 */
public enum FuelType {
    ELECTRICITY,
    MANPOWER,
    ANTIMATTER;
    /**
     * Generates a beautiful list of enum string values.
     * @return String with all enum values splitted by comma.
     */
    public static String nameList() {
        String nameList = "";
        for(FuelType fuelType : values()) {
            nameList += fuelType.name() + ", ";
        }

        return nameList.substring(0, nameList.length() -2);
    }
}
