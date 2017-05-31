<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ include file="/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Login Form</title>
  <link rel="stylesheet" href="${Cxtstatic}/css/login.css" type="text/css">
</head>
<body>
  <section class="container">
    <div class="login">
      <h1>登陆BOS</h1>
      <form method="post" action="${Cxt}/user_login">
        <p><input type="text" name="username"  placeholder="用户名"></p>
        <p><input type="password" name="password" placeholder="密码"></p>
        <p class="submit"><input type="submit" value="登陆"></p>
      </form>
    </div>

    <div class="login-help">
      <p>制作人：唐玖佳 电话:18381957044</p>
    </div>
  </section>

</body>
</html>
