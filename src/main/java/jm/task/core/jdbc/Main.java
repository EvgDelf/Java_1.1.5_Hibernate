package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl us = new UserServiceImpl();
        us.createUsersTable();
        us.saveUser("Ivan", "Ivanov", (byte) 7);
        us.saveUser("Peter", "Ivanov", (byte) 72);
        us.saveUser("Ivan", "Petrov", (byte) 92);
        us.saveUser("Ivana", "Ivanova", (byte) 17);
        List<User> user = us.getAllUsers();
        for(User u : user) {
            System.out.println(u.toString());
        }
        us.cleanUsersTable();
        us.dropUsersTable();
    }
}
