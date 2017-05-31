<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${Cxtstatic}/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${Cxtstatic}/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${Cxtstatic}/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${Cxtstatic}/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${Cxtstatic}/css/default.css">
<script type="text/javascript"
	src="${Cxtstatic}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${Cxtstatic}/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${Cxtstatic}/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${Cxtstatic}/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	function doAdd(){
		$('#addStaffWindow').window("open");
	}
	
	function doView(){

	}
	
	function doDelete(){
	    var rows=$("#grid").datagrid("getSelections");

	    if (rows.length==0){
	        $.messager.alert("信息提示","请选中要删除的派件员","info");
		}else{
	        $.messager.confirm("信息提示","确认删除选中的派件员？",function (r) {
				if (r){
				    var ids="";
				    var array=new Array();

				    for(var i=0;i<rows.length;i++){
				        var id=rows[i].staffid;
				        array.push(id);
					}
					ids=array.join(",");
					location.href="${Cxt}/staff_updateDeltag?ids="+ids;
				}
            });
		}
	}
	
	function doRestore(){
		alert("将取派员还原...");
	}
	//工具栏
	var toolbar = [ {
		id : 'button-view',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-delete',
		text : '作废',
		iconCls : 'icon-cancel',
		handler : doDelete
	},{
		id : 'button-save',
		text : '还原',
		iconCls : 'icon-save',
		handler : doRestore
	}];
	// 定义列
	var columns = [ [ {
		field : 'staffid',
		checkbox : true,
	},{
		field : 'name',
		title : '姓名',
		width : 120,
		align : 'center'
	}, {
		field : 'cellphone',
		title : '手机号',
		width : 120,
		align : 'center'
	}, {
		field : 'haspad',
		title : '是否有PDA',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="1"){
				return "有";
			}else{
				return "无";
			}
		}
	}, {
		field : 'deltag',
		title : '是否作废',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="0"){
				return "正常使用"
			}else{
				return "已作废";
			}
		}
	}, {
		field : 'standard',
		title : '取派标准',
		width : 120,
		align : 'center'
	}, {
		field : 'station',
		title : '所属单位',
		width : 200,
		align : 'center'
	} ] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 取派员信息表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList: [30,50,100],
			pagination : true,
			toolbar : toolbar,
			url : "${Cxt}/staff_queryStaff",
			idField : 'staffid',
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		// 添加取派员窗口
		$('#addStaffWindow').window({
	        title: '添加取派员',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });

        // 修改取派员窗口
        $('#editStaffWindow').window({
            title: '修改取派员',
            width: 400,
            modal: true,
            shadow: true,
            closed: true,
            height: 400,
            resizable:false
        });
	});

    function doDblClickRow(rowIndex, rowData){
        //打开修改取派员窗口
        $('#editStaffWindow').window("open");
        //使用form表单对象的load方法回显数据
        $("#editStaffForm").form("load",rowData);
    }

</script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="addStaffForm" action="staff_addStaff" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<tr>
						<td>取派员编号</td>
						<td><input type="text" name="staffid" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<script type="text/javascript">
                            $(function () {
                                $("#save").click(function () {
                                    var b=$("#addStaffForm").form("validate");
                                    if(b){
                                        $("#addStaffForm").submit();
                                    }
                                });


                                var reg = /^1[3|4|5|7|8][0-9]{9}$/;
                                $.extend($.fn.validatebox.defaults.rules,{
                                    telephone:{
                                        validator:function (value, param) {
                                            return reg.test(value);
                                        },
                                        message:'手机号输入有误'
                                    }
                                });
                            });
						</script>
						<td>
							<input type="text" name="cellphone"  data-options="validType:'telephone'"
								   class="easyui-validatebox" required="true"/>
						</td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input  type="text" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<input id="Cbox" type="checkbox"/>
						是否有PDA</td>
						<input id="boxValue" type="hidden" name="haspad"/>
					</tr>
					<script type="text/javascript">
						$(function () {
							if(($("#Cbox").is(':checked'))){
							    $("#boxValue").attr("value","1");
							}else{
                                $("#boxValue").attr("value","2");
							}
                        });
					</script>
					<tr>
						<td>取派标准</td>
						<td>
							<input type="text" name="standard" class="easyui-validatebox" required="true"/>
						</td>
					</tr>
					</table>
			</form>
		</div>
	</div>

    <%--修改取派员--%>
	<div class="easyui-window" title="对收派员进行添加或者修改" id="editStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="edit" icon="icon-edit" href="#" class="easyui-linkbutton" plain="true" >保存修改</a>
			</div>
		</div>

		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="editStaffForm" action="${Cxt}/staff_editStaff" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员修改 table -->
					<tr>
						<td>取派员编号</td>
						<td><input readonly="true" type="text" name="staffid" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<script type="text/javascript">
                            $(function () {
                                $("#edit").click(function () {
                                    var b=$("#editStaffForm").form("validate");
                                    if(b){
                                        $("#editStaffForm").submit();
                                    }
                                });


                                var reg = /^1[3|4|5|7|8][0-9]{9}$/;
                                $.extend($.fn.validatebox.defaults.rules,{
                                    telephone:{
                                        validator:function (value, param) {
                                            return reg.test(value);
                                        },
                                        message:'手机号输入有误'
                                    }
                                });
                            });
						</script>
						<td>
							<input type="text" name="cellphone"  data-options="validType:'telephone'"
								   class="easyui-validatebox" required="true"/>
						</td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
							<input id="cccbb" type="checkbox"/>
							是否有PDA</td>
						<input id="hid2" type="hidden" name="haspad" />
					</tr>
					<script>
						$(function () {
							if(($("#cccbb").is(":checked"))){
							    $("#hid2").val("1");
							}else{
                                $("#hid2").val("2");
							}
                        });
					</script>
					<tr>
						<td>取派标准</td>
						<td>
							<input type="text" name="standard" class="easyui-validatebox" required="true"/>
						</td>
					</tr>
					<tr>
						<td>作废?</td>
						<td>
							<input id="delbox" type="checkbox" class="easyui-validatebox" required="true"/>
							<input id="boxVal" type="hidden" name="deltag" value="1"/>
						</td>
					</tr>
					<script type="text/javascript">
						$(function () {
                            if (false==($("#delbox").is(":checked"))){
                                $("#boxVal").val("0");
                            }
                        });
					</script>
				</table>
			</form>
		</div>
	</div>
</body>
</html>	