package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.entity.Goodstype;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wms
 * @since 2022-11-30
 */
public interface GoodstypeService extends IService<Goodstype> {

    IPage<Goodstype> pageC(IPage<Goodstype> page, Wrapper wrapper);
}
