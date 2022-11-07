package edusys.utils;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author Manh Hung
 */
public class XImage {

    /**
     * Ảnh biểu tượng của ứng dụng, xuất hiện trên mọi cửa sổ
     */
    public static Image getAppIcon() {
        URL url = XImage.class.getResource("/image/icon/fpt.png");
        return new ImageIcon(url).getImage();
    }

    /**
     * Sao chép file logo chuyên đề vào thư mục logo
     *
     * @param src là đối tượng file ảnh
     */
    public static void save(File src) {
        File fl = new File("logos", src.getName());
        // Tạo thư mục logos nếu chưa tồn tại
        if (!fl.getParentFile().exists()) {
            fl.getParentFile().mkdirs();
        }
        // Copy file vào thư mục logos
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(fl.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Đọc hình ảnh logo chuyên đề
     *
     * @param fileName là tên file logo
     * @return ảnh đọc được
     */
    public static ImageIcon read(String fileName) {
        File path = new File("src/image/logos", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
}
