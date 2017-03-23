package cn.com.dao;

import java.util.List;
import java.util.Map;

public interface GenericDao<T> {

    public List<T> get();

    public int update(Map<String, Object> values, Map<String, Object> updateWhere);

    public int update(String sql);

    public boolean deleteByWhere(String deleteWhere);

    public boolean deleteByWhere(Map<String, Object> map);

    public boolean delete(String deleteWhere);

    public boolean save(T t) throws InstantiationException, IllegalAccessException;

    public boolean insert(String sql);

    public boolean insert(Map<String, Object> map);

    public List<T> getByWhere(String selectWhere);

    public List<T> select(String sql);

    public List<T> getByWhere(Map<String, Object> map);
}
