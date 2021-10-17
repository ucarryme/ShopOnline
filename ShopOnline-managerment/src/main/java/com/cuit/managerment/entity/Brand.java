package com.cuit.managerment.entity;

import java.io.Serializable;

/**
 * @since 1.0.0
 */
public class Brand implements Serializable {
    /**
     * 品牌表
     */
    private Short id;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌logo
     */
    private String logo;

    /**
     * 品牌地址
     */
    private String url;

    /**
     * 排序
     */
    private Byte sort;

    /**
     * 品牌分类
     */
    private String catName;

    /**
     * 分类id
     */
    private Integer parentCatId;

    /**
     * 分类id
     */
    private Integer catId;

    /**
     * 是否推荐
     */
    private Byte isHot;

    /**
     * 品牌描述
     */
    private String desc;

    /**
     * t_brand
     */
    private static final long serialVersionUID = 1L;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Byte getSort() {
        return sort;
    }

    public void setSort(Byte sort) {
        this.sort = sort;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName == null ? null : catName.trim();
    }

    public Integer getParentCatId() {
        return parentCatId;
    }

    public void setParentCatId(Integer parentCatId) {
        this.parentCatId = parentCatId;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Byte getIsHot() {
        return isHot;
    }

    public void setIsHot(Byte isHot) {
        this.isHot = isHot;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", logo=").append(logo);
        sb.append(", url=").append(url);
        sb.append(", sort=").append(sort);
        sb.append(", catName=").append(catName);
        sb.append(", parentCatId=").append(parentCatId);
        sb.append(", catId=").append(catId);
        sb.append(", isHot=").append(isHot);
        sb.append(", desc=").append(desc);
        sb.append("]");
        return sb.toString();
    }
}