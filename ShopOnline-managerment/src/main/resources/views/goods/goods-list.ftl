<#assign ctx=request.contextPath/>
<html>
<head>
    <#include "../head.ftl">
    <script type="text/javascript">
        function delfunc(obj) {
            layer.confirm('确认删除？', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.ajax({
                        type: 'post',
                        url: $(obj).attr('data-url'),
                        data: {act: 'del', del_id: $(obj).attr('data-id')},
                        dataType: 'json',
                        success: function (data) {
                            if (data == 1) {
                                layer.msg('操作成功', {icon: 1});
                                $(obj).parent().parent().remove();
                            } else {
                                layer.msg(data, {icon: 2, time: 2000});
                            }
                            layer.closeAll();
                        }
                    })
                }, function (index) {
                    layer.close(index);
                    return false;// 取消
                }
            );
        }

        //全选
        function selectAll(name, obj) {
            $('input[name*=' + name + ']').prop('checked', $(obj).checked);
        }

        function get_help(obj) {
            layer.open({
                type: 2,
                title: '帮助手册',
                shadeClose: true,
                shade: 0.3,
                area: ['90%', '90%'],
                content: $(obj).attr('data-url'),
            });
        }

        function delAll(obj, name) {
            var a = [];
            $('input[name*=' + name + ']').each(function (i, o) {
                if ($(o).is(':checked')) {
                    a.push($(o).val());
                }
            })
            if (a.length == 0) {
                layer.alert('请选择删除项', {icon: 2});
                return;
            }
            layer.confirm('确认删除？', {btn: ['确定', '取消']}, function () {
                    $.ajax({
                        type: 'get',
                        url: $(obj).attr('data-url'),
                        data: {act: 'del', del_id: a},
                        dataType: 'json',
                        success: function (data) {
                            if (data == 1) {
                                layer.msg('操作成功', {icon: 1});
                                $('input[name*=' + name + ']').each(function (i, o) {
                                    if ($(o).is(':checked')) {
                                        $(o).parent().parent().remove();
                                    }
                                })
                            } else {
                                layer.msg(data, {icon: 2, time: 2000});
                            }
                            layer.closeAll();
                        }
                    })
                }, function (index) {
                    layer.close(index);
                    return false;// 取消
                }
            );
        }
    </script>



    <!-- 引入doT.js -->
    <script type="text/javascript" src="${ctx}/static/js/doT.min.js"></script>
    <!--引入fy显示-->
    <link rel="stylesheet" href="${ctx}/static/fy-alert/css/fy-alert.css"></link>
    <script type="text/javascript" src="${ctx}/static/fy-alert/js/fy-alert.js"></script>



    <meta name="__hash__" content="934c3c704c4bed5cb4da6cec6353613a_2e4eb53d2afc41d11040df3ef57fa1ca">
</head>
<body style="background-color:#ecf0f5;">


