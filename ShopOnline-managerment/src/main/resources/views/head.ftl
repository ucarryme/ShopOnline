<!-- 设置项目根路径全局变量 -->
<#assign ctx=request.contextPath/>
<meta charset="UTF-8">
<title>ShopOnline后台管理</title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
      name="viewport">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 禁止浏览器发起 favicon.ico 请求 -->
<link rel="icon" href="data:image/ico;base64,aWNv">
<!-- Bootstrap 3.3.4 -->
<link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<!-- FontAwesome 4.3.0 -->
<link href="${ctx}/static/bootstrap/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<!-- Ionicons 2.0.0 -->
<#--<link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css"
rel="stylesheet" type="text/css"/>-->
<!-- Theme style -->
<link href="${ctx}/static/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css"/>
<!-- iCheck -->
<link href="${ctx}/static/plugins/iCheck/flat/blue.css" rel="stylesheet" type="text/css"/>
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<!-- jQuery 2.1.4 -->
<script src="${ctx}/static/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script src="${ctx}/static/js/global.js"></script>
<script src="${ctx}/static/js/upgrade.js"></script>
<script src="${ctx}/static/js/myFormValidate.js"></script>
<script src="${ctx}/static/js/layer/layer-min.js"></script>
<!--弹窗js 参考文档 http://layer.layui.com/-->
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/static/js/myAjax.js"></script>