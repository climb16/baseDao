<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/easyui/demo.css">
    <script type="text/javascript" src="<%=path%>/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script language="JavaScript" src="<%=path%>/zTree/js/jquery.ztree.all-3.5.min.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=path%>/zTree/css/demo.css">
    <link type="text/css" rel="stylesheet" href="<%=path%>/zTree/css/zTreeStyle/zTreeStyle.css">
    <script language="javascript" type="text/javascript" src="<%=path%>/js/base.js"></script>
    <style type="text/css">
        div.index-top{
            padding-left:6px;
            line-height: 60px;
            color: #fff;
            font-size: 22px;
            font-style: italic;
        }
    </style>
    <SCRIPT type="text/javascript">
        var setting = {
            edit: {enable: true,showRemoveBtn: false, showRenameBtn: false},
            view: {selectedMulti: false,dblClickExpand:false },
            data: {simpleData: {enable: true, idKey: "id",pIdKey: "pid",rootPId: 0}},
            callback: {
                beforeClick:beforeClick,
                onClick:onClick
            }
        };
        var zNodes;
        var zTree = $.fn.zTree.getZTreeObj("menu");
        function beforeClick(treeId, treeNode, clickFlag) {
            if (!treeNode.isParent) {
                return true;
            }
            return false;
        }
        function onClick(event, treeId, treeNode){
            addTab(treeNode.id,treeNode.name, treeNode.url);
        }
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
        $(document).ready(function(){
            $.fn.zTree.init($("#menu"), setting, zNodes);
        });
    </SCRIPT>
</head>
<body class="easyui-layout" scroll="no" style="background: #D2E0F2">
<div region="north" split="false" style="height: 60px; background: #D2E0F2; margin-bottom: 6px; ">
    <div class="index-top">欢迎使用</div>
</div>
<div region="south" style="height: 40px; background: #D2E0F2; margin-top: 6px">
    <p style="text-align: center; font-weight: bold; font-size: 14px">
        HR ver 1.0
    </p>
    <p style="text-align: right; font-size: 10px">
       author:肖&nbsp;&nbsp;&nbsp;date:2014-01-01 北京
    </p>
</div>
<div data-options="region:'west',collapsible:false, iconCls:'icon-redo'" title="导航菜单" style="width: 220px;">
    <div scroll="no" style="margin-left: 10px;">
        <ul id="menu" class="ztree"></ul>
    </div>
</div>
<div data-options="region:'center'" id="mainPanle" style="background: #eee; margin-left: 6px;">
    <div id="tabs" class="easyui-tabs" fit="true" border="0">
        <div title="欢迎页" style="padding:40px;"></div>
    </div>
</div>
</body>
</html>