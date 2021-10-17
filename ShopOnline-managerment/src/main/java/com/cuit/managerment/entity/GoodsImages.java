package com.cuit.managerment.entity;

import java.io.Serializable;

/**
 * @author zhoubin 
 * @since 1.0.0
 */
public class GoodsImages implements Serializable {
    /**
     * 图片id 自增
     */
    private Integer imgId;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 图片地址
     */
    private String imageUrl;

    /**
     * t_goods_images
     */
    private static final long serialVersionUID = 1L;

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", imgId=").append(imgId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append("]");
        return sb.toString();
    }
}