package com.cuit.managerment.service;

import com.cuit.managerment.entity.Brand;

import java.util.List;

public interface BrandService {
    /**
     * 查询所有的品牌
     * @return
     */
    public List<Brand> selectAllBrands();
}
