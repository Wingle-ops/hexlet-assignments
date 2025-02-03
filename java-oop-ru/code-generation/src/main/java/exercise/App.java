package exercise;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.io.IOException;

public class App {

    public static void save(Path path, Car car) {
        try {
            // Читаем текущее содержимое файла
            String content = Files.readString(path);
            content += "\n" + car.serialize(); // Добавляем сериализованный объект
            Files.writeString(path, content, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace(); // Обработка ошибок
        }
    }

    public static Car extract(Path path) {
        try {
            // Читаем содержимое файла
            String content = Files.readString(path);
            // Предполагаем, что в файле содержится JSON представление последнего объекта Car
            String[] lines = content.split("\n");
            String lastLine = lines[lines.length - 1]; // Получаем последнюю строку
            return Car.deserialize(lastLine); // Десериализуем и возвращаем объект Car
        } catch (IOException e) {
            e.printStackTrace(); // Обработка ошибок
            return null;
        }
    }
}
