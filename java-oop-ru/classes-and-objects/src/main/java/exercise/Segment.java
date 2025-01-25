package exercise;

class Segment {

    private final Point one;
    private final Point two;

    Segment(Point one, Point two) {
        this.one = one;
        this.two = two;
    }

    public Point getBeginPoint() {
        return one;
    }

    public Point getEndPoint() {
        return two;
    }

    public Point getMidPoint() {
        int middle1 = (one.getX() + two.getX()) / 2;
        int middle2 = (one.getY() + two.getY()) / 2;
        return new Point(middle1, middle2);
    }

}
