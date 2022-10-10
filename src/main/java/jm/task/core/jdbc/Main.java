package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("Mikhail", "Kurmaev", (byte) 22);
        User user2 = new User("Alexandr", "Dotsenko", (byte) 22);
        User user3 = new User("Daria", "Overchenko", (byte) 19);
        User user4 = new User("Oleg", "Kivgazov", (byte) 23);

        UserServiceImpl us = new UserServiceImpl();
        us.createUsersTable();
        us.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        us.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        us.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        us.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        List<User> list = new ArrayList<>(us.getAllUsers());
        for(User u : list) {
            System.out.println(u);
        }

        us.removeUserById(1);
        us.removeUserById(2);

        List<User> list2 = new ArrayList<>(us.getAllUsers());
        for(User u : list2) {
            System.out.println(u);
        }

        us.cleanUsersTable();

        List<User> list3 = new ArrayList<>(us.getAllUsers());
        for(User u : list3) {
            System.out.println(u);
        }

        us.dropUsersTable();
    }
}
