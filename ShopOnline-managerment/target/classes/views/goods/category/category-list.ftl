<#assign ctx=request.contextPath/>
<!DOCTYPE html>
<html>
  <head>
	  <#include "../../head.ftl">
    <script type="text/javascript">


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
  <meta name="__hash__" content="de25d42444751b4d059ec9006eb4ebb9_18e7b463027ae778e1bc48f3dec702d7" /></head>
  <body style="background-color:#ecf0f5;">
 

<div class="wrapper">
    <div class="breadcrumbs" id="breadcrumbs">
	<ol class="breadcrumb">
	<li><a href="javascript:void();"><i class="fa fa-home"></i>&nbsp;&nbsp;后台首页</a></li>
	        
	        <li><a href="javascript:void();">商品管理</a></li>    
	        <li><a href="javascript:void();">商品分类</a></li>          
	</ol>
</div>

	<section class="content">
       <div class="row">
       		<div class="col-xs-12">
	       		<div class="box">
	             <div class="box-header">
	               	<nav class="navbar navbar-default">	     
				        <div class="collapse navbar-collapse">
						   <div class="navbar-form row">
				            	<div class="col-md-1">
									<button class="btn bg-navy" type="button" onclick="tree_open(this);"><i class="fa fa-angle-double-down"></i>展开</button>
					            </div>
					            <div class="col-md-9">
					            	<span class="warning">温馨提示：顶级分类（一级大类）设为推荐时才会在首页楼层中显示</span>
					            </div>
					            <div class="col-md-2">
					            <a href="${ctx}/goods/category/add" class="btn btn-primary pull-right"><i class="fa fa-plus"></i>新增分类</a>
				            	</div>
				            </div>
				      	</div>
	    			</nav> 	               
	             </div><!-- /.box-header -->
	           <div class="box-body">
	           <div class="row">
	            <div class="col-sm-12">
	              <table id="list-table" class="table table-bordered table-striped dataTable" role="grid" aria-describedby="example1_info">
	                 <thead>
	                   <tr role="row">
	                   	   <th valign="middle">分类ID</th>
		                   <th valign="middle">分类名称</th>
                           <th valign="middle">手机显示名称</th>
                           <th valign="middle">是否推荐</th>
		                   <th valign="middle">是否显示</th>
                                   <th valign="middle">分组</th>
		                   <th valign="middle">排序</th>
		                   <th valign="middle">操作</th>
	                   </tr>
	                 </thead>
			<tbody>
			<#list gcvList as gcv1>
			<tr role="row" align="center" class="1" id='${"gcvList"+gcv1.id}' >
			  			 <td>${gcv1.id}</td>
	                     <td align="left" style="padding-left:5em"> 
	                      <span class="glyphicon glyphicon-plus btn-warning" style="padding:2px; font-size:12px;"  id="icon_1_1" aria-hidden="false" onclick="rowClicked(this)" ></span>&nbsp;                             <span>${gcv1.name}</span>
			     		 </td>
                         <td><span>${gcv1.mobileName}</span></td>
                         <td>
							 <input type="hidden" id = "isHot${gcv1.id}" value="${gcv1.isHot}">
                             <img id = "imgHot${gcv1.id}" width="20" height="20" onclick="changeIshot(${gcv1.id},${gcv1.isHot},this)"/>
                         </td>
	                 <td>
						 <input type="hidden" id = "isShow${gcv1.id}" value="${gcv1.isShow}">
                             <img id="imgShow${gcv1.id}" width="20" height="20" src="" onclick="changeIsshow(${gcv1.id},${gcv1.isShow},this)"/>
                         </td>
	                     <td>                                 
                         	<input type="text" onchange="updateCatGroup(${gcv1.id},this)"  onkeyup="this.value=this.value.replace(/[^\d]/g,'')" onpaste="this.value=this.value.replace(/[^\d]/g,'')"  size="4"value="${gcv1.catGroup}" class="input-sm" />
                            </td>                         
	                     <td>
                         	<input type="text" onchange="updateSortOrder(${gcv1.id},this)"   onkeyup="this.value=this.value.replace(/[^\d]/g,'')" onpaste="this.value=this.value.replace(/[^\d]/g,'')" size="4" value="${gcv1.sortOrder}" class="input-sm" />
                             </td>
	                     <td>
	                      <a class="btn btn-primary" href="${ctx}/goods/category/updateOneInfo/${gcv1.id}"><i class="fa fa-pencil"></i></a>
	                      <a class="btn btn-danger" onclick="del_fun(this,'${ctx}'+'/goods/category/delete/','${gcv1.id}');"><i class="fa fa-trash-o"></i></a>
			     		</td>
	                   </tr>
				<#list  gcv1.children as gcv2>
			<tr role="row" align="center" class="2" id='${"gcvList"+gcv2.id}' style="display:none">
			  			 <td>${gcv2.id}</td>
	                     <td align="left" style="padding-left:10em"> 
	                      <span class="glyphicon glyphicon-plus btn-warning" style="padding:2px; font-size:12px;"  id="icon_2_12" aria-hidden="false" onclick="rowClicked(this)" ></span>&nbsp;                             <span>${gcv2.name}</span>
			     		 </td>
                         <td><span>${gcv2.mobileName}</span></td>
                         <td>
							 <input type="hidden" id = "isHot${gcv2.id}" value="${gcv2.isHot}">
                             <img id="imgHot${gcv2.id}" width="20" height="20" src="" onclick="changeIshot(${gcv2.id},${gcv2.isHot},this)"/>
                         </td>
	                 <td>
						 	<input type="hidden" id = "isShow${gcv2.id}" value="${gcv2.isShow}">
                             <img id="imgShow${gcv2.id}" width="20" height="20" src="" onclick="changeIsshow(${gcv1.id},${gcv1.isShow},this)"/>
                         </td>
	                     <td>                                 
                         	<input type="text" onchange="updateCatGroup(${gcv2.id},this)"  onkeyup="this.value=this.value.replace(/[^\d]/g,'')" onpaste="this.value=this.value.replace(/[^\d]/g,'')"  size="4"value="${gcv2.catGroup}" class="input-sm" />
                            </td>                         
	                     <td>
                         	<input type="text" onchange="updateSortOrder(${gcv2.id},this)"   onkeyup="this.value=this.value.replace(/[^\d]/g,'')" onpaste="this.value=this.value.replace(/[^\d]/g,'')" size="4" value="${gcv2.sortOrder}" class="input-sm" />
                             </td>
	                     <td>
	                      <a class="btn btn-primary" href="${ctx}/goods/category/updateOneInfo/${gcv2.id}"><i class="fa fa-pencil"></i></a>
	                      <a class="btn btn-danger" onclick="del_fun(this,'${ctx}'+'/goods/category/delete/','${gcv2.id}');"><i class="fa fa-trash-o"></i></a>
			     		</td>
	                   </tr>
					<#list  gcv2.children as gcv3>
			<tr role="row" align="center" class="3" id='${"gcvList"+gcv3.id}' style="display:none">
			  			 <td>${gcv3.id}</td>
	                     <td align="left" style="padding-left:15em">
	                                                   <span>${gcv3.name}</span>
			     		 </td>
                         <td><span>${gcv3.mobileName}</span></td>
                         <td>
							 <input type="hidden" id = "isHot${gcv3.id}" value="${gcv3.isHot}">
                             <img id="imgHot${gcv3.id}" width="20" height="20" src="" onclick="changeIshot(${gcv3.id},${gcv3.isHot},this)"/>
                         </td>
	                 <td>
						 <input type="hidden" id = "isShow${gcv3.id}" value="${gcv3.isShow}">
                             <img id="imgShow${gcv3.id}" width="20" height="20" src="" onclick="changeIsshow(${gcv1.id},${gcv1.isShow},this)"/>
                         </td>
	                     <td>
                         	<input type="text" onchange="updateCatGroup(${gcv3.id},this)"  onkeyup="this.value=this.value.replace(/[^\d]/g,'')" onpaste="this.value=this.value.replace(/[^\d]/g,'')"  size="4"value="${gcv3.catGroup}" class="input-sm" />
                            </td>
	                     <td>
                         	<input type="text" onchange="updateSortOrder(${gcv3.id},this)"   onkeyup="this.value=this.value.replace(/[^\d]/g,'')" onpaste="this.value=this.value.replace(/[^\d]/g,'')" size="4" value="${gcv3.sortOrder}" class="input-sm" />
                             </td>
	                     <td>
	                      <a class="btn btn-primary" href="${ctx}/goods/category/updateOneInfo/${gcv3.id}"><i class="fa fa-pencil"></i></a>
	                      <a class="btn btn-danger" onclick="del_fun(this,'${ctx}'+'/goods/category/delete/','${gcv3.id}');"><i class="fa fa-trash-o"></i></a>
