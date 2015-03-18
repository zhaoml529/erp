<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>欢迎</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="layout/script.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			initMenu();
			if (jqueryUtil.isLessThanIe8()) {
				$.messager.show({
					title : '警告',
					msg : '您使用的浏览器版本太低！<br/>建议您使用谷歌浏览器来获得更快的页面响应效果！',
					timeout : 1000 * 30
				});
			}
		});
		function initMenu(){
			var $ma=$("#menuAccordion");
			$ma.accordion({animate:true,fit:true,border:false});
			$.post("systemAction!findAllFunctionList.action", {userName:"1"}, function(rsp) {
				$.each(rsp,function(i,e){
					var menulist ="<div class=\"well well-small\">";
					if(e.child && e.child.length>0){
						$.each(e.child,function(ci,ce){
							var effort=ce.name+"||"+ce.iconCls+"||"+ce.url;
							menulist+="<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'"+ce.iconCls+"'\" onclick=\"addTab('"+effort+"');\">"+ce.name+"</a><br/>";
						});
					}
					menulist+="</div>";
					$ma.accordion('add', {
			            title: e.name,
			            content: menulist,
						border:false,
			            iconCls: e.iconCls,
			            selected: false
			        });
				});
			}, "JSON").error(function() {
				$.messager.alert("提示", "获取菜单出错,请重新登陆!");
			});
		}
	</script>
	<style type="text/css">
	#menuAccordion a.l-btn span span.l-btn-text {
	    display: inline-block;
	    height: 14px;
	    line-height: 14px;
	    margin: 0px 0px 0px 10px;
	    padding: 0px 0px 0px 10px;
	    vertical-align: baseline;
	    width: 128px;
	}
	#menuAccordion 	a.l-btn span span.l-btn-icon-left {
	    background-position: left center;
	    padding: 0px 0px 0px 20px;
	}
	#menuAccordion .panel-body {
		padding:5px;
	}
	#menuAccordion span:focus{
		outline: none;
	}
	</style>
  </head>
 <body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:40px;background:#EEE;padding:10px;overflow: hidden;"  href="layout/north.jsp"></div>
	<div data-options="region:'west',split:true,title:'主要菜单'" style="width:200px;">
			<div id="menuAccordion"></div>
	</div> 
	<div data-options="region:'south',border:false" style="height:25px;background:#EEE;padding:5px;" href="layout/south.jsp"></div>
	<div data-options="region:'center',plain:true,title:'欢迎使用ERP'" style="overflow: hidden;"  href="layout/center.jsp"></div>
<%--	<jsp:include page="user/loginAndReg.jsp"></jsp:include>--%>
</body>
</html>