<div class="wrapper">
    <div class="breadcrumbs" id="breadcrumbs">
        <ol class="breadcrumb">
            <li><a href="javascript:void();"><i class="fa fa-home"></i>&nbsp;&nbsp;后台首页</a></li>

            <li><a href="javascript:void();">商品管理</a></li>
            <li><a href="javascript:void();">商品列表</a></li>
        </ol>
    </div>

    <style>#search-form > .form-group {
            margin-left: 10px;
        }</style>
    <!-- Main content -->
    <section class="content">
        <div class="container-fluid">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="fa fa-list"></i> 商品列表</h3>
                </div>
                <div class="panel-body">
                    <div class="navbar navbar-default">
                        <form action="" id="search-form2" class="navbar-form form-inline" method="post"
                              onsubmit="return false">
                            <div class="form-group">
                                <select name="catId" id="catId" class="form-control" onchange="ajax_get_table(1);">
                                    <option value="">所有分类</option>
                                    <#list gcList as gc>
                                        <option value="${gc.id}">${gc.id}、${gc.name}</option>
                                    </#list>
                                </select>
                            </div>
                            <div class="form-group">
                                <select name="brandId" id="brandId" class="form-control" onchange="ajax_get_table(1);">
                                    <option value="">所有品牌</option>
                                    <#list brandList as bL>
                                        <option value="${bL.id}">${bL.id}、${bL.name}</option>
                                    </#list>
                                </select>
                            </div>

                            <div class="form-group">
                                <label class="control-label" for="input-order-id">每页显示</label>
                                <select name="pageSize" id="pageSize" onchange="ajax_get_table(1);"
                                        class="form-control">
                                    <option value="10">10</option>
                                    <option value="20">20</option>
                                    <option value="50">50</option>
                                    <option value="100">100</option>
                                </select>
                                <label class="control-label" for="input-order-id">条</label>
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="input-order-id">关键词</label>
                                <div class="input-group">
                                    <input name="goodsName" id="goodsName" value="" placeholder="搜索词"
                                           class="formcontrol" type="text">
                                </div>
                            </div>
                            <!--排序规则-->
                            <input name="orderby1" value="goods_id" type="hidden">
                            <input name="orderby2" value="desc" type="hidden">
                            <button type="button" onclick="ajax_get_table(1)"
                                    id="button-filter search-order" class="btn btn-primary"><i class="fa fa-search">
                                </i>
                                筛选
                            </button>
                            <button type="button" onclick="location.href='${ctx}/goodsSelf/add'"
                                    class="btn btn-primary pull-right"><i class="fa fa-plus"></i>添加新商品
                            </button>
                            <input name="__hash__"
                                   value="934c3c704c4bed5cb4da6cec6353613a_2e4eb53d2afc41d11040df3ef57fa1ca"
                                   type="hidden"></form>
                    </div>
                    <div id="ajax_return">
                        <form method="post" enctype="multipart/form-data" target="_blank" id="form-order">
                            <div class="table-responsive">
                                <table class="table table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <td style="width: 1px;" class="text-center">
                                        </td>
                                        <td class="text-right">
                                            <a href="javascript:sort('goods_id');">ID</a>
                                        </td>
                                        <td class="text-left">
                                            <a href="javascript:sort('goods_name');">商品名称</a>
                                        </td>
                                        <td class="text-left">
                                            <a href="javascript:sort('goods_sn');">货号</a>
                                        </td>
                                        <td class="text-left">
                                            <a href="javascript:sort('cat_id');">分类</a>
                                        </td>
                                        <td class="text-left">
                                            <a href="javascript:sort('shop_price');">价格</a>
                                        </td>
                                        <td class="text-left">
                                            <a href="javascript:void(0);">库存</a>
                                        </td>
                                        <td class="text-center">
                                            <a href="javascript:sort('sort');">排序</a>
                                        </td>
                                        <td class="text-center" style="width:135px;">操作</td>
                                    </tr>
                                    </thead>
                                    <tbody id="goodsContent">
                                    </tbody>
                                </table>
                            </div>
                            <input name="__hash__"
                                   value="00ea0d70ce1e0760a8bf5d90b5e30971_699560bd02bf6cad1be4e51b170eb190"
                                   type="hidden"></form>
                        <div class="row">
                            <div class="col-sm-3 text-left"></div>
                            <div class="col-sm-9 text-right">
                                <div class="dataTables_paginate paging_simple_numbers">
                                    <ul class="pagination" id="pageContent">
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.row -->
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->



<!---================商品模板========================-->
<!-- 编写商品模板 -->
<script type="template" id="goodsTemplate">
    {{ for(var i = 0; i < it.length; i++){ }}
    <tr id="goodsId{{=it[i].goodsId}}">
        <td class="text-center">
            <input name="shipping_code[]" value="flat.flat" type="hidden">
        </td>
        <td class="text-right">{{=it[i].goodsId}}</td>
        <td class="text-left">{{=it[i].goodsName}}</td>
        <td class="text-left">{{=it[i].goodsSn}}</td>
        <td class="text-left">{{=it[i].catId}}</td>
        <td class="text-left">{{=it[i].shopPrice}}</td>
        <td class="text-left">
            <input onkeyup="this.value=this.value.replace(/[^\d.]/g,'')"
                   onpaste="this.value=this.value.replace(/[^\d.]/g,'')"
                   onchange="ajaxUpdateField(this);" name="store_count" size="4"
                   data-table="goods" data-id="143" value="{{=it[i].storeCount}}"
                   type="text">
        </td>
        <td class="text-center">
            <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')"
                   onpaste="this.value=this.value.replace(/[^\d]/g,'')"
                   onchange="updateSort('goods','goods_id','143','sort',this)" size="4"
                   value="{{=it[i].sort}}" type="text">
        </td>
        <td class="text-center">
            <a target="_blank" href="/index/Home/Goods/goodsInfo/id/143"
               class="btn btn-info" title="查看详情"><i class="fa fa-eye"></i></a>
            <a href="商品列表-添加新商品.html" class="btn btn-primary" title="编辑"><i
                        class="fa fa-pencil"></i></a>
            <a href="javascript:void(0);" onclick="del('{{=it[i].goodsId}}')" class="btn btn-danger"
               title="删除"><i class="fa fa-trash-o"></i></a>
        </td>
    </tr>
    {{ } }}
