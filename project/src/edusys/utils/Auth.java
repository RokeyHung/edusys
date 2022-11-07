package edusys.utils;

import edusys.entity.NhanVien;

/**
 *
 * @author Manh Hung
 */
public class Auth {

    /*
     * Duy trì user đăng nhập vào hệ thống
     */
    public static NhanVien user = null;

    /**
     * Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất
     */
    public static void clear() {
        Auth.user = null;
    }

    /**
     * Kiểm tra xem đăng nhập hay chưa
     */
    public static boolean isLogin() {
        return Auth.user != null;
    }

    /**
     * Kiểm tra xem có phải là trưởng phòng hay không
     */
    public static boolean isManager() {
        return isLogin() && user.isVaiTro();
    }

}
