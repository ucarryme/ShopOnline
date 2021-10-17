<!-- 设置项目根路径全局变量 -->
<#assign ctx=request.contextPath/>
<input type="hidden" id="userCartHasUserName" value="${(users.email)!''}"/>
<div id="s_cart">
    <ul>
        <li class="nums">
            <a href="${ctx}/cart/showCart">购物车： <span id="s_cart_nums1">0</span> 件</a>
            <a href="" class="btn" id="s_cart_nums2"></a>
        </li>
        <li class="checkout">
            <a href="#">去结算&gt;&gt;</a>
        </li>
    </ul>
</div>
<script type="text/javascript">
    $(function () {
        // 获取购物车数量
        if ($("#userCartHasUserName").val())
            getCartNum();
    });
    // 添加至购物车
    function addToCart(goodsId, goodsName, marketPrice, originalImg) {
        console.log("goodsName"+goodsName);
        $.ajax({
            url: "${ctx}/cart/addCart",
            type: "POST",
            data: {
                goodsId: goodsId,
                goodsName: goodsName,
                marketPrice: marketPrice,
                originalImg: originalImg,
                goodsNum: 1
            },
            dataType: "JSON",
            success: function (result) {
                if (200 == result.code) {
                    var num = parseInt($("#s_cart_nums1").text());
                    $("#s_cart_nums1").text(num + 1);
                    layer.msg("添加至购物车成功");
                }
            },
            error: function (result) {
                console.log(result);
                // status=200 statusText="OK"说明请求正常，无返回结果
                if (200 == result.status) {
                    // 跳转登录页面
                    location.href = "${ctx}/login";
                } else {
                    // 系统真的出错了
                    layer.alert("亲，系统正在升级中，请稍后再试！");
                }
            }
        });
    }
    // 获取购物车数量
    function getCartNum() {
        $.ajax({
            url: "${ctx}/cart/getCartNum",
            type: "POST",
            dataType: "JSON",
            success: function (result) {
                $("#s_cart_nums1").text(result);
            },
            error: function (result) {
                layer.alert("亲，系统正在升级中，请稍后再试！");
            }
        });
    }
</script>
