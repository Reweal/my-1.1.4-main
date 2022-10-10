package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final Connection conn = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String query = "create table if not exists users\n" +
                "(\n" +
                "    id       INT auto_increment not null,\n" +
                "    name     TEXT not null,\n" +
                "    lastName TEXT not null,\n" +
                "    age      INT  null,\n" +
                "    constraint users_pk\n" +
                "        primary key (id)\n" +
                ");";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.executeUpdate();
            System.out.println("Таблица users успешно создана.");
        } catch (SQLException e) {
            System.err.println("При создании таблицы произошла ошибка");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String query = "drop table if exists users;";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.executeUpdate();
            System.out.println("Таблица успешно удалена.");
        } catch (SQLException e) {
            System.err.println("При удалении таблицы произошла ошибка");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "insert into users(name, lastname, age) values(?, ?, ?);";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            System.out.println("User " + name + " успешно добавлен в таблицу");
        } catch (SQLException e) {
            System.err.println("При создании user произошла ошибка.");
        }
    }

    public void removeUserById(long id) {
        String query = "delete from users where id = ?;";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, id);
            ps.executeUpdate();
            System.out.println("User под номером " + id + " успешно удалён");
        } catch (SQLException e) {
            System.err.println("При удалении user произошла ошибка.");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> usery = new ArrayList<>();
        String query = "Select * from users;";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            System.out.println("Таблица users :");
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                byte age = rs.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                usery.add(user);
            }
        } catch (SQLException e) {
            System.err.println("При получении таблицы произошла ошибка.");
            e.printStackTrace();
        }
        return usery;
    }

    public void cleanUsersTable() {
        String query = "truncate table users";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.executeUpdate();
            System.out.println("Таблица успешно очищена");
        } catch (SQLException e) {
            System.err.println("При очищении таблицы users произошла ошибка");
        }
    }
}
