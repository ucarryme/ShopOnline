<#assign ctx=request.contextPath/>
<!DOCTYPE html>
<html>
<head>
    <#include "../../head.ftl">
    <link rel="stylesheet" href="${ctx}/static/css/fileinput.min.css"></link>
    <script type="text/javascript" src="${ctx}/static/js/fileinput.js"></script>
    <!-- 对中文的支持 -->
    <script type="text/javascript" src="${ctx}/static/js/fileinput_locale_zh.js"></script>
    <script type="text/javascript">

    </script>
    <meta name="__hash__" content="5ab856735c6bdf6e6c05512f732b7cb9_c69aca1884010e29fc472c9ece13ff67"/>
</head>
<body style="background-color:#ecf0f5;">


<div class="wrapper">
    <div class="breadcrumbs" id="breadcrumbs">
        <ol class="breadcrumb">
            <li><a href="javascript:void();"><i class="fa fa-home"></i>&nbsp;&nbsp;后台首页</a></li>

            <li><a href="javascript:void();">商品管理</a></li>
            <li><a href="javascript:void();">添加修改分类</a></li>
        </ol>
    </div>

    <section class="content">
        <div class="row">
            <div class="col-sm-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">增加分类</h3>
                        <div class="pull-right">
                            <a href="javascript:history.go(-1)" data-toggle="tooltip" title="" class="btn btn-default"
                               data-original-title="返回"><i class="fa fa-reply"></i></a>
                            <a href="javascript:;" class="btn btn-default"
                               data-url="http://www.ego.cn/Doc/Index/article/id/1006/developer/user.html"
                               onclick="get_help(this)"><i class="fa fa-question-circle"></i> 帮助</a>
                        </div>
                    </div>
                    <!-- /.box-header -->
                    <form action="" method="post" class="form-horizontal" id="category_form">
                        <div class="box-body">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">分类名称</label>
                                <div class="col-sm-6">
                                    <input type="text" placeholder="名称" class="form-control large" name="name">
                                    <span class="help-inline" style="color:#F00; display:none;" id="err_name"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2">手机分类名称</label>
                                <div class="col-sm-6">
                                    <input type="text" placeholder="手机分类名称" class="form-control large"
                                           name="mobileName">
                                    <span class="help-inline" style="color:#F00; display:none;"
                                          id="err_mobile_name"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label0 class="control-label col-sm-2">上级分类</label0>
                                <input type="text" name="parentId" id="parentId" value="0">
                                <input type="text" name="level" id="level" value="1">
                                <div class="col-sm-3">
                                    <select name="parent_id_1" id="parent_id_1"
                                            onchange="getCategory(this.value,'parent_id_2','0');"
                                            class="small form-control">
                                        <option value="0">顶级分类</option>
                                        <#list gcList as gc>
                                            <option value="${gc.id}">${gc.id}、${gc.name}</option>
                                        </#list>
                                    </select>
                                </div>
                                <div class="col-sm-3">
                                    <select name="parent_id_2" id="parent_id_2" class="small form-control" onchange="setParentId(this.value,'3')">
                                        <option value="0">请选择商品分类</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2">导航显示</label>

                                <div class="col-sm-10">
                                    <label>
                                        <input checked="checked" type="radio" name="isShow" value="1"> 是
                                        <input type="radio" name="isShow" value="0"> 否
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2">分类分组:</label>
                                <div class="col-sm-1">
                                    <select name="catGroup" id="cat_group" class="form-control">
                                        <option value="0">0</option>
                                        <option value='1'>1</option>
                                        "
                                        <option value='2'>2</option>
                                        "
                                        <option value='3'>3</option>
                                        "
                                        <option value='4'>4</option>
                                        "
                                        <option value='5'>5</option>
                                        "
                                        <option value='6'>6</option>
                                        "
                                        <option value='7'>7</option>
                                        "
                                        <option value='8'>8</option>
                                        "
                                        <option value='9'>9</option>
                                        "
                                        <option value='10'>10</option>
                                        "
                                        <option value='11'>11</option>
                                        "
                                        <option value='12'>12</option>
                                        "
                                        <option value='13'>13</option>
                                        "
                                        <option value='14'>14</option>
                                        "
                                        <option value='15'>15</option>
                                        "
                                        <option value='16'>16</option>
                                        "
                                        <option value='17'>17</option>
                                        "
                                        <option value='18'>18</option>
                                        "
                                        <option value='19'>19</option>
                                        "
                                        <option value='20'>20</option>
                                        "
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2">分类展示图片</label>
                                <input type="hidden" id="image" name="image"/>
                                <form enctype="multipart/form-data">
                                    <input id="file-goods-category" class="file" name="file" type="file"
                                           data-min-file-count="1">
                                </form>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2">显示排序</label>
                                <div class="col-sm-1">
                                    <input type="text" placeholder="50" class="form-control large" name="sortOrder"
                                           value="50"/>
                                    <span class="help-inline" style="color:#F00; display:none;"
                                          id="err_sort_order"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2">分佣比例</label>
                                <div class="col-sm-1">
                                    <input type="text" placeholder="50" class="form-control large" name="commissionRate"
                                           id="commission_rate" value="0"
                                           onpaste="this.value=this.value.replace(/[^\d.]/g,'')"
                                           onkeyup="this.value=this.value.replace(/[^\d.]/g,'')"/>
                                </div>
                                <div class="col-sm-1" style="margin-top: 6px;margin-left: -20px;">
                                    <span>%</span>
                                </div>
                            </div>
                        </div>
                        <div class="box-footer">
                            <input type="hidden" name="id" value="1">
                            <button type="reset" class="btn btn-primary pull-left"><i class="icon-ok">重填 </i></button>
                            <button type="button" onclick="ajaxSubmitForm();" class="btn btn-primary pull-right"><i
                                        class="icon-ok"></i>提交
                            </button>
                        </div>
                        <input type="hidden" name="__hash__"
                               value="5ab856735c6bdf6e6c05512f732b7cb9_c69aca1884010e29fc472c9ece13ff67"/>
                    </form>
                </div>
            </div>
        </div>
    </section>
