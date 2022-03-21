package utility;

import Exception.CollectionIsEmptyException;
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
import java.util.Date;
import java.util.ArrayDeque;
import java.util.List;

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
        } catch (CollectionIsEmptyException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Reads collection from a file.
     * @return Read collection.
     */
    public ArrayDeque<Vehicle> readCollection(){

        ArrayDeque<Vehicle> result = new ArrayDeque<>();
        try {
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
                Integer id = Integer.parseInt(vehicle.getElementsByTagName("id").item(0).getTextContent());
                String name = vehicle.getElementsByTagName("name").item(0).getTextContent();
                Element coordinate = (Element) vehicle.getElementsByTagName("coordinates").item(0);
                Coordinates coordinates = new Coordinates(
                        Integer.parseInt(coordinate.getElementsByTagName("X").item(0).getTextContent()),
                        Double.parseDouble(coordinate.getElementsByTagName("Y").item(0).getTextContent())
                );
                Date date = new Date();
                Double enginePower = Double.parseDouble(vehicle.getElementsByTagName("enginePower").item(0).getTextContent());
                Long capacity = Long.parseLong(vehicle.getElementsByTagName("capacity").item(0).getTextContent());
                int fuelConsumption = Integer.parseInt(vehicle.getElementsByTagName("fuelConsumption").item(0).getTextContent());
                FuelType fuelType = FuelType.valueOf(vehicle.getElementsByTagName("fuelType").item(0).getTextContent());
                result.add(new Vehicle(id, name, coordinates,date,enginePower,capacity,fuelConsumption,fuelType));
            }


        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String toString() {

        return "File Manager (>-<)";
    }
}
