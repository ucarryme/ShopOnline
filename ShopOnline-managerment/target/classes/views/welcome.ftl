<#assign ctx=request.contextPath/>
<!DOCTYPE html>
<html>
  <head>
      <#include "head.ftl">
    <script type="text/javascript">
    function delfunc(obj){
    	layer.confirm('确认删除？', {
    		  btn: ['确定','取消'] //按钮
    		}, function(){
   				$.ajax({
   					type : 'post',
   					url : $(obj).attr('data-url'),
   					data : {act:'del',del_id:$(obj).attr('data-id')},
   					dataType : 'json',
   					success : function(data){
   						if(data==1){
   							layer.msg('操作成功', {icon: 1});
   							$(obj).parent().parent().remove();
   						}else{
   							layer.msg(data, {icon: 2,time: 2000});
   						}
   						layer.closeAll();
   					}
   				})
    		}, function(index){
    			layer.close(index);
    			return false;// 取消
    		}
    	);
    }
    
    //全选
    function selectAll(name,obj){
    	$('input[name*='+name+']').prop('checked', $(obj).checked);
    }   
    
    function get_help(obj){
        layer.open({
            type: 2,
            title: '帮助手册',
            shadeClose: true,
            shade: 0.3,
            area: ['90%', '90%'],
            content: $(obj).attr('data-url'), 
        });
    }
    
    function delAll(obj,name){
    	var a = [];
    	$('input[name*='+name+']').each(function(i,o){
    		if($(o).is(':checked')){
    			a.push($(o).val());
    		}
    	})
    	if(a.length == 0){
    		layer.alert('请选择删除项', {icon: 2});
    		return;
    	}
    	layer.confirm('确认删除？', {btn: ['确定','取消'] }, function(){
    			$.ajax({
    				type : 'get',
    				url : $(obj).attr('data-url'),
    				data : {act:'del',del_id:a},
    				dataType : 'json',
    				success : function(data){
    					if(data == 1){
    						layer.msg('操作成功', {icon: 1});
    						$('input[name*='+name+']').each(function(i,o){
    							if($(o).is(':checked')){
    								$(o).parent().parent().remove();
    							}
    						})
    					}else{
    						layer.msg(data, {icon: 2,time: 2000});
    					}
    					layer.closeAll();
    				}
    			})
    		}, function(index){
    			layer.close(index);
    			return false;// 取消
    		}
    	);	
    }
    </script>        
  <meta name="__hash__" content="6f2f83477e27fc132fe9427e851ef6a0_f98429bc72657fc38860af448bf30e91" /></head>
  <body style="background-color:#ecf0f5;">
 

<div class="wrapper">
     <div class="breadcrumbs" id="breadcrumbs">
	<ol class="breadcrumb">
	<li><a href="javascript:void(0);"><i class="fa fa-home"></i>&nbsp;&nbsp;后台首页</a></li>
	        
	        <li><a href="javascript:void(0);">ShopOnline</a></li>
	        <li><a href="javascript:void(0);"></a></li>          
	</ol>
</div>

     <section class="content">
		<div class="row">
            <div class="col-md-3 col-sm-6 col-xs-12">
              <div class="info-box">
                <span class="info-box-icon bg-aqua"><i id="icon_1" class="fa fa-bell icon_position"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">待处理订单</span>
                  <span class="info-box-number">139</span>
                </div>
              </div>
            </div>
            <div class="col-md-3 col-sm-6 col-xs-12">
              <div class="info-box">
                <span class="info-box-icon bg-green"><i class="fa fa-flag-o icon_position"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">商品数量</span>
                  <span class="info-box-number">105</span>
                </div>
              </div>
            </div>
            <div class="col-md-3 col-sm-6 col-xs-12">
              <div class="info-box">
                <span class="info-box-icon bg-yellow"><i class="fa fa-files-o icon_position"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">文章数量</span>
                  <span class="info-box-number">149</span>
                </div>
              </div>
            </div>
            <div class="col-md-3 col-sm-6 col-xs-12">
              <div class="info-box">
                <span class="info-box-icon bg-red"><i class="fa fa-user-plus icon_position"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">会员总数</span>
                  <span class="info-box-number">2568</span>
                </div>
              </div>
            </div>
         </div>
		
		<div class="row">
			<div class="col-md-12">
		      <div class="box box-info">
		        <div class="box-header">
		          <h3 class="box-title">今日统计</h3>
		        </div>
		        <div class="box-body">
	         		<div class="row">
			  			<div class="col-sm-3 col-xs-6">
			  				新增订单：0			  			</div>
			  				<div class="col-sm-3 col-xs-6">
			  				今日访问：0			  			</div>
			  				<div class="col-sm-3 col-xs-6">
			  				新增会员：0			  			</div>
			  				<div class="col-sm-3 col-xs-6">
			  				待审评论：5			  			</div>
		  			</div>
		        </div>
		      </div>
		    </div>
		</div>
          <div class="row">
          	     <div class="col-md-12">
			       	 <div class="box  box-primary">
                        <div class="box-body">
                            <div class="info-box">                 
                            	<table class="table table-bordered">
                                <tbody>
                                <tr>
                                    <td>服务器操作系统：</td>
                                    <td>Linux</td>
                                    <td>服务器域名/IP：</td>
                                    <td>demo2.ShopOnline.cn [ 183.158.35.60 ]</td>
                                    <td>服务器环境：</td> 
                                    <td>Apache/2.4.12 (Unix) OpenSSL/1.0.1m /5.6.8 mod_perl/2.0.8-dev Perl/v5.16.3</td>       
                                </tr> 
                                <tr>
                                    <td> 版本：</td>
                                    <td>5.6.8</td>
                                    <td>Mysql 版本：</td>
                                    <td>5.6.24</td> 
                                    <td>GD 版本</td> 
                                    <td>bundled (2.1.0 compatible)</td>  
                                </tr>   
                                <tr>
                                    <td>文件上传限制：</td>
                                    <td>128M</td>
                                    <td>最大占用内存：</td>
                                    <td>512M</td> 
                                    <td>最大执行时间：</td> 
                                    <td>30s</td>  
                                </tr>  
                                <tr>
                                    <td>安全模式：</td>
                                    <td>NO</td>
                                    <td>Zlib支持：</td>
                                    <td>YES</td> 
                                    <td>Curl支持：</td> 
                                    <td>YES</td>  
                                </tr>  
                            	</table>				
                            </div>
                        </div>
                    </div>
			    </div>
          </div>

           <div class="row">
                <div class="col-md-12">
                    <div class="box  box-success">
				        <div class="box-body">
				        	<div class="info-box">
					         	<table class="table table-bordered">
					         		<tr>
					         			<td>程序版本：</td><td>ShopOnline v1.3.2</td>
					         			<td>更新时间：</td><td>2019-02-25</td>
					         			<td>程序开发：</td><td>上海尚学堂科技有限公司</td>			         			
					         		</tr>
					         		<tr>
					         			<td>版权所有：</td><td>上海尚学堂科技有限公司</td>
					         			<td>官方授权：</td><td><a href="http://www.shsxt.com/" target="_blank">商业授权</a></td>
					         			<td>官方论坛：</td><td><a href="#" target="_blank">ShopOnline交流论坛</a></td>
					         		</tr>
					         	</table>
				         	</div>
				        </div>
                    </div>
                </div>
          </div>
          <div class="callout callout-success">
            <p> <b>ShopOnline</b> B2C 开源商城    包含(pc + wap + android + ios + 微商城)多终端 </p>
          </div>
     </section>
 </div>
 </body>
 </html>