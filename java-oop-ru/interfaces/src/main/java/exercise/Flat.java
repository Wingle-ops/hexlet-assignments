package exercise;

public class Flat implements Home{

    public double area;
    public double balconyArea;
    public int floor;

    Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public double getArea() {
        return area + balconyArea;
    }

    @Override
    public String toString() {
        return "Квартира площадью " + getArea() + " метров на " + floor + " этаже";
    }

    @Override
    public double compareTo(Flat flat) {
        return Double.compare(this.getArea(), flat.getArea());
    }

}
