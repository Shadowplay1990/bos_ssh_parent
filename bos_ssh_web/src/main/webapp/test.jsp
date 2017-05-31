<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/taglib.jsp"%>
<html>
<head>
    <title>test</title>
    <%@include file="include/head-easyUI.jsp"%>
</head>
<body class="easyui-layout">

    <div title="系统管理" style="height: 120px" data-options="region:'north'">北</div>
    <div title="菜单" style="width: 180px" data-options="region:'west'">
        <div class="easyui-accordion" data-options="fit:true">
            <div title="面板1" data-options="iconCls:'icon-cut'">
                <a id="bt1" class="easyui-linkbutton">添加一个选项卡</a>
                <script type="text/javascript">
                    $(function () {
                        $("#bt1").click(function () {
                            var b=$("#mytabs").tabs("exists","唐哥哥");
                            if (b){
                                $("#mytabs").tabs("select","唐哥哥");
                            }else {
                                $("#mytabs").tabs("add",{
                                    title:'唐哥哥',
                                    iconCls:'icon-edit',
                                    closable:true,
                                    content:'<iframe frameborder="0" height="100%" width="100%" src="https://www.baidu.com"></iframe>'
                                });
                            }
                        });
                    });
                </script>
            </div>
            <div title="面板2">
                <ul id="tree1" class="ztree"></ul>
                <script type="text/javascript">
                    $(function () {

                        var setting={
                            data:{
                                simpleData:{
                                    enable:true
                                }
                            }
                        };

                        var zNodes=[
                            {"id":"1","pId":"2","name":"节点1"},
                            {"id":"2","pId":"3","name":"节点2"},
                            {"id":"3","pId":"0","name":"节点3"}
                        ];

                        $.fn.zTree.init($("#tree1"),setting,zNodes);
                    });
                </script>
            </div>
            <div title="面板3">

            </div>
        </div>
    </div>
    <div data-options="region:'center'">
        <div id="mytabs" class="easyui-tabs" data-options="fit:true">
            <div title="面板1" data-options="closable:true">1</div>
            <div title="面板2" data-options="iconCls:'icon-cut'">2</div>
            <div title="面板3">3</div>
        </div>
    </div>
    <div title="左边栏" style="width: 100px" data-options="region:'east'">东</div>
    <div title="下边栏" style="height: 120px" data-options="region:'south'">南</div>

</body>
</html>
