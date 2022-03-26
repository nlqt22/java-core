import backend.controller.UserController;
import entity.Role;
import entity.User;
import utils.ScannerUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Program {
    private UserController userController;

    public Program() {
        userController = new UserController();
    }

    public void getListUsersByProjectId() throws SQLException, IOException {
        System.out.print("Nhập Project ID : ");
        int projectId = ScannerUtils.scanInt();
        List<User> users = userController.getListUsersByProjectId(projectId);
        int count = 0;
        System.out.println("== Bảng thông tin Project : " + projectId + " ==");
        for(User user : users) {
            System.out.println(++count + ". " + user.getFullName() + " (" + user.getRole() + ")");
        }
    }

    public User login() throws SQLException, IOException {
        System.out.println("Vui lòng đăng nhập");
        while(true) {
            System.out.print("Nhập Email : ");
            String email = ScannerUtils.scanEmail("[Lỗi] Email không hợp lệ");
            if (userController.isUserExistsByEmail(email)) {
                System.out.print("Nhập Password : ");
                String password = ScannerUtils.scanPassword("[Lỗi] Password không hợp lệ");
                User user = userController.login(email, password);

                if (user != null) {
                    System.out.println("Đăng nhập thành công. Xin chào " + user.getFullName());
                    return user;
                } else {
                    System.err.println("[Lỗi] Email hoặc mật khẩu không chính xác");
                }
            }
        }
    }

    public void addUser() throws SQLException, IOException {
        System.out.print("[2] Nhập Fullname cho tài khoản : ");
        String fullName = ScannerUtils.scanName("[Lỗi] Tên không hợp lệ");
        String email;
        System.out.print("Nhập email : ");
        while(true) {
            email = ScannerUtils.scanEmail("[Lỗi] Email không hợp lệ");
            if(userController.isUserExistsByEmail(email)) {
                System.err.println("[Lỗi] Email "+ email +" đã tồn tại");
                System.out.print("Nhập email khác : ");
            } else {
                break;
            }
        }
        userController.createUser(fullName, email);
        System.out.println("Tạo user " + email + " thành công");
    }

    public void run() throws SQLException, IOException {
        User account = login();
        User user = userController.getUserById(account.getId());

        System.out.println("============          MENU          ============");
        System.out.println("= 1 Tra cứu danh sách thành viên trong Project =");
        System.out.println("= 2 Tạo tài khoản cho Employee                 =");
        System.out.println("= 0 Thoát                                      =");
        System.out.println("================================================");
        int luaChon;
        do {
            System.out.print("[Bạn] ");
            luaChon = ScannerUtils.scanInt();
            switch (luaChon) {
                case 0:
                    System.out.println("Tạm biệt");
                    break;
                case 1:
                    getListUsersByProjectId();
                    break;
                case 2:
                    if(user.getRole() == Role.MANAGER) addUser();
                    else System.err.println("Phải là MANAGER mới thực hiện được chức năng này");
                    break;
                default:
                    System.err.println("[Hệ thống] Chức năng không hợp lệ");
                    break;
            }
        } while(luaChon != 0);
    }
}
