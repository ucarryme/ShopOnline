package com.cuit.managerment.service.implet;

import com.cuit.managerment.entity.Brand;
import com.cuit.managerment.entity.BrandExample;
import com.cuit.managerment.mapper.BrandMapper;
import com.cuit.managerment.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BrandServiceImple implements BrandService {
    @Autowired
    private  BrandMapper brandMapper;
    @Override
    /**
     * 查询所有的品牌
     * @return
     */
    public List<Brand> selectAllBrands() {

        return brandMapper.selectByExample(new BrandExample());
    }
}
