package edusys.dao;

import edusys.entity.ChuyenDe;
import edusys.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manh Hung
 */
public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String> {

    @Override
    public void insert(ChuyenDe model) {
        String sql = "INSERT INTO ChuyenDe (MaCD, TenCD, HocPhi, ThoiLuong,"
                + " Hinh, MoTa) VALUES (?, ?, ?, ?, ?, ?)";
        XJdbc.update(sql,
                model.getMaCD(),
                model.getTenCD(),
                model.getHocPhi(),
                model.getThoiLuong(),
                model.getHinh(),
                model.getMoTa());
    }

    @Override
    public void update(ChuyenDe model) {
        String sql = "UPDATE ChuyenDe SET TenCD = ?, HocPhi = ?, ThoiLuong = ?,"
                + " Hinh = ?, MoTa = ? WHERE MaCD = ?";
        XJdbc.update(sql,
                model.getTenCD(),
                model.getHocPhi(),
                model.getThoiLuong(),
                model.getHinh(),
                model.getMoTa(),
                model.getMaCD());
    }

    @Override
    public void delete(String maCD) {
        String sql = "DELETE FROM ChuyenDe WHERE MaCD = ?";
        XJdbc.update(sql, maCD);
    }

    @Override
    public List<ChuyenDe> selectAll() {
        String sql = "SELECT * FROM ChuyenDe";
        return selectBySql(sql);
    }

    @Override
    public ChuyenDe selectById(String maCD) {
        String sql = "SELECT * FROM ChuyenDe WHERE MaCD=?";
        List<ChuyenDe> list = selectBySql(sql, maCD);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    protected List<ChuyenDe> selectBySql(String sql, Object... args) {
        List<ChuyenDe> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    ChuyenDe entity = new ChuyenDe();
                    entity.setMaCD(rs.getString("MaCD"));
                    entity.setHinh(rs.getString("Hinh"));
                    entity.setHocPhi(rs.getDouble("HocPhi"));
                    entity.setMoTa(rs.getString("MoTa"));
                    entity.setTenCD(rs.getString("TenCD"));
                    entity.setThoiLuong(rs.getInt("ThoiLuong"));
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

    @Override
    public List<ChuyenDe> selectSqlJTable() {
        String sql = "SELECT cd.MaCD, cd.TenCD, cd.HocPhi, cd.ThoiLuong, cd.MoTa FROM ChuyenDe cd";
        return listJTable(sql);
    }

    @Override
    protected List<ChuyenDe> listJTable(String sql, Object... args) {
        List<ChuyenDe> listTableChuyenDe = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    ChuyenDe entity = new ChuyenDe();
                    entity.setMaCD(rs.getString("MaCD"));
                    entity.setTenCD(rs.getString("TenCD"));
                    entity.setHocPhi(rs.getDouble("HocPhi"));
                    entity.setThoiLuong(rs.getInt("ThoiLuong"));
                    entity.setMoTa(rs.getString("MoTa"));
                    listTableChuyenDe.add(entity);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return listTableChuyenDe;
    }

}
