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

</head>
<body style="margin: 2px; padding: 0">
<div>
    <table title="功能表格" data-options="iconCls:'icon-save',
    frozenColumns: [[{ field: 'select', checkbox: true }]],
    columns: [[{ field: 'id', title: '节点编号', width: 60, halign:'center',align:'center'},
               { field: 'name', title: '节点名称', width:140, halign:'center'},
               { field: 'oder', title: '节点顺序', width:60, halign:'center',align:'center'},
               { field: 'pid', title: '父节点', width:60, halign:'center',align:'center'} ,
               { field: 'type', title: '节点类型', width:80, halign:'center',align:'center'},
               { field: 'url', title: '资源路径', width:250, halign:'center'},
               { field: 'oper', title: '操作', width:280, halign:'center'}]],
    pagination: true, rownumbers: true, singleSelect:true, selectOnCheck:false, checkOnSelect: false" class="easyui-datagrid">
        <tbody>
       <c:forEach var="menu" items="${requestScope.menus}">
        <tr>
            <td><input type="checkbox" name="sel" id="sel"/></td>
            <td>${menu.getId()}</td>
            <td>${menu.getName()}</td>
            <td>${menu.getOder()}</td>
            <td>${menu.getPid()}</td>
            <td>
                <c:if test="${menu.getType() == 1}">结构</c:if>
                <c:if test="${menu.getType() == 2}">功能</c:if>
            </td>
            <td>${menu.getUrl()}</td>
            <td>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <span  onclick="add('${menu.getId()}');"><img src="<%=path%>/images/add.gif"/>&nbsp;添加</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span  onclick="edit('${menu.getId()}');"><img src="<%=path%>/images/edit.gif"/>&nbsp;更新</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span  onclick="javascript:$.messager.confirm(' ', '您确认要删除该节点吗？', function(r){if(r){del('${menu.getId()}')}});"><img src="<%=path%>/images/del.gif" />&nbsp;删除</span>
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