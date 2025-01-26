package exercise;

import java.util.Comparator;
import java.util.List;

public class App {

    public static List<String> buildApartmentsList(List<Home> apartments, int n) {
        return apartments.stream()
                .sorted(Comparator.comparingDouble(Home::getArea))
                .map(Home::toString)
                .limit(n)
                .toList();
    }
}
