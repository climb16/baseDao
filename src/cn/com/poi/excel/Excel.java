package cn.com.poi.excel;

import cn.com.user.dao.UserDao;
import cn.com.user.model.User;
import cn.com.util.Util;
import cn.com.util.type.ClassType;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.DataFormat;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Excel {

    private static String OUTPATH = "E:\\base"; //输出路径

    /**
     * Excel导出
     * @param table 要导出的数据表
     * @param title 数据标题
     * @param fileName 文件名称
     */
    public void excelExport(List<?> table, String[] title, String fileName){
        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet();
        createTitle(sheet, title);
        createContext(sheet, table);
        try {
            String tem_path =  OUTPATH + "\\" + fileName;
            File file = new File(tem_path);
            File parent = file.getParentFile();
            if(null != parent && !parent.exists()){
                parent.mkdirs();
            }
            if(file.exists()){
                file.delete();
            }
            FileOutputStream stream = new FileOutputStream(OUTPATH + "\\" + fileName);
            book.write(stream);
            stream.flush();
            stream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //创建表格标题
    public void createTitle(HSSFSheet sheet, String[] title){
        //设置标题
        HSSFRow row = sheet.createRow(0);
        for(int i = 0; i < title.length; i++){
            HSSFCell cell = row.createCell(i);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(Util.objToString(Util.isNull(title[i])));
        }
    }
    //创建表格体
    public void createContext(HSSFSheet sheet, List<?> table){
        if(!table.isEmpty()){
            for(int i = 0; i < table.size(); i++){
                Object obj = table.get(i);
                Class clazz = obj.getClass();
                Field[] fields = clazz.getDeclaredFields();
                HSSFRow rows = sheet.createRow(i+1);
                for(int j = 0; j < fields.length; j++){
                    HSSFCell cell = rows.createCell(j);
                    try {
                        PropertyDescriptor pd = new PropertyDescriptor(fields[j].getName(), clazz);
                        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                        cell.setCellValue(Util.objToString(pd.getReadMethod().invoke(obj)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public List<?> excelImport(Class bean, String filePath) throws NumberFormatException, ParseException{
        List<Object> list = new ArrayList<>();
        try {
            HSSFWorkbook book = new HSSFWorkbook(new FileInputStream(filePath));
            HSSFSheet sheet = book.getSheetAt(0);
            int rows = sheet.getLastRowNum();
            for(int i = 1; i <= rows; i++){
                HSSFRow row = sheet.getRow(i);
                Field[] fields = bean.getDeclaredFields();
                Object obj = bean.newInstance();
                for(int j = 0; j <= fields.length; j++){
                    PropertyDescriptor pd = new PropertyDescriptor(fields[j].getName(), bean);
                    String value = row.getCell(j).getStringCellValue();
                    Object o = null;
                    Method method = pd.getWriteMethod();
                    Type type = method.getParameterTypes()[0];
                    if(Util.isNuN(value)){
                        if(Util.isNuN(o)){
                            o = ClassType.typeConvert(type, value);
                            method.invoke(obj, new Object[]{o});
                        }
                    }
                }
            list.add(obj);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public static void main(String[] args) throws ParseException {
        UserDao dao = new UserDao();
        /*List<User> roles = dao.get();*/
        Excel excel = new Excel();
       /* String[] title = new String[]{"功能节点","功能名称","父节点编号","资源路径","节点类型"};
        excel.excelExport(roles, title, "1.xls");*/

        List list = excel.excelImport(User.class, "E:\\base\\user20141125020709.xls");

    }


}
