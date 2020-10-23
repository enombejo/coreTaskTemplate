package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        User user = new User("Fyodor", "Fyodorov", (byte) 25);
        userService.saveUser(user.getName(), user.getLastName(), user.getAge());
        System.out.printf("User с именем – %s добавлен в базу данных\n", user.getName());


        user = new User("Ivan", "Ivanov", (byte) 56);
        userService.saveUser(user.getName(), user.getLastName(), user.getAge());
        System.out.printf("User с именем – %s добавлен в базу данных\n", user.getName());


        user = new User("Sergei", "Sergeev", (byte) 90);
        userService.saveUser(user.getName(), user.getLastName(), user.getAge());
        System.out.printf("User с именем – %s добавлен в базу данных\n", user.getName());

        List<User> list = userService.getAllUsers();
        System.out.println(list);

        userService.createUsersTable();

        userService.dropUsersTable();
    }
}
