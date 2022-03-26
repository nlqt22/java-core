package backend.controller;

import backend.service.UserService;
import entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserController {
    private UserService userService;

    public UserController() {
        userService = new UserService();
    }

    public List<User> getListUsersByProjectId(int projectId) throws SQLException, IOException {
        return userService.getListUsersByProjectId(projectId);
    }

    public User getUserById(int id) throws SQLException, IOException {
        return userService.getUserById(id);
    }

    public boolean isUserExistsByEmail(String email) throws SQLException, IOException {
        return userService.isUserExistsByEmail(email);
    }

    public User login(String email, String password) throws SQLException, IOException {
        return userService.login(email, password);
    }

    public void createUser(String fullName, String password) throws SQLException, IOException {
        userService.createUser(fullName, password);
    }
}
