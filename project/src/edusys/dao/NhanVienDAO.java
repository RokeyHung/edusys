package edusys.dao;

import edusys.entity.NhanVien;
import edusys.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manh Hung
 */
public class NhanVienDAO extends EduSysDAO<NhanVien, String> {

    @Override
    public void insert(NhanVien model) {
        String sql = "INSERT INTO NhanVien (MaNV, MatKhau, HoTen, VaiTro) VALUES (?, ?, ?, ?, ?)";
        XJdbc.update(sql,
                model.getMaNV(),
                model.getMatKhau(),
                model.getHoTen(),
                model.isVaiTro()
        );
    }

    @Override
    public void update(NhanVien model) {
        String sql = "UPDATE NhanVien SET MatKhau = ?, HoTen = ?, VaiTro = ? WHERE MaNV = ?";
        XJdbc.update(sql,
                model.getMatKhau(),
                model.getHoTen(),
                model.isVaiTro(),
                model.getMaNV());
    }

    @Override
    public void delete(String maNV) {
        String sql = "DELETE FROM NhanVien WHERE MaNV = ?";
        XJdbc.update(sql, maNV);
    }

    @Override
    public List<NhanVien> selectAll() {
        String sql = "SELECT * FROM NhanVien";
        return selectBySql(sql);
    }

    // Tìm kiếm nhân viên
    @Override
    public NhanVien selectById(String maNV) {
        String sql = "SELECT * FROM NhanVien WHERE MaNV = ?";
        List<NhanVien> list = this.selectBySql(sql, maNV);
        return list.size() > 0 ? list.get(0) : null;
    }

    // duyệt danh sách lên table
    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    NhanVien entity = new NhanVien();
                    entity.setMaNV(rs.getString(1));
                    entity.setMatKhau(rs.getString(2));
                    entity.setHoTen(rs.getString(3));
                    entity.setVaiTro(rs.getBoolean(4));
                    list.add(entity);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    @Override
    public List<NhanVien> selectSqlJTable() {
        String sql = "SELECT nv.MaNV, nv.HoTen, nv.VaiTro FROM NhanVien nv";
        return listJTable(sql);
    }

    @Override
    protected List<NhanVien> listJTable(String sql, Object... args) {
        List<NhanVien> listTableNhanVien = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    NhanVien entity = new NhanVien();
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setHoTen(rs.getString("HoTen"));
                    entity.setVaiTro(rs.getBoolean("VaiTro"));
                    listTableNhanVien.add(entity);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return listTableNhanVien;
    }
}
