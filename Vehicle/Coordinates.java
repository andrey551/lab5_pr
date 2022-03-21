package Vehicle;

import Exception.coordinatesInputException;
/**
 * X-Y coordinates.
 */
public class Coordinates {
    private static final Integer maxX = (Integer)717;
    private static final double maxY = 12.0;
    private Integer x; 
    private double y;

    public Coordinates(Integer x, double y) throws coordinatesInputException{
        if(x > maxX || y > maxY) throw new coordinatesInputException();
        this.x = x;
        this.y = y;
    }
    /**
     * @return X-coordinate.
     */
    public Integer getX() {
        return this.x;
    }
    /**
     * @return Y-coordinate.
     */
    public double getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "X: " + this.getX() + " Y: " + this.getY();
    }

    @Override
    public int hashCode() {
        return x.hashCode() + (int)y;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(obj instanceof Coordinates) {
            Coordinates coordinatesObj = (Coordinates) obj;
            return (x.equals(coordinatesObj.getX()) && (y == coordinatesObj.getY())); 
        }

        return false;
    }

}
