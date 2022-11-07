package edusys.dao;

import java.util.List;

/**
 *
 * @author Manh Hung
 */
abstract public class EduSysDAO<EntityType, KeyType> {
    abstract public void insert(EntityType entity);
    abstract public void update(EntityType entity);
    abstract public void delete(KeyType id);
    abstract public EntityType selectById(KeyType id);
    abstract public List<EntityType> selectAll();
    abstract protected List<EntityType> selectBySql(String sql, Object...args);
    
    abstract public List<EntityType> selectSqlJTable();
    abstract protected List<EntityType> listJTable(String sql, Object...args);
}
