package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.entity.Goodstype;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wms
 * @since 2022-12-01
 */
public interface GoodsService extends IService<Goods> {

    IPage<Goods> pageC(IPage<Goods> page, Wrapper wrapper);

}
