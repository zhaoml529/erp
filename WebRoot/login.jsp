<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	/*response.setHeader("Pragma","No-cache"); 
	response.setHeader("Cache-Control","no-cache"); 
	response.setDateHeader("Expires", 0); 
	response.flushBuffer();*/
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>欢迎登陆</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" type="image/x-icon" href="../extend/iconkey.ico" media="screen" />
	<script type="text/javascript" src="js/xheditor/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/jquery.cookie.js"></script>
	<link rel="stylesheet" type="text/css" href="css/zice.style.css">
	<link rel="stylesheet" type="text/css" href="css/tipsy.css">
	<link rel="stylesheet" type="text/css" href="css/icon.css">
	<link rel="stylesheet" type="text/css" href="css/buttons.css">
	<script type="text/javascript" src="js/iphone.check.js"></script>
	<script type="text/javascript" src="js/jquery-jrumble.js"></script>
	<script type="text/javascript" src="js/jquery.tipsy.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
	<script type="text/javascript">
		if(top!=self){
			if(top.location != self.location)
			 top.location=self.location; 
		}
	</script>
	 <style type="text/css">
html {
	background-image: none;
}

label.iPhoneCheckLabelOn span {
	padding-left: 0px
}

#versionBar {
	background-color: #212121;
	position: fixed;
	width: 100%;
	height: 35px;
	bottom: 0;
	left: 0;
	text-align: center;
	line-height: 35px;
	z-index: 11;
	-webkit-box-shadow: black 0px 10px 10px -10px inset;
	-moz-box-shadow: black 0px 10px 10px -10px inset;
	box-shadow: black 0px 10px 10px -10px inset;
}

.copyright {
	text-align: center;
	font-size: 10px;
	color: #CCC;
}

.copyright a {
	color: #A31F1A;
	text-decoration: none
}

/*update-begin--Author:tanghong  Date:20130419 for：【是否】按钮错位*/
.on_off_checkbox{
	width:0px;
}
/*update-end--Author:tanghong  Date:20130419 for：【是否】按钮错位*/
#login .logo {
	width: 500px;
	height: 51px;
}
#cap{
margin-left: 88px;
}
</style>
  </head>
  <body>
	<div id="alertMessage"></div>
	<div id="successLogin"></div>
	<div class="text_success">
		<img src="extend/loader_green.gif" alt="Please wait" /> <span>登陆成功!请稍后....</span>
	</div>
	<div id="login">
		<div class="ribbon" style="background-image:url(extend/typelogin.png);"></div>
		<div class="inner">
			<div class="logo">
				<img src="extend/toplogo-jeecg.png" />
			</div>
			<div class="formLogin">
				<form name="formLogin" action="systemAction!load.action" id="formLogin" method="post">
					<input name="userKey" type="hidden" id="userKey" value="D1B5CC2FE46C4CC983C073BCA897935608D926CD32992B5900" />
					<div class="tip">
						<input class="userName" name="userName" type="text" id="userName" title="用户名" iscookie="true" value="admin" nullmsg="请输入用户名!" />
					</div>
					<div class="tip">
						<input class="password" name="password" type="password" id="password" title="密码" value="admin" nullmsg="请输入密码!" />
					</div>
					<div id="cap" class="tip">
						<input class="captcha" name="captcha" type="text" id="captcha"  nullmsg="请输入验证码!" />
						<img style="width:85px;height:35px;margin-top: -10px;" align="absmiddle" id="Kaptcha" src="Kaptcha.jpg"/>
					</div>
					<div class="loginButton">
						<div style="float: left; margin-left: -9px;">
							<input type="checkbox" id="on_off" name="remember" checked="ture" class="on_off_checkbox" value="0" /> <span class="f_help">是否记住用户名?</span>
						</div>
						<div style="float: right; padding: 3px 0; margin-right: -12px;">
							<div>
								<ul class="uibutton-group">
									<li><a class="uibutton normal" href="javascript:void(0);" id="but_login">登陆</a>
									</li>
									<li><a class="uibutton normal" href="javascript:void(0);" id="forgetpass">重置</a>
									</li>
								</ul>
							</div>
							<div style="float: left; margin-left: 30px;">
								<a href="javascript:void(0);"><span class="f_help">是否初始化admin的密码</span></a>
							</div>
						</div>
						<div class="clear"></div>
					</div>
				</form>
			</div>
		</div>
		<div class="shadow"></div>
	</div>
	<!--Login div-->
	<div class="clear"></div>
	<div id="versionBar">
		<div class="copyright">&copy; 版权所有 <span class="tip"><a href="javascript:void(0);" title="sysErp">sy</a>
				(推荐使用IE9+,谷歌浏览器可以获得更快,更安全的页面响应速度)技术支持:<a href="javascript:void(0);" title="sysErp">sy</a> </span>
		</div>
	</div>
</body>
</html>
