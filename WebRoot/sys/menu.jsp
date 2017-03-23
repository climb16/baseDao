<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/easyui/demo.css">
    <script type="text/javascript" src="<%=path%>/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script language="JavaScript" src="<%=path%>/zTree/js/jquery.ztree.all-3.5.min.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=path%>/zTree/css/demo.css">
    <link type="text/css" rel="stylesheet" href="<%=path%>/zTree/css/zTreeStyle/zTreeStyle.css">
    <link type="text/css" rel="stylesheet" href="<%=path%>/css/menu.css">
    <script language="javascript" type="text/javascript" src="<%=path%>/js/base.js"></script>
    <script language="javascript" type="text/javascript" src="<%=path%>/js/menu.js"></script>

    <SCRIPT type="text/javascript">
        var zNodes;
        var setting = {
            edit: {enable: true,showRemoveBtn: false, showRenameBtn: false},
            view: {selectedMulti: false, dblClickExpand: false },
            data: {simpleData: {enable: true, idKey: "id",pIdKey: "pid",rootPId: 0}},
            callback: {
                onRightClick:onRightClick,
                onClick:onClick
            }
        };
        $(document).ready(function(){
            $.ajax({
                type: "post",
                url: "user_init.do",
                async:false,
                dataType: "json",
                success: function(data){
                    zNodes = eval(data);
                }
            });
        });
        function onRightClick(event, treeId, treeNode) {
            if(treeNode){
                zTree.selectNode(treeNode);
                showRMenu( event.clientX+5, event.clientY-12);
            }
        }
        function onClick(event, treeId, treeNode){
            showMenuTble(treeNode);
        }
        $(document).ready(function(){
            $.fn.zTree.init($("#sys"), setting, zNodes);
            zTree = $.fn.zTree.getZTreeObj("sys");
            rMenu = $("#rMenu");
        });
    </SCRIPT>
</head>
<body class="easyui-layout" data-options="fit:true" style="background: #D2E0F2">
    <div data-options="region:'west'" style="width: 180px; border: 0">
       <div class="easyui-layout" data-options="fit:true" style="border: 0">
           <div data-options="region:'north'" class="menu-top">系统功能树</div>
           <div data-options="region:'center'" class="menu-center">
               <ul id="sys" class="ztree"></ul>
               <div id="rMenu">
                   <div onclick="menuOnclick('add')"><img src="<%=path%>/images/add.gif"/>&nbsp;&nbsp;&nbsp;添加</div>
                   <div class="menu-sep1"></div>
                   <div onclick="menuOnclick('edt')"><img src="<%=path%>/images/edit.gif"/>&nbsp;&nbsp;&nbsp;编辑</div>
                   <div class="menu-sep1"></div>
                   <div onclick="menuOnclick('del')"><img src="<%=path%>/images/del.gif" />&nbsp;&nbsp;&nbsp;删除</div>
               </div>
           </div>
       </div>
    </div>
    <div data-options="region:'center'" style="margin-left: 6px; border-top: 0; border-bottom: 0">
        <div id="main" class="easyui-panel" data-options="fit:true,border:false">

        </div>
        <div id="add"></div>
    </div>
</body>
</html>