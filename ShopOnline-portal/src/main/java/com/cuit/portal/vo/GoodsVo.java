package com.cuit.portal.vo;

import java.io.Serializable;
import java.math.BigDecimal;
public class GoodsVo implements Serializable {
    private static final long serialVersionUID = -1905915184535584387L;
    private Integer goodsId;
    private String goodsName;
    private String goodsNameHl;
    private BigDecimal marketPrice;
    private String originalImg;

    public GoodsVo() {
    }

    public GoodsVo(Integer goodsId, String goodsName, String goodsNameHl, BigDecimal
            marketPrice, String originalImg) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsNameHl = goodsNameHl;
        this.marketPrice = marketPrice;
        this.originalImg = originalImg;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsNameHl() {
        return goodsNameHl;
    }

    public void setGoodsNameHl(String goodsNameHl) {
        this.goodsNameHl = goodsNameHl;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getOriginalImg() {
        return originalImg;
    }

    public void setOriginalImg(String originalImg) {
        this.originalImg = originalImg;
    }

    @Override
    public String toString() {
        return "GoodsVo{" +
                "goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsNameHl='" + goodsNameHl + '\'' +
                ", marketPrice=" + marketPrice +
                ", originalImg='" + originalImg + '\'' +
                '}';
    }
}
