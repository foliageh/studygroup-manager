package models;

public class Coordinates implements Model, Comparable<Coordinates> {
    private int x; //must be greater than -261
    private double y; //must be greater than -162
    private final int X_LOWER_BOUND = -261;
    private final double Y_LOWER_BOUND = -162;

    public Coordinates() {}
    public Coordinates(int x, double y) {
        if (x <= X_LOWER_BOUND || y <= Y_LOWER_BOUND)
            throw new RuntimeException("Invalid coordinates.");
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("(x=%d, y=%f)", x, y);
    }

    @Override
    public int compareTo(Coordinates coordinates) {
        return Integer.compare(x, coordinates.x);
    }
}
