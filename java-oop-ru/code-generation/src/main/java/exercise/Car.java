package exercise;

import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

@Value
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    public String serialize() {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return json;
    }

    // Метод для десериализации
    public static Car deserialize(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Car car = null; // Создаем переменную для хранения объекта Car
        try {
            // Десериализуем JSON в объект Car
            car = mapper.readValue(json, Car.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return car; // Возвращаем объект Car
    }

}
