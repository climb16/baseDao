function addTab(tabId,title,url){
    if (!$('#tabs').tabs('exists', title)) {
        $('#tabs').tabs('add', {
            id:'tab'+tabId,
            title: title,
            content: createFrame(url),
            closable: true,
            width: $('#mainPanle').width() - 10,
            height: $('#mainPanle').height() - 26
        });
    } else {
        $('#tabs').tabs('select', title);
    }
}
function createFrame(url) {
    return '<iframe scrolling="no" frameborder="0"  src="'+url+'" style="width:100%;height:100%;margin:0"></iframe>';
}

function createWindow(id, title,iconCls,url, width, height){
    var win = $('#'+id);
    win.window({
        title:title,
        iconCls:iconCls,
        width:width,
        height:height,
        collapsible: false,
        minimizable: false,
        maximizable: false,
        modal:true,
        //content: createFrame('menu_addInit.do?menuId='+menuId)
        href:url
    });
}