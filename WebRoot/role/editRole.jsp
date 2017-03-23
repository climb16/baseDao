<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<div id="title" style="margin-top: 16px;"></div>
<div style="padding:8px; margin-left: 40px;">
    <form id="form" action="role_updateRole.do" method="post">
        <table border="0">
            <tr>
                <td height="36px" style="text-align:right"><label>角色名称:</label></td>
                <td ><input type="hidden" name="rId" value="${requestScope.role.getrId()}">
                    <input class="easyui-textbox" type="text" name="rName" value="${requestScope.role.getrName()}"/></td>
            </tr>
            <tr >
                <td height="100px" style="text-align:right"><label>角色描述:</label></td>
                <td>
                    <textarea  class="easyui-textarea" name="depict">${requestScope.role.getDepict()}</textarea></td>
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
                $.messager.alert(' ','修改成功！','info');
            }
        });
    }
    function resetForm(){
        $('#form').form('reset');
    }
</script>
</body>
</html>