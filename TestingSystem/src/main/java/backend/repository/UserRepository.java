package backend.repository;

import entity.Employee;
import entity.Manager;
import entity.User;
import utils.JDBCUtils;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private JDBCUtils jdbcUtils;

    public UserRepository() {
        jdbcUtils = JDBCUtils.getInstance();
    }

    public List<User> getListUsersByProjectId(int projectId) throws SQLException, IOException {
        List<User> users = new ArrayList<User>();
        Connection connection = jdbcUtils.getConnection();
        String sql = "SELECT id, full_name, email, `password`,role FROM `User` WHERE project_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, projectId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            String fullName = resultSet.getString("full_name");
            String email = resultSet.getString("email");
            String role = resultSet.getString("role");
            if(role.equals("Manager")) {
                User user = new Manager(id, fullName, email);
                users.add(user);
            }
            if(role.equals("Employee")) {
                User user = new Employee(id, fullName, email);
                users.add(user);
            }
        }
        jdbcUtils.disconnect();

        return users;
    }

    public User getUserById(int id) throws SQLException, IOException{
        try {
            Connection connection = jdbcUtils.getConnection();
            String sql = "SELECT id, full_name, email, project_id, `role`, exp_in_year, pro_skill FROM `User` WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                String fullName = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                if(role.equals("Manager")) {
                    int expInYear = resultSet.getInt("exp_in_year");
                    User manager = new Manager(id, fullName, email, expInYear);
                    return manager;
                }
                if(role.equals("Employee")) {
                    String proSkill = resultSet.getString("pro_skill");
                    User employee = new Employee(id, fullName, email, proSkill);
                    return employee;
                }
            }

        } finally {
            jdbcUtils.disconnect();
        }
        return null;
    }

    public boolean isUserExistsByEmail(String email) throws SQLException, IOException {
        try {
            Connection connection = jdbcUtils.getConnection();
            String sql = "SELECT 1 FROM `User` WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
                return true;
            else
                return false;
        } finally {
          jdbcUtils.disconnect();
        }
    }

    public User login(String email, String password) throws SQLException, IOException {
        try {
            Connection connection = jdbcUtils.getConnection();

            String sql = "SELECT id, full_name, `role` FROM `User` WHERE email = ? AND `password` = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int id = resultSet.getInt("id");
                String fullName = resultSet.getString("full_name");
                String role = resultSet.getString("role");
                if(role.equals("Manager")) {
                    User manager = new Manager(id, fullName, email);
                    return manager;
                }
                if(role.equals("Employee")) {
                    User employee = new Employee(id, fullName, email);
                    return employee;
                }
            } else {
                return null;
            }

        } finally {
            jdbcUtils.disconnect();
        }
        return null;
    }

    public void createUser(String fullName, String email) throws SQLException, IOException {
        Connection connection = jdbcUtils.getConnection();

        String sql = "INSERT INTO `User` (full_name, email, `password`, exp_in_year, pro_skill, `role`) " +
                "VALUES (?, ?, '123456', null, null, Employee";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, fullName);
        preparedStatement.setString(2, email);

        preparedStatement.executeUpdate();
    }
}