</script>
<!-- 编写分页模板 -->
<script type="template" id="pageTemplate">
    {{ if(it.hasPreviousPage){ }}
    <li class="paginate_button prev">
        <a href="javascript:ajax_get_table('{{=it.prePage}}');">上一页</a>
    </li>
    {{ } }}
    {{ for(var i = 1; i <= it.pages; i++){ }}
    <li class="paginate_button
 {{ if(i == it.pageNum){ }}
 active
 {{ } }}
 ">
        <a href="javascript:ajax_get_table('{{=i}}');">{{=i}}</a>
    </li>
    {{ } }}
    {{ if(it.hasNextPage){ }}
    <li class="paginate_button next">
        <a href="javascript:ajax_get_table('{{=it.nextPage}}');">下一页</a>
    </li>
    {{ } }}
</script>
<!---================商品模板========================-->


<!---================商品分页显示========================-->
<script>
    var cur_page = 1;
    $(document).ready(function () {

        // ajax 加载商品列表
        ajax_get_table(1);
    });

    //ajax抓取页面 page为当前第几页
    function ajax_get_table(page) {
        cur_page = page;
        console.log($("#catId").val());
        $.ajax({
            url: "${ctx}/goodsSelf/listForPage",
            type: "POST",
            data: {
                catId: $("#catId").val(),
                brandId: $("#brandId").val(),
                goodsName: $("#goodsName").val(),
                pageNum: page,
                pageSize: $("#pageSize").val()
            },
            dataType: "JSON",
            success: function (result) {
                if (200 == result.code) {
                    if (result.pageInfo.list.length > 0) {
                        //获取商品列表模板
                        var goodsTemp = doT.template($("#goodsTemplate").text());
                        //填充数据
                        $("#goodsContent").html(goodsTemp(result.pageInfo.list));
                        //获取分页模板
                        var pageTemp = doT.template($("#pageTemplate").text());
                        //填充数据
                        $("#pageContent").html(pageTemp(result.pageInfo));
                    } else {
                        fyAlert.msg("该分类或品牌暂无商品信息！", {icon: 3, animateType: 3});
                        //获取商品列表模板
                        var goodsTemp = doT.template($("#goodsTemplate").text());
                        //填充数据
                        $("#goodsContent").html(goodsTemp(result.pageInfo.list));
                    }
                } else {
                    fyAlert.msg("该分类或品牌暂无商品信息！", {icon: 3, animateType: 3});
                }
            },
            error: function (result) {
                console.log(result)
            }
        });
    }

    <!---================商品分页显示========================-->


    // 点击排序
    function sort(field) {
        $("input[name='orderby1']").val(field);
        var v = $("input[name='orderby2']").val() == 'desc' ? 'asc' : 'desc';
        $("input[name='orderby2']").val(v);
        ajax_get_table('search-form2', cur_page);
    }

    // 删除操作
    function del(id) {
        if (!confirm('确定要删除吗?'))
            return false;
        console.log(id);
        $.ajax({
            url: "${ctx}/goodsSelf/delete/"+id,
            type: "GET",
            success: function (result) {
                if (200 == result.code) {
                    //document.getElementById("goodsId"+id).remove();
                    console.log("goodsId"+id);
                    console.log("当前页:"+cur_page);
                    ajax_get_table(cur_page);
                }
                else
                    layer.msg(v.msg, {icon: 2, time: 1000}); //alert(v.msg);
            }
        });
        return false;
    }
</script>

</body>
</html>
