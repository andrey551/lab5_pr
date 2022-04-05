package utility;

import Exception.CollectionIsEmptyException;
import Exception.coordinatesInputException;
import Vehicle.Coordinates;
import Vehicle.FuelType;
import Vehicle.Vehicle;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayDeque;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Operates the file for saving/loading collection.
 */
public class FileManager {
    private final String path;

    public FileManager(String path){
        this.path = path;
    }

    /**
     * Writes collection to a file.
     * @param collection Collection to write.
     */
    public void writeCollection(ArrayDeque<Vehicle> collection) {
        try {
            String toWrite = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
            toWrite += "<vehicleData>\n";

            if (collection.isEmpty()) throw new CollectionIsEmptyException();
            for(Vehicle vehicle : collection) {
                toWrite += "\t<Vehicle>\n";
                toWrite += "\t\t<id>" + vehicle.getId() + "</id>\n";
                toWrite += "\t\t<name>" + vehicle.getName() + "</name>\n";
                toWrite += "\t\t<coordinates>\n";
                toWrite += "\t\t\t<X>" + vehicle.getCoordinates().getX() + "</X>\n";
                toWrite += "\t\t\t<Y>" + vehicle.getCoordinates().getY() + "</Y>\n";
                toWrite += "\t\t</coordinates>\n";
                toWrite += "\t\t<creationDate>" + vehicle.getCreationDate() +"</creationDate>\n";
                toWrite += "\t\t<enginePower>" + vehicle.getEnginePower() + "</enginePower>\n";
                toWrite += "\t\t<capacity>" + vehicle.getCapacity() + "</capacity>\n";
                toWrite += "\t\t<fuelConsumption>" + vehicle.getFuelConsumption() + "</fuelConsumption>\n";
                toWrite += "\t\t<fuelType>" + vehicle.getFuelType() + "</fuelType>\n";
                toWrite += "\t</Vehicle>\n";
            }
            toWrite += "</vehicleData>\n";

            PrintWriter writeToFile = new PrintWriter(path);
            writeToFile.write(toWrite);
            writeToFile.flush();
            writeToFile.close();
            Console.println("Save to file success!");
        } catch (FileNotFoundException e) {
            Console.println("File error, rebuild again!");
        } catch (CollectionIsEmptyException e) {
            Console.println("Empty collection!");
        }

    }

    /**
     * Reads collection from a file.
     * @return Read collection.
     */
    public ArrayDeque<Vehicle> readCollection(){

        ArrayDeque<Vehicle> result = new ArrayDeque<>();
        try {
            if(path.length() < 1) {
                Console.println("Path argument is missing!");
                Console.println("Program can't save data!");
                return result;
            }

            String XMLString = "";
            FileInputStream in = new FileInputStream(path);

            InputStreamReader in_strm = new InputStreamReader(in);

            int t;
            while((t = in_strm.read()) != -1) {
                XMLString += (char) t;
            }
            in_strm.close();
            in.close();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(XMLString)));
            NodeList elements = document.getDocumentElement().getElementsByTagName("Vehicle");

            for(int i = 0; i < elements.getLength(); ++i) {
                Element vehicle = (Element) elements.item(i);
                Integer id = i+1;
                String name = vehicle.getElementsByTagName("name").item(0).getTextContent();
                if(name.length() < 1) continue;
                Element coordinate = (Element) vehicle.getElementsByTagName("coordinates").item(0);
                Coordinates coordinates = new Coordinates(
                        Integer.parseInt(coordinate.getElementsByTagName("X").item(0).getTextContent()),
                        Double.parseDouble(coordinate.getElementsByTagName("Y").item(0).getTextContent())
                );

                SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd hh:mm:ss z yyyy", Locale.ENGLISH);
                formatter.setTimeZone(TimeZone.getTimeZone("MSK"));
                Date date = formatter.parse(vehicle.getElementsByTagName("creationDate").item(0).getTextContent());
                Double enginePower = Double.parseDouble(vehicle.getElementsByTagName("enginePower").item(0).getTextContent());
                Long capacity = Long.parseLong(vehicle.getElementsByTagName("capacity").item(0).getTextContent());
                int fuelConsumption = Integer.parseInt(vehicle.getElementsByTagName("fuelConsumption").item(0).getTextContent());
                FuelType fuelType = FuelType.valueOf(vehicle.getElementsByTagName("fuelType").item(0).getTextContent());
                if(!checkObject( name, coordinates,enginePower,capacity,fuelConsumption,fuelType)) {
                    Console.println("Missing an element because of wrong input data!");
                } else {
                    result.add(new Vehicle(id, name, coordinates, date, enginePower, capacity, fuelConsumption, fuelType));
                }
            }


        } catch (IOException | ParserConfigurationException | SAXException  e) {
            Console.println("File error, rebuild again!");
        } catch (coordinatesInputException|
                ParseException| IllegalArgumentException e) {
            Console.println("File input have wrong data, check it out and try again!");
        }

        return result;
    }

    boolean checkObject(String name,Coordinates coordinates,
                        Double enginePower,Long capacity,
                        int fuelConsumption,FuelType fuelType) {
        if(name == null || name.equals("")) return false;
        if(coordinates == null ) return false;
        if( coordinates.getX() > 717) return false;
        if(coordinates.getY() > 12) return false;
        if(enginePower == null || enginePower < 0) return false;
        if(capacity == null || capacity < 0) return false;
        if(fuelConsumption < 0) return false;
        if(fuelType == null) return false;

        return true;

    }

    @Override
    public String toString() {

        return "File Manager (>-<)";
    }
}
