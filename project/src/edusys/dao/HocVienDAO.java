package edusys.dao;

import edusys.entity.HocVien;
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
public class HocVienDAO extends EduSysDAO<HocVien, Integer> {

    @Override
    public void insert(HocVien model) {
        String sql = "INSERT INTO HocVien(MaKH, MaNH, Diem) VALUES(?, ?, ?)";
        XJdbc.update(sql,
                model.getMaKH(),
                model.getMaNH(),
                model.getDiem());
    }

    @Override
    public void update(HocVien model) {
        String sql = "UPDATE HocVien SET MaKH = ?, MaNH = ?, Diem = ? WHERE MaHV = ?";
        XJdbc.update(sql,
                model.getMaKH(),
                model.getMaNH(),
                model.getDiem(),
                model.getMaHV());
    }

    @Override
    public void delete(Integer maHV) {
        String sql = "DELETE FROM HocVien WHERE MaHV=?";
        XJdbc.update(sql, maHV);
    }

    @Override
    public HocVien selectById(Integer maHV) {
        String sql = "SELECT * FROM HocVien WHERE MaHV = ?";
        List<HocVien> list = selectBySql(sql, maHV);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<HocVien> selectAll() {
        String sql = "SELECT * FROM HocVien";
        return selectBySql(sql);
    }

    @Override
    protected List<HocVien> selectBySql(String sql, Object... args) {
        List<HocVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    HocVien entity = new HocVien();
                    entity.setMaHV(rs.getInt("MaHV"));
                    entity.setMaKH(rs.getInt("MaKH"));
                    entity.setMaNH(rs.getString("MaNH"));
                    entity.setDiem(rs.getDouble("Diem"));
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

    public List<HocVien> selectByKhoaHoc(int maKH) {
        String sql = "SELECT * FROM HocVien WHERE MaKH=?";
        return this.selectBySql(sql, maKH);
    }

    @Override
    public List<HocVien> selectSqlJTable() {
        return null;
    }

    @Override
    protected List<HocVien> listJTable(String sql, Object... args) {
        return null;
    }
}
