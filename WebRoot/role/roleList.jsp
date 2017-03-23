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
    <script type="text/javascript" src="<%=path%>/js/base.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/menu.css">

</head>
<body style="margin: 2px; padding: 0">
<div id="tools" style="padding:5px;height:auto">
    <span style="margin-bottom:5px;" >
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
        <a href="javascript:void(0);"
           onclick="javascript:$.messager.confirm(
           ' ', '您确认要删除该角色吗？', function(r){if(r){dels()}});"
           class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
    </span>
</div>
<div>
    <table id="dg" title="角色列表" toolbar="#tools" data-options="iconCls:'icon-save',
    frozenColumns: [[{ field: 'sel', checkbox: true }]],
    columns: [[{ field: 'r_id', title: '角色编号', width: 80, halign:'center',align:'center'},
               { field: 'name', title: '角色名称', width:100, halign:'center'},
               { field: 'pid', title: '更新日期', width:160, halign:'center',align:'center'} ,
               { field: 'type', title: '角色状态', width:80, halign:'center',align:'center'},
               { field: 'url', title: '角色描述', width:350, halign:'center'},
               { field: 'oper', title: '操作', width:300, halign:'center'}]],
    pagination: true, rowNumbers: true, singleSelect:true, selectOnCheck:false, checkOnSelect: false" class="easyui-datagrid">
        <tbody>
        <c:forEach var="role" items="${requestScope.roles}">
            <tr>
                <td><input type="checkbox" name="sel" id="sel" value="${role.getRId()}"/></td>
                <td>${role.getRId()}</td>
                <td>${role.getRName()}</td>
                <td>${role.getUpdateTime()}</td>
                <td>
                    <c:if test="${role.getState() == 1}">有效</c:if>
                    <c:if test="${role.getState() == -1}">失效</c:if>
                </td>
                <td>${role.getDepict()}</td>
                <td>
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <span  onclick="add();"><img src="<%=path%>/images/add.gif"/>&nbsp;添加</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <span  onclick="edit('${role.getRId()}');"><img src="<%=path%>/images/edit.gif"/>&nbsp;更新</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <span  onclick="javascript:$.messager.confirm(' ', '您确认要删除该角色吗？', function(r){if(r){del('${role.getRId()}')}});"><img src="<%=path%>/images/del.gif" />&nbsp;删除</span>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <script language="JavaScript">
        function add(){
              createWindow("add", "添加角色","icon-add","../role/addRole.jsp", 280, 200);
        }
        function edit(roleId){
              createWindow("add","编辑角色","icon-edit","role_updateInit.do?roleId="+roleId,340,280);
        }
        function del(roleId){
            $.ajax({
                type:"post",
                url:"role_deleteRoleById.do?rId="+roleId,
                success:function(msg){
                    if(msg == 0) $.messager.alert(' ','您要删除的角色不存在！','info');
                    if(msg == 2) $.messager.alert(' ', '删除成功！','info');
                }
            });
        }
        function dels(){
            var checkedItems = $('#dg').datagrid('getChecked');
            var names = [];
            $.each(checkedItems, function(index, item){
                names.push(item.sel);
                alert(names[1]);
            });
        }
    </script>
</div>
<!--添加部分-->
<div>
    <div id="add"></div>
</div>
</body>
</html>