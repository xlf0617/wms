package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.*;
import com.wms.service.GoodsService;
import com.wms.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wms
 * @since 2022-12-01
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private RecordService recordService;

    //新增或修改
    @PostMapping("/saveOrMod")
    public Result saveOrMod(@RequestBody Goods goods){
        boolean flag=goodsService.saveOrUpdate(goods);
        if (flag){
            Record record=new Record();
            record.setGoods(goods.getId());
            record.setCount(goods.getCount());
            record.setAdminId(goods.getAdminid());
            record.setUserid(goods.getAdminid());
            recordService.save(record);
        }
        return new Result(flag? Code.OK:Code.ERR,flag?"成功":"失败");
    }

    @GetMapping("/delete")
    public Result delete(Integer id){
        boolean flag=goodsService.removeById(id);
        return new Result(flag? Code.OK:Code.ERR,flag?"成功":"失败");
    }

    //分页查询
    @PostMapping("/listPageC")
    public Result listPageC(@RequestBody QueryPageParam query){
        Page<Goods> page=new Page<>(query.getPageNum(),query.getPageSize());

        HashMap param=query.getParam();
        String name= (String) param.get("name");
        String storage= (String) param.get("storage");
        String goodstype= (String) param.get("goodstype");


        LambdaQueryWrapper<Goods> lambdaQueryWrapper=new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(name)&&!"null".equals(name)){
            lambdaQueryWrapper.like(Goods::getName,name);
        }
        if (StringUtils.isNotBlank(storage)&&!"null".equals(storage)){
            lambdaQueryWrapper.eq(Goods::getStorage,storage);
        }
        if (StringUtils.isNotBlank(goodstype)&&!"null".equals(goodstype)){
            lambdaQueryWrapper.eq(Goods::getGoodstype,goodstype);
        }

        IPage<Goods> res = goodsService.pageC(page, lambdaQueryWrapper);
        List<Goods> list=res.getRecords();
        Result result=new Result(list!=null? Code.OK:Code.ERR,list!=null?"查询成功":"查询失败，请重试",res.getTotal(),list);

        return result;
    }
    
}
