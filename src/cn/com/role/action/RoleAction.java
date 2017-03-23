package cn.com.role.action;

import cn.com.controller.Action;
import cn.com.role.model.Role;
import cn.com.role.service.RoleService;
import cn.com.sys.model.Menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;

public class RoleAction extends Action{
    RoleService roleService = new RoleService();

    //获取角色列表
    public String getRoleList(HttpServletRequest req, HttpServletResponse resp){
        List<Role> roles = roleService.getRoles();
        req.setAttribute("roles", roles);
        return "list";
    }

    //添加角色
    public void addRole(HttpServletRequest req, HttpServletResponse resp){
        Role role = new Role();
        role.setRName(req.getParameter("rName"));
        role.setState(1);
        role.setUpdateTime(new Date());
        role.setDepict(req.getParameter("depict"));
        roleService.addRole(role);
    }

    //修改角色初始化
    public String updateInit(HttpServletRequest req, HttpServletResponse resp){
        Role role = new Role();
        String rId = req.getParameter("roleId");
        if(!"".equals(rId) && rId != null){
            role = roleService.getRoleById(Integer.parseInt(rId));
        }
        req.setAttribute("role", role);
        return "edit_init";
    }

    //修改角色信息
    public void updateRole(HttpServletRequest req, HttpServletResponse resp){
        Map values = new HashMap();
        Map updateWhere = new HashMap();
        values.put("rName", req.getParameter("rName"));
        values.put("state", 1);
        values.put("updateTime", new Date());
        values.put("depict", req.getParameter("depict"));
        updateWhere.put("rId", Integer.parseInt(req.getParameter("rId")));
        roleService.updateRole(values, updateWhere);
    }

    //删除角色
    public void deleteMenuById(HttpServletRequest req, HttpServletResponse resp){
        int flag = roleService.deleteRoleById(Integer.parseInt(req.getParameter("rId")));
        try {
            resp.getWriter().print(flag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
