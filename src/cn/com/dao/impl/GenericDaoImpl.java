package cn.com.dao.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.dao.GenericDao;
import cn.com.db.DBConnection;
import cn.com.util.Util;
public class GenericDaoImpl<T> implements GenericDao<T>{
    private Class<?> clazz;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    //构造函数，初始化类，获取泛型对象实例
    public GenericDaoImpl(){
        Type type = getClass().getGenericSuperclass();
        if(!(type instanceof ParameterizedType)){
            clazz = Object.class;
        }else{
            Type[] parameters = ((ParameterizedType)type).getActualTypeArguments();
            if(!(parameters[0] instanceof Class)){
                clazz = Object.class;
            }else{
                clazz = (Class<?>)parameters[0];
            }
        }
    }
    //获取实体类对应的表
    private String getTableName(){
        String tableName = clazz.getName().toString();
        return Util.stringConvert(tableName.substring(tableName.lastIndexOf(".")+1));
    }
    //构造实体属性构造get和set
    private PropertyDescriptor getPropertyDescriptor(String fieldName) {
        PropertyDescriptor pd = null;
        try {
             pd = new PropertyDescriptor(fieldName, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pd;
    }
    //获取实体类所有声明的属性
    private Field[] getFileds(){
        return clazz.getDeclaredFields();
    }
    //将表转换成对象
    private T tableToObject(ResultSet rs){
        T t = null;
        Field[] fields = getFileds();
        try{
            t = (T) clazz.newInstance();
            for (Field field : fields){
                 String property = Util.stringConvert(field.getName());
                 PropertyDescriptor pd = getPropertyDescriptor(field.getName());
                 if(Util.isNuN(property)){
                    pd.getWriteMethod().invoke(t, new Object[]{rs.getObject(property)});
                 }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return t;
    }
    private List<T> tableToList(ResultSet rs){
        List<T> list = new ArrayList<T>();
        try{
            while(rs.next()){
                list.add(tableToObject(rs));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public List<T> select(String sql){
        List<T> list = new ArrayList<T>();
        try{
            conn = DBConnection.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            list = tableToList(rs);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            DBConnection.close(rs, ps, conn);
        }
        return list;
    }
    @Override
    public List<T> getByWhere(String selectWhere) {
        String sql = "select * from " + getTableName() + " where 1=1"+selectWhere;
        return select(sql);
    }
    @Override
    public List<T> getByWhere(Map<String, Object> map) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("select * from " + getTableName() + " where 1=1");
        for(Map.Entry<String, Object> entity : map.entrySet()){
            buffer.append(" and "+Util.stringConvert(entity.getKey())+"="+entity.getValue());
        }
        return select(buffer.toString());
    }
    @Override
    public List<T> get() {
        return getByWhere("");
    }
    @Override
    public boolean deleteByWhere(String deleteWhere) {
        String sql = "delete from " + getTableName()+" where 1=1" + " and "+deleteWhere;
        return delete(sql);
    }
    @Override
    public boolean deleteByWhere(Map<String, Object> map) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("delete from " + getTableName()+" where 1=1");
        for(Map.Entry<String, Object> entity : map.entrySet()){
            buffer.append(" and "+Util.stringConvert(entity.getKey())+"="+entity.getValue());
        }
        return delete(buffer.toString());
    }
    @Override
    public boolean delete(String sql){
        Boolean state = true;
        try{
            conn = DBConnection.getConn();
            ps = conn.prepareStatement(sql);
            state = ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            DBConnection.close(ps, conn);
        }
        if(state == true){
            state = false;
        }else{
            state = true;
        }
        return state;
    }

    //将实体对象转换成map
    private Map<String, Object> ObjectToMap(T entity){
        Map<String, Object> map = new HashMap<String, Object>();
        for(Field field : getFileds()){
            try{
                PropertyDescriptor pd = getPropertyDescriptor(field.getName());
                Object value = pd.getReadMethod().invoke(entity);
                if(value != null && !"".equals(value)){
                    map.put(Util.stringConvert(field.getName()),value);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return map;
    }
    //拼接插入sql
    private Map<String, List> insertSql(Map<String, Object> map){
        Map<String, List> sqlMaps = new HashMap<String, List>();
        StringBuffer sql = new StringBuffer();
        StringBuffer properties = new StringBuffer();
        StringBuffer placeholders = new StringBuffer();
        List values = new ArrayList();
        sql.append("insert into ").append(getTableName());
        for(Map.Entry<String, Object> entity : map.entrySet()){
            properties.append(Util.stringConvert(entity.getKey())).append(",");
            placeholders.append("?").append(",");
            values.add(entity.getValue());
        }
        String prop = properties.toString();
        String holders = placeholders.toString();
        if (",".equals(prop.substring(prop.length() - 1))){
            prop = prop.substring(0, prop.length()-1);
        }
        if (",".equals(holders.substring(holders.length()-1))){
            holders = holders.substring(0, holders.length()-1);
        }
        sql.append("(").append(prop).append(")").append(" values(").append(holders).append(")");
        sqlMaps.put(sql.toString(),values);
        return sqlMaps;
    }
    //插入
    private boolean insert(String sql, List values){
        boolean state = true;
        try{
            conn = DBConnection.getConn();
            ps = conn.prepareStatement(sql);
            if(!values.isEmpty()){
                for (int i = 0; i<values.size(); i++){
                    ps.setObject(i+1, values.get(i));
                }
            }
            state = ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            DBConnection.close(ps, conn);
        }
        if(state == true){
            state = false;
        }else{
            state = true;
        }
        return state;
    }

    //插入
    @Override
    public boolean insert(String sql){
        return insert(sql,null);
    }
    @Override
    public boolean insert(Map<String, Object> map){
        String sql = null;
        List values = null;
        Map<String, List> sqlMaps = insertSql(map);
        for (Map.Entry<String, List> sqlMap : sqlMaps.entrySet()){
            sql = sqlMap.getKey();
            values = sqlMap.getValue();
        }
        return insert(sql,values);
    }
    @Override
    public boolean save(T t){
        String sql = "";
        List values = new ArrayList();
        Map<String, List> sqlMaps = insertSql(ObjectToMap(t));
        for (Map.Entry<String, List> sqlMap : sqlMaps.entrySet()){
            sql = sqlMap.getKey();
            values = sqlMap.getValue();
        }
        return insert(sql,values);
    }

    /**
     * 拼接update语句.
     * @param values        要更新的字段map.
     * @param updateWhere   更新条件map.
     * @return
     */
    private String updateSql(Map<String, Object> values, Map<String, Object> updateWhere){
        String sql = "";
        StringBuffer value = new StringBuffer();
        StringBuffer where = new StringBuffer();
        sql="update "+getTableName()+" set ";
        for(Map.Entry<String, Object> entity : values.entrySet()){
            value.append(Util.stringConvert(entity.getKey())).append("=").append(entity.getValue()).append(",");
        }
        for(Map.Entry<String, Object> entity : updateWhere.entrySet()){
            where.append(Util.stringConvert(entity.getKey())).append("=").append(entity.getValue()).append(" and ");
        }
        String prop = value.toString().trim();
        String wh = where.toString().trim();
        if (",".equals(prop.substring(prop.length()-1))){
            prop = prop.substring(0, prop.length()-1);
        }
        if ("and".equals(wh.substring(wh.length()-3))){
            wh = wh.substring(0, wh.length()-4);
        }
        sql = sql + prop+" where "+wh;
        return sql;
    }
    @Override
    public int update(Map<String, Object> values, Map<String, Object> updateWhere){
        return update(updateSql(values, updateWhere));
    }
    @Override
    public int update(String sql){
        int state = 0;
        conn = DBConnection.getConn();
        try{
            ps = conn.prepareStatement(sql);
            state = ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return state;
    }

}
