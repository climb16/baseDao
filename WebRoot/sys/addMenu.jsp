<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
     <div id="title" style="margin-top: 16px;"></div>
     <div style="padding:8px; margin-left: 10px;">
         <form id="form" action="menu_addMenu.do" method="post">
             <table border="0">
                 <tr>
                    <td height="36px" style="text-align:right"><label>节点名称:</label></td>
                    <td ><input class="easyui-textbox" type="text" name="name" /></td>
                 </tr>
                 <tr>
                     <td style="text-align:right"><label>节点顺序:</label></td>
                     <td ><input class="easyui-textbox" type="text" name="oder" /></td>
                 </tr>
                 <tr >
                    <td height="36px" style="text-align:right"><label>父节点编号:</label></td>
                    <td><input class="easyui-textbox" readonly type="text" name="pid" value="${requestScope.menu.getId()}" /></td>
                 </tr>
                 <tr>
                    <td height="36px" style="text-align:right"><label>节点类型:</label></td>
                    <td><select id="sel"  class="easyui-combobox" panelHeight="auto" name="type" style="width:130px;">
                        <option value=1 >节点</option>
                        <option value=2 >功能</option>
                    </select></td>
                 </tr>
                 <tr id="sys">
                     <td height="36px" style="text-align:right" ><label>资源路径:</label></td>
                     <td><input id="url" class="easyui-textbox" type="text" name="url"/></td>
                 </tr>
            </table>
         </form>
     </div>
     <div style="text-align:center;padding:5px">
         <span style="padding: 14px;"><a href="javascript:void(0);" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="submitForm();" >确定</a></span>
         <span style="padding: 14px;"><a href="javascript:void(0);" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton"  onclick="resetForm();">取消</a></span>
     </div>
    <script language="javascript">
        function submitForm(){
            $('#form').form('submit',{
                success: function(){
                    $('#add').window('close');
                    $.messager.alert(' ','添加成功！','info');
                }
            });
        }
        function resetForm(){
            $('#form').form('reset');
        }
    </script>
</body>
</html>