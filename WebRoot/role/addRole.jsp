<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<div id="title" style="margin-top: 16px;"></div>
<div style="padding:8px; margin-left: 10px;">
    <form id="form" action="role_addRole.do" method="post">
        <table border="0">
            <tr>
                <td height="36px" style="text-align:right"><label>角色名称:</label></td>
                <td ><input class="easyui-textbox" type="text" name="rName" /></td>
            </tr>
            <tr >
                <td height="36px" style="text-align:right"><label>角色描述:</label></td>
                <td><input class="easyui-textbox" type="text" name="depict"/></td>
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