<#--							  '${ctx}'+'/goods/category/delete/'+'${gcv2.id}'-->

			     		</td>
	                   </tr>
						</#list>
					</#list>
			</#list>
	                   </tbody>
	               </table></div></div>
		               <div class="row">
			               <div class="col-sm-5">
			               <div class="dataTables_info" id="example1_info" role="status" aria-live="polite">分页</div></div>                                   
		               </div>
	             </div><!-- /.box-body -->
	           </div><!-- /.box -->
       		</div>
       </div>
     </section>
</div>
<script type="text/javascript">

	function del_list_fuc(list) {
		if(Array.isArray(list)){
			for(var item in list){
				console.log(item)
			}
		}
	}

// 展开收缩
function  tree_open(obj)
{
	 var tree = $('#list-table tr[id^="2_"], #list-table tr[id^="3_"] '); //,'table-row'
	 if(tree.css('display')  == 'table-row')
	 {
		 $(obj).html("<i class='fa fa-angle-double-down'></i>展开");
		tree.css('display','none');
		$("span[id^='icon_']").removeClass('glyphicon-minus');
		$("span[id^='icon_']").addClass('glyphicon-plus');
	 }else
	 {
		 $(obj).html("<i class='fa fa-angle-double-up'></i>收缩");
		tree.css('display','table-row');
		$("span[id^='icon_']").addClass('glyphicon-minus');
		$("span[id^='icon_']").removeClass('glyphicon-plus');
	 }
}
    
