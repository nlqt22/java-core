package backend.service;

import backend.repository.UserRepository;
import entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    public List<User> getListUsersByProjectId(int projectId) throws SQLException, IOException {
        return userRepository.getListUsersByProjectId(projectId);
    }

    public User getUserById(int id) throws SQLException, IOException {
        return userRepository.getUserById(id);
    }

    public boolean isUserExistsByEmail(String email) throws SQLException, IOException {
        return userRepository.isUserExistsByEmail(email);
    }

    public User login(String email, String password) throws SQLException, IOException {
        return userRepository.login(email, password);
    }

    public void createUser(String fullName, String email) throws SQLException, IOException {
        userRepository.createUser(fullName, email);
    }
}
