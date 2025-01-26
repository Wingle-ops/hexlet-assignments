package exercise;

public class Cottage implements Home{

    public double area;
    public int floorCount;

    Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public String toString() {
        return floorCount + " этажный коттедж площадью " + area + " метров";
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public double compareTo(Flat flat) {
        return Double.compare(this.getArea(), flat.getArea());
    }

}
