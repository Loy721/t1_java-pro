package com.loy.dao;

import com.loy.model.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Repository
public class UserDao {
    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<User> createUser(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUserName());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return Optional.of(new User(statement.getGeneratedKeys().getLong(1), user.getUserName()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<User> getUser(long id) {
        String sql = "SELECT id, username FROM users WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    long userId = resultSet.getLong("id");
                    String userName = resultSet.getString("username");
                    return Optional.of(new User(userId, userName));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<User> getAllUsers() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users")) {
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String userName = resultSet.getString(2);
                users.add(new User(id, userName));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * Update user with param user id
     *
     * @param user
     * @return
     */
    public Optional<User> updateUser(User user) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE users SET userName = ? WHERE id = ?")) {

            stmt.setString(1, user.getUserName());
            stmt.setLong(2, user.getId());
            if (stmt.executeUpdate() == 0) {
                return Optional.empty();
            }
            return Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Delete user with param user id
     *
     * @param user
     * @return
     */
    public Optional<User> deleteUser(User user) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
            stmt.setLong(1, user.getId());
            if (stmt.executeUpdate() == 0) {
                return Optional.empty();
            }
            return Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
