<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/easyui/demo.css">
    <script type="text/javascript" src="<%=path%>/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=path%>/js/menu.js"></script>
    <script type="text/javascript" src="<%=path%>/js/base.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/menu.css">
    <style>
        .tools{
            padding: 6px;
            color: #0000ff;
        }
        .tools:hover{
            padding: 6px;
            color: #0000ff;
            cursor: pointer;
        }
    </style>
</head>
<body style="margin: 2px; padding: 0">
<div id="tools">
<span style="margin-bottom:5px;" >
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
        <a href="javascript:void(0);"
           onclick="javascript:$.messager.confirm(
           ' ', '您确认要删除该角色吗？', function(r){if(r){dels()}});"
           class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
    </span>
    <span style="margin-left: 760px;">
        <span class="tools" onclick="imp();"><img src="<%=path%>/images/import.gif">&nbsp;导入</span><span class="tools" onclick="exp();"><img src="<%=path%>/images/export.gif">&nbsp;导出</span>
    </span>
    <script type="text/javascript">
        function exp(){
            $.ajax({
                type:'post',
                url:'user_exp.do',
                success:function(data){
                    if(data == 1) $.messager.alert("导出","导出成功!","info");
                    if(data == -1) $.messager.alert("导出","导出失败!","warning");
                }
            });
        }
        function imp(){
            createWindow('add', '导入','','sys/impUser.jsp', 400, 150);
        }
</script>
</div>
<div>
    <table title="用户列表" toolbar="#tools" data-options="iconCls:'icon-save',
    frozenColumns: [[{ field: 'select', checkbox: true }]],
    columns: [[{ field: 'uid', title: '用户编号', width: 80, halign:'center',align:'center'},
               { field: 'uname', title: '登录名称', width:200, halign:'center'},
               { field: 'upass', title: '登录密码', width:200, halign:'center',align:'center'} ,
               { field: 'state', title: '用户状态', width:80, halign:'center',align:'center'},
               { field: 'rid', title: '所属角色', width:160, halign:'center'},
               { field: 'oper', title: '操作', width:320, halign:'center'}]],
    pagination: true, rownumbers: true, singleSelect:true, selectOnCheck:false, checkOnSelect: false" class="easyui-datagrid">
        <tbody>
        <c:forEach var="user" items="${requestScope.users}">
            <tr>
                <td><input type="checkbox" name="sel" id="sel"/></td>
                <td>${user.getUId()}</td>
                <td>${user.getUName()}</td>
                <td>${user.getUPass()}</td>
                <td>
                    <c:if test="${user.getState() == 0}">禁用</c:if>
                    <c:if test="${user.getState() == 1}">可用</c:if>
                </td>
                <td>${user.getRId()}</td>
                <td>
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <span  onclick="add('${user.getUId()}');"><img src="<%=path%>/images/add.gif"/>&nbsp;添加</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <span  onclick="edit('${user.getUId()}');"><img src="<%=path%>/images/edit.gif"/>&nbsp;更新</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <span  onclick="javascript:$.messager.confirm(' ', '您确认要删除该节点吗？', function(r){if(r){del('${user.getUId()}')}});"><img src="<%=path%>/images/del.gif" />&nbsp;删除</span>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<!--添加部分-->
<div>
    <div id="add"></div>
</div>
</body>
</html>