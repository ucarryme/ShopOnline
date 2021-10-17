package com.cuit.portal.vo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
public class CartVo implements Serializable {
    private static final long serialVersionUID = -196260271603271346L;
    // 商品ID
    private Integer goodsId;
    // 商品名称
    private String goodsName;
    // 商品价格
    private BigDecimal marketPrice;
    // 商品图片
    private String originalImg;
    // 商品数量
    private Integer goodsNum;
    // 商品添加时间
    private Date addTime;
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
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }
    public String getOriginalImg() {   return originalImg;
    }
    public void setOriginalImg(String originalImg) {
        this.originalImg = originalImg;
    }
    public Integer getGoodsNum() {
        return goodsNum;
    }
    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    @Override
    public String toString() {
        return "CartVo{" +
                "goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", marketPrice=" + marketPrice +
                ", originalImg='" + originalImg + '\'' +
                ", goodsNum=" + goodsNum +
                ", addTime=" + addTime +
                '}';
    }
}