</div>
<script>

    /** 以下是编辑时默认选中某个商品分类*/
    $(document).ready(function () {


    });

</script>
<script>
    /**
     * 获取多级联动的商品分类
     * id:当前选择框的值
     * next：下级选择框显示的内容
     * select_id:level
     */
    function getCategory(id, next, select_id) {
        var url = '${ctx}/goods/category/' + id;
        // 用户重新选择顶级分类时，重置下级分类为：请选择商品分类，且清空下级分类信息
        var htmlStr = "<option value='0'>请选择商品分类</option>";
        //修改parent_id_1

        $("#parentId").val(id);
        if (0 == id) {
            $("#" + next).html(htmlStr);
            //修改level
            $("#level").val(1);
            layer.alert("进来了");
            return;
        }
        $.ajax({
            type: "GET",
            url: url,
            error: function (request) {
                layer.alert("获取子分类失败！");
            },
            success: function (result) {
                if (result.length > 0) {
                    for (i = 0; i < result.length; i++) {
                        htmlStr += "<option value='" + result[i].id + "'>" + result[i].id + "、" + result[i].name + "</option>"
                    }
                    $("#" + next).html(htmlStr);
                    $("#level").val(2);
                } else {
                    $("#level").val(2);
                    layer.alert("无子分类");
                }
            }
        });
    }

    /**
     *保存分类
     */
    function ajaxSubmitForm() {
        $.ajax({
            url: "${ctx}/goods/category/save",
            type: "post",
            data: $("#category_form").serialize(),
            dataType: "JSON",
            success: function (result) {
                if (result.code == 200) {
                    layer.confirm("保存成功", {btn: ['继续新增', '返回列表']},
                        function () {
                            window.location.href = "${ctx}/goods/category/add";
                        }, function () {
                            window.location.href = "${ctx}/goods/category/list";
                        });
                } else {
                    layer.alert("保存失败");
                }
            },
            error: function () {
                layer.alert("保存失败");
            }
        });
    }

    ///=================商品分类图片上传===================///
    /**
     * 初始设置
     *   language指定语言
     *   uploadUrl指定文件上传的后台地址
     *   allowedPreviewTypes允许上传文件的类型
     */
    $('#file-goods-category').fileinput({
        language: 'zh',
        uploadUrl: '${ctx}/fileUpload/save',
        allowedPreviewTypes: ['image', 'html', 'text', 'video', 'audio', 'flash']
    });
    /**
     * 上传文件失败后 调用方法（回调函数）
     */
    $('#file-goods-category').on('fileuploaderror', function (event, data, previewId, index) {
        var form = data.form,
            files = data.files, e
        xtra = data.extra,
            response = data.response,
            reader = data.reader;
        console.log(data);
        console.log('File upload error');
    });
    /**
     * 文件错误 比如文件类型错误 调用方法（回调函数）
     */
    $('#file-goods-category').on('fileerror', function (event, data) {
        console.log(data.id);
        console.log(data.index);
        console.log(data.file);
        console.log(data.reader);
        console.log(data.files);
    });
    /**
     * 文件上传成功后 调用方法（回调函数）
     */
    $('#file-goods-category').on('fileuploaded', function (event, data, previewId, index) {
        var form = data.form,
            files = data.files,
            extra = data.extra,
            response = data.response,
            reader = data.reader;
        // 服务器文件地址
        // alert(data.response.fileUrl);
        // 将服务器文件地址设置至隐藏域
        $("#image").val(data.response.fileUrl);
        console.log('File uploaded triggered');
    });
    ///=================商品分类图片上传===================///
    /**
     * 设置parentId和level
     */
    function setParentId(parentId,level) {
        console.log("fache")
        // 修改parentId和level
        if (0==parentId){
            $("#parentId").val($("#parent_id_1").val);
            $("#level").val(2);
            return;
        }
        // 修改parentId和level
        $("#parentId").val(parentId);
        $("#level").val(level);
    }

</script>
</body>
</html>