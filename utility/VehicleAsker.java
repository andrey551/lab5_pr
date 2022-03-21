package utility;

import java.util.NoSuchElementException;
import java.util.Scanner;

import Exception.IncorrectInputScriptException;
import Exception.MustBeNotEmptyException;
import Exception.NotDeclaredLimitsException;
import Vehicle.Coordinates;
import Vehicle.FuelType;
import main.lab5;

/**
 * Asks a user a marine's value.
 */
public class VehicleAsker {
    private final Integer MAX_X = 717;
    private final double MAX_Y = 12.0;

    private Scanner userScanner;
    private boolean fileMode;

    public VehicleAsker(Scanner userScanner) {
        this.userScanner = userScanner;
        fileMode = false;
    }

    /**
     * Sets a scanner to scan user input.
     * @param userScanner Scanner to set.
     */
    public void setUserScanner(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    /**
     * @return Scanner, which uses for user input.
     */
    public Scanner getUserScanner() {
        return this.userScanner;
    }

    /**
     * Sets vehicle asker mode to 'File Mode'.
     */
    public void setFileMode() {
        this.fileMode = true;
    }

    /**
     * Sets vehicle asker mode to 'User Mode'.
     */
    public void setUserMode() {
        this.fileMode = false;
    }

    /**
     * Ask vehicle's name
     * @return Vehicle's name
     * @throws IncorrectInputScriptException
     * @throws MustBeNotEmptyException
     */
    public String askName() throws IncorrectInputScriptException, MustBeNotEmptyException{
        String name;
        while(true) {
            try{
                Console.println("Type name: ");
                Console.print(lab5.PS2);
                name = userScanner.nextLine().trim();
                if (fileMode) Console.println(name);
                if(name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch(NoSuchElementException e) {
                Console.printerror("Name is not legal!");
                if(fileMode) throw new IncorrectInputScriptException();
            } catch (MustBeNotEmptyException e) {
                Console.printerror("Name field shouldn't empty!");
                if(fileMode) throw new IncorrectInputScriptException();
            } catch(IllegalStateException e) {
                Console.printerror("bug.........");
                System.exit(0);
            }
        }

        return name;
    }

    /**
     * Ask coordinate X
     * @return X coordinate (Integer)
     * @throws IncorrectInputScriptException
     */
    public Integer askX() throws IncorrectInputScriptException{
        String strX;
        Integer x;
        while (true) {
            try {
                Console.println("Input Coordinate X < " + (MAX_X+1) + ":");
                Console.println("Type coordinate X:");
                Console.print(lab5.PS2);
                strX = userScanner.nextLine().trim();
                if (fileMode) Console.println(strX);
                x = Integer.parseInt(strX);
                if (x > MAX_X) throw new NotDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Coordinate is not legal!");
                if (fileMode) throw new IncorrectInputScriptException();
            } catch (NotDeclaredLimitsException exception) {
                Console.printerror("Coordinate must be smaller than " + MAX_X + "!");
                if (fileMode) throw new IncorrectInputScriptException();
            }catch (NumberFormatException exception) {
                Console.printerror("Coordinate X must be a number!");
                if (fileMode) throw new IncorrectInputScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("bug........");
                System.exit(0);
            }
        }
        return x;
    }

    /**
     * Ask Y coordinate
     * @return coordinate Y ( double)
     * @throws IncorrectInputScriptException
     */
    public double askY() throws IncorrectInputScriptException {
        String strY;
        double y;
        while (true) {
            try {
                Console.println("Input coordinate Y < " + (MAX_Y+1) + ":");
                Console.print(lab5.PS2);
                strY = userScanner.nextLine().trim();
                if (fileMode) Console.println(strY);
                y = Double.parseDouble(strY);
                if (y > MAX_Y) throw new NotDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Coordinate is not legal!");
                if (fileMode) throw new IncorrectInputScriptException();
            } catch (NotDeclaredLimitsException exception) {
                Console.printerror("Coordinate Y must be smaller than " + MAX_Y + "!");
                if (fileMode) throw new IncorrectInputScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Coordinate Y must be a number!");
                if (fileMode) throw new IncorrectInputScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Bug.............");
                System.exit(0);
            }
        }
        return y;
    }

    /**
     * Ask vehicle coordinate
     * @return Vehicle coordinate
     * @throws IncorrectInputScriptException
     */
    public Coordinates askCoordinates() throws IncorrectInputScriptException {
        Integer x;
        double y;
        x = askX();
        y = askY();
        return new Coordinates(x, y);
    }

    /**
     * Ask vehicle engine power
     * @return Vehicle engine power
     * @throws IncorrectInputScriptException
     */
    public Double askEnginePower() throws IncorrectInputScriptException {
        String strEnginePower;
        Double enginePower;
        while(true) {
            try{
                Console.println("Type engine power: ");
                Console.print(lab5.PS2);
                strEnginePower = userScanner.nextLine().trim();
                if(fileMode) Console.println(strEnginePower);
                enginePower = Double.parseDouble(strEnginePower);
                if(enginePower <= 0) throw new NotDeclaredLimitsException();
                break;
            } catch (NotDeclaredLimitsException e){
                Console.printerror("Input must be bigger than 0");
                if (fileMode) throw new IncorrectInputScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Must be a double number");
                if (fileMode) throw new IncorrectInputScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Buggggg");
                System.exit(0);                
            }
        }
        
        return enginePower;
    }

    /**
     * Ask vehicle capacity
     * @return Vehicle capacity
     * @throws IncorrectInputScriptException
     */
    public Long askCapacity() throws IncorrectInputScriptException {
        String strCapacity;
        Long capacity;
        
        while(true) {
            try{
                Console.println("Type capacity: ");
                Console.print(lab5.PS2);
                strCapacity = userScanner.nextLine().trim();
                capacity = Long.parseLong(strCapacity);
                if(capacity <= 0) throw new NotDeclaredLimitsException();
                break;
            } catch (NotDeclaredLimitsException e){
                Console.printerror("Input must be bigger than 0");
                if (fileMode) throw new IncorrectInputScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Must be a double number");
                if (fileMode) throw new IncorrectInputScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Buggggg");
                System.exit(0);                
            }
        }
        
        return capacity;
    }

    /**
     * Ask vehicle fuel consumption
     * @return Vehicle fuel consumption
     * @throws IncorrectInputScriptException
     */
    public int askFuelConsumption() throws IncorrectInputScriptException{
        String strFuelConsumption;
        Integer fuelConsumption;
        while(true) {
            try{
                Console.println("Type fuel consumption: ");
                Console.print(lab5.PS2);
                strFuelConsumption = userScanner.nextLine().trim();
                fuelConsumption = Integer.parseInt(strFuelConsumption);
                if(fuelConsumption <= 0) throw new NotDeclaredLimitsException();
                break;
            } catch (NotDeclaredLimitsException e){
                Console.printerror("Input must be bigger than 0");
                if (fileMode) throw new IncorrectInputScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Must be a double number");
                if (fileMode) throw new IncorrectInputScriptException();
            }catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("buggggg!");
                System.exit(0);
            }
        }

        return (int)fuelConsumption;
    }

    /**
     * Ask vehicle fuel type
     * @return Vehicle fuel type
     * @throws IncorrectInputScriptException
     */
    public FuelType askFuelType() throws IncorrectInputScriptException {
        String strFuelType;
        FuelType fuel;

        while(true) {
            try{
                Console.println("List of Fuel Type: " + FuelType.nameList());
                Console.println("Type Fuel Type: ");
                Console.print(lab5.PS2);
                strFuelType = userScanner.nextLine().trim();
                if(fileMode) Console.println(strFuelType);
                fuel = FuelType.valueOf(strFuelType.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Input is not legal!");
                if (fileMode) throw new IncorrectInputScriptException();
            } catch (IllegalArgumentException exception) {
                Console.printerror("Input must be a type of Fuel!");
                if (fileMode) throw new IncorrectInputScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Bug...Bug...Bug...");
                System.exit(0);
            }
        }

        return fuel;
    }

    /**
     *
     * @param question
     * @return boolean mode
     * @throws IncorrectInputScriptException
     */
    public boolean askQuestion(String question) throws IncorrectInputScriptException {
        String finalQuestion = question + " (+/-):";
        String answer;
        while (true) {
            try {
                Console.println(finalQuestion);
                Console.print(lab5.PS2);
                answer = userScanner.nextLine().trim();
                if (fileMode) Console.println(answer);
                if (!answer.equals("+") && !answer.equals("-")) throw new NotDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Answer is not legal!");
                if (fileMode) throw new IncorrectInputScriptException();
            } catch (NotDeclaredLimitsException exception) {
                Console.printerror("Answer must be represent by '+' or '-'!");
                if (fileMode) throw new IncorrectInputScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("bug>output");
                System.exit(0);
            }
        }
        return (answer.equals("+")) ? true : false;
    }    

    @Override
    public String toString() {
        return "VehicleAsker (class for helping user request)";
    }
    

}
