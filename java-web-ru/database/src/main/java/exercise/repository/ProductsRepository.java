package exercise.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import exercise.model.Product;

import java.sql.SQLException;
import java.sql.Statement;

public class ProductsRepository extends BaseRepository {

    public static void save(Product product) throws SQLException {
        String save = "INSERT INTO product (title, price) VALUES (?, ?)";
        try (var connect = dataSource.getConnection();
             PreparedStatement stmt = connect.prepareStatement(save, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, product.getTitle());
            stmt.setInt(2, product.getPrice());
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.next()) {
                product.setId(resultSet.getLong("id"));
            } else {
                throw new SQLException("Не удалось добавить объект в БД");
            }
        }
    }

    public static Optional<Product> find(Long id) throws SQLException {
        String find = "SELECT * FROM product WHERE id = ?";
        try (var connect = dataSource.getConnection();
             PreparedStatement stmt = connect.prepareStatement(find)) {
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString("title");
                int price = resultSet.getInt("price");
                Product product = new Product(title, price);
                product.setId(id);
                return Optional.of(product);
            }
            return Optional.empty();
        }
    }

    public static List<Product> getEntities() throws SQLException {
        String getEntities = "SELECT * FROM product";
        try (var connect = dataSource.getConnection();
             PreparedStatement stmt = connect.prepareStatement(getEntities)) {
            List<Product> products = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                int price = resultSet.getInt("price");
                Product product = new Product(title, price);
                product.setId(id);
                products.add(product);
            }
            return products;
        }
    }
}
