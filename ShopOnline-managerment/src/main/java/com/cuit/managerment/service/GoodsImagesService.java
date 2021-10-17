package com.cuit.managerment.service;

import com.cuit.common.result.BaseResult;
import com.cuit.managerment.entity.GoodsImages;
import org.springframework.stereotype.Service;


/**
 * 保存goodsimages
 */
public interface GoodsImagesService {
    BaseResult saveGoodsImages(GoodsImages goodsImages);

}
