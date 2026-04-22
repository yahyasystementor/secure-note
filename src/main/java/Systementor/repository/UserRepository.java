package Systementor.repository;

import Systementor.config.DatabaseConnection;
import Systementor.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    // TODO
    // Login users
    // Get user from db
    // LAST Check if username already in use.
    // Check if password is correct



    public boolean existsByUsername(String username) {
        String sql = "SELECT id FROM users WHERE username = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();

        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }




    public User findByUsername(String username){
        String sql = "SELECT * FROM users WHERE username = ?";

        try(Connection connection = DatabaseConnection.getConnection();
              PreparedStatement statement = connection.prepareStatement(sql)) {

              statement.setString(1, username);

              ResultSet resultSet = statement.executeQuery();

              if(resultSet.next()) {
                  User user = new User();
                  user.setId(resultSet.getInt("id"));
                  user.setUsername(resultSet.getString("username"));
                  user.setPassword(resultSet.getString("password"));
                  user.setRole(resultSet.getString("role"));
                  return user;
              }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }









    public boolean saveUser(String username, String password, String role) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?,?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1,username);
            statement.setString(2,password);
            statement.setString(3,role);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
