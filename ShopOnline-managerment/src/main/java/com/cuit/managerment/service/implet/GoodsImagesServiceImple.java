package com.cuit.managerment.service.implet;

import com.cuit.common.result.BaseResult;
import com.cuit.managerment.entity.GoodsImages;
import com.cuit.managerment.mapper.GoodsImagesMapper;
import com.cuit.managerment.service.GoodsImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsImagesServiceImple implements GoodsImagesService {
    @Autowired
    private GoodsImagesMapper goodsImagesMapper;

    /**
     * 保存goodsimages
     * @param goodsImages
     * @return
     */
    @Override
    public BaseResult saveGoodsImages(GoodsImages goodsImages) {
        int result = goodsImagesMapper.insertSelective(goodsImages);
        return result>0?BaseResult.success():BaseResult.error();
    }
}
