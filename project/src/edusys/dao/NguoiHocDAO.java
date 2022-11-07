package edusys.dao;

import edusys.entity.NguoiHoc;
import edusys.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manh Hung
 */
public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String> {

    @Override
    public void insert(NguoiHoc model) {
        String sql = "INSERT INTO NguoiHoc (MaNH, HoTen, NgaySinh, GioiTinh,"
                + " DienThoai, Email, GhiChu, MaNV) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        XJdbc.update(sql,
                model.getMaNH(),
                model.getHoTen(),
                model.getNgaySinh(),
                model.isGioiTinh(),
                model.getDienThoai(),
                model.getEmail(),
                model.getGhiChu(),
                model.getMaNV());
    }

    @Override
    public void update(NguoiHoc model) {
        String sql = "UPDATE NguoiHoc SET HoTen = ?, NgaySinh = ?, GioiTinh = ?, "
                + "DienThoai = ?, Email = ?, GhiChu = ?, MaNV = ? WHERE MaNH = ?";
        XJdbc.update(sql,
                model.getHoTen(),
                model.getNgaySinh(),
                model.isGioiTinh(),
                model.getDienThoai(),
                model.getEmail(),
                model.getGhiChu(),
                model.getMaNV(),
                model.getMaNH());
    }

    @Override
    public void delete(String maNH) {
        String sql = "DELETE FROM NguoiHoc WHERE MaNH=?";
        XJdbc.update(sql, maNH);
    }

    @Override
    public List<NguoiHoc> selectAll() {
        String sql = "SELECT * FROM NguoiHoc";
        return selectBySql(sql);
    }

    @Override
    public NguoiHoc selectById(String maNH) {
        String sql = "SELECT * FROM NguoiHoc WHERE MaNH = ?";
        List<NguoiHoc> list = selectBySql(sql, maNH);
        return !list.isEmpty() ? list.get(0) : null;
    }

    @Override
    protected List<NguoiHoc> selectBySql(String sql, Object... args) {
        List<NguoiHoc> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    NguoiHoc entity = new NguoiHoc();
                    entity.setMaNH(rs.getString("MaNH"));
                    entity.setHoTen(rs.getString("HoTen"));
                    entity.setNgaySinh(rs.getDate("NgaySinh"));
                    entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                    entity.setDienThoai(rs.getString("DienThoai"));
                    entity.setEmail(rs.getString("Email"));
                    entity.setGhiChu(rs.getString("GhiChu"));
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setNgayDK(rs.getDate("NgayDK"));
                    list.add(entity);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public List<NguoiHoc> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM NguoiHoc WHERE HoTen LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%");
    }

    public List<NguoiHoc> selectNotInCourse(int maKH, String keyword) {
        String sql = "SELECT * FROM NguoiHoc "
                + " WHERE HoTen LIKE ? AND "
                + " MaNH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH = ?)";
        return this.selectBySql(sql, "%" + keyword + "%", maKH);
    }

    @Override
    public List<NguoiHoc> selectSqlJTable() {
        return null;
    }

    @Override
    protected List<NguoiHoc> listJTable(String sql, Object... args) {
        return null;
    }
}