// 以下是 bootstrap 自带的  js
function rowClicked(obj)
{
  span = obj;

  obj = obj.parentNode.parentNode;

  var tbl = document.getElementById("list-table");

  var lvl = parseInt(obj.className);

  var fnd = false;
  
  var sub_display = $(span).hasClass('glyphicon-minus') ? 'none' : '' ? 'block' : 'table-row' ;
  //console.log(sub_display);
  if(sub_display == 'none'){
	  $(span).removeClass('glyphicon-minus btn-info');
	  $(span).addClass('glyphicon-plus btn-warning');
  }else{
	  $(span).removeClass('glyphicon-plus btn-info');
	  $(span).addClass('glyphicon-minus btn-warning');
  }

  for (i = 0; i < tbl.rows.length; i++)
  {
      var row = tbl.rows[i];
      
      if (row == obj)
      {
          fnd = true;         
      }
      else
      {
          if (fnd == true)
          {
              var cur = parseInt(row.className);
              var icon = 'icon_' + row.id;
              if (cur > lvl)
              {
                  row.style.display = sub_display;
                  if (sub_display != 'none')
                  {
                      var iconimg = document.getElementById(icon);
                      $(iconimg).removeClass('glyphicon-plus btn-info');
                      $(iconimg).addClass('glyphicon-minus btn-warning');
                  }else{               	    
                      $(iconimg).removeClass('glyphicon-minus btn-info');
                      $(iconimg).addClass('glyphicon-plus btn-warning');
                  }
              }
              else
              {
                  fnd = false;
                  break;
              }
          }
      }
  }

  for (i = 0; i < obj.cells[0].childNodes.length; i++)
  {
      var imgObj = obj.cells[0].childNodes[i];
      if (imgObj.tagName == "IMG")
      {
          if($(imgObj).hasClass('glyphicon-plus btn-info')){
        	  $(imgObj).removeClass('glyphicon-plus btn-info');
        	  $(imgObj).addClass('glyphicon-minus btn-warning');
          }else{
        	  $(imgObj).removeClass('glyphicon-minus btn-warning');
        	  $(imgObj).addClass('glyphicon-plus btn-info');
          }
      }
  }

}



