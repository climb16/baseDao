package cn.com.user.action;

import cn.com.controller.Action;
import cn.com.poi.excel.Excel;
import cn.com.sys.model.Menu;
import cn.com.sys.service.MenuService;
import cn.com.user.model.User;
import cn.com.user.service.UserService;
import cn.com.util.Util;
import net.sf.json.JSONArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAction extends Action{

    UserService userService = new UserService();
    MenuService menuService = new MenuService();
    Excel excel = new Excel();
    //登录
    public String login(HttpServletRequest req, HttpServletResponse resp){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uName", "'"+req.getParameter("name")+"'");
        map.put("uPass", "'"+req.getParameter("pass")+"'");
        User u = userService.getUserByNameAndPassword(map);
        if(u != null){
            req.getSession().setAttribute("u", u);
            return "index";
        }
        return "login";
    }

    //首页面
    public void init(HttpServletRequest req, HttpServletResponse resp){
        List<Menu> menus = menuService.getMenusByUid(((User)req.getSession().getAttribute("u")).getUId());
        JSONArray jsonArray = JSONArray.fromObject(menus);
        try {
            resp.getWriter().print(jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //获取用户列表
    public String getUserList(HttpServletRequest req, HttpServletResponse resp){
        req.setAttribute("users", userService.getUsers());
        return "user_list";
    }
    //导出
    public void exp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = "user" + Util.dateFormat("yyyyMMddhhmmss")+".xls";
        PrintWriter out = resp.getWriter();
        List<User> users = userService.getUsers();
        try{
            String[] title = new String[]{"用户编号","用户名称","用户密码","用户状态","角色编号"};
            excel.excelExport(users, title, path);
            out.println(1);
        }catch (Exception e){
            out.println(2);
        }
        out.flush();
        out.close();
    }
    //导入
    public void imp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fileName = req.getParameter("excel");
        PrintWriter out = resp.getWriter();

        try{
            out.println(1);
        }catch (Exception e){
            out.println(2);
        }
        out.flush();
        out.close();
    }
}
