var rMenu, zTree;

function showRMenu( x, y) {
    rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});
    $("body").bind("mousedown", onBodyMouseDown);
}
function onBodyMouseDown(event){
    if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
        rMenu.css({"visibility" : "hidden"});
    }
}
function hideRMenu() {
    if (rMenu) rMenu.css({"visibility": "hidden"});
    $("body").unbind("mousedown", onBodyMouseDown);
}
function menuOnclick(handle){
    hideRMenu();
    var menuId = zTree.getSelectedNodes()[0].id;
    if(handle == "add"){
        add(menuId);
    }else if(handle == "edt"){
        edit(menuId);
    }else if(handle == "del"){
        del(menuId);
    }
}
function showMenuTble(treeNode){
    editPanel(treeNode);
}

function add( menuId ){
    createWindow('add','添加', 'icon-add', 'menu_addInit.do?menuId='+menuId, 280, 320);
}

function edit( menuId ){
    createWindow('add', '编辑', 'icon-edit', 'menu_updateInit.do?menuId='+menuId, 280, 320)
}

function del( menuId ){
    $.ajax({
        type:"post",
        url:"menu_deleteMenuById.do?id="+menuId,
        success:function(msg){
            if(msg == 0) $.messager.alert(' ','您要删除的节点不存在！','info');
            if(msg == 1) $.messager.alert(' ', '该节点存在子节点，禁止删除！','warning');
            if(msg == 2) $.messager.alert(' ', '删除成功！','info');
        }
    });
}
function editPanel( treeNode ){
    $('#main').panel({
        content: createFrame('menu_getChildMenusById.do?id='+treeNode.id)
    });
}