/////////显示分类列表的推荐图片
	$(function(){
		for(var i = 1;i <1100; i++) {
			if ("1" == $("#isHot"+i).val()) {
				//$("#imgHot"+i).attr("src","${ctx}/static/images/yes.png");
				document.getElementById("imgHot"+i).src = "${ctx}/static/images/yes.png";

				console.log($("#imgHot").attr("src"));
			} else
				$("#imgHot"+i).attr("src", "${ctx}/static/images/cancel.png");
			if ("1" == $("#isShow"+i).val()) {
				document.getElementById("imgShow"+i).src ="${ctx}/static/images/yes.png";
			}else
				$("#imgShow"+i).attr("src","${ctx}/static/images/cancel.png");
		}

	})


	// 修改分类列表ishot
	function changeIshot(id,isHot,obj)
	{
		var src = "";
		if($(obj).attr('src').indexOf("cancel.png") > 0 )
		{
			src = '${ctx}/static/images/yes.png';
			var hot = 1;
		}else{
			src = '${ctx}/static/images/cancel.png';
			var hot = 0;
		}
		$.ajax({
			url:"${ctx}/goods/category/changeIshot",
			type: "POST",
			data: {
				id: id,
				isHot: hot,
			},
			dataType: "JSON",
			success: function(data){
				$(obj).attr('src',src);
			},
			error:function (result) {
				console.log(result);
			}
		});
	}
	// 修改分类列表ishow
	function changeIsshow(id,isShow,obj)
	{
		var src = "";
		if($(obj).attr('src').indexOf("cancel.png") > 0 )
		{
			src = '${ctx}/static/images/yes.png';
			var show = 1;
		}else{
			src = '${ctx}/static/images/cancel.png';
			var show = 0;
		}
		$.ajax({
			url:"${ctx}/goods/category/changeIsshow",
			type: "POST",
			data: {
				id: id,
				isShow: show,
			},
			dataType: "JSON",
			success: function(data){
				$(obj).attr('src',src);
			},
			error:function (result) {
				console.log(result);
			}
		});
	}
	/// 修改分类列表的分组
	function updateCatGroup(id,obj)
	{
		var value = $(obj).val();
		console.log(value);
		$.ajax({
			url:"${ctx}/goods/category/changeCatGroup",
			type: "POST",
			data: {
				id: id,
				catGroup: value,
			},
			dataType: "JSON",
			success: function(data){
				layer.msg('更新成功', {icon: 1});
			},
			error :function (result) {
				console.log("error");
				console.log(result);
			}
		});
	}
	/// 修改分类列表的分组
	function updateSortOrder(id,obj)
	{
		var value = $(obj).val();
		console.log(value);
		$.ajax({
			url:"${ctx}/goods/category/changeSortOrder",
			type: "POST",
			data: {
				id: id,
				sortOrder: value,
			},
			dataType: "JSON",
			success: function(data){
				layer.msg('更新成功', {icon: 1});
			},
			error :function (result) {
				console.log("error");
				console.log(result);
			}
		});
	}


	/**
	 * 删除
	 * @returns {void}
	 */
	function del_fun(obj,del_url,id)
	{
		if(confirm("确定要删除吗?")) {
			returnResult(null,del_url,id);
		}
	}
	function returnResult(obj,del_url,id) {
		$.ajax({
			type:'post',
			url :del_url+id,
			dataType : 'json',
			success : function(result){
				console.log(result);
				console.log("删除成功");
				if(result[0].id != -1 ){
					for(var i = 0;i < result.length;i++){
						//obj是当使用$(obj).parent().parent().remove();方法是使用的，现无需。
						returnResult(null,del_url,result[i].id);
						console.log(result[i].id);
					}
				}else
					console.log("无子类数据");

				document.getElementById("gcvList"+id).remove();
				//$(obj).parent().parent().remove();
			},
			error: function (err) {
				console.log(err);
			}
		})
	}

</script>
</body>
</html>