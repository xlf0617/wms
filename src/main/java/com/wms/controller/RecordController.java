package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.*;
import com.wms.service.GoodsService;
import com.wms.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wms
 * @since 2022-12-01
 */
@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @Autowired
    private GoodsService goodsService;

    //出入库
    @PostMapping("/save")
    public Result save(@RequestBody Record record) {
        Goods goods = goodsService.getById(record.getGoods());

        int n = record.getCount();

        if ("2".equals(record.getAction())) {
            if (n>goods.getCount()){
                return new Result(Code.ERR,"库存不足，请重试!");
            }
            n = -n;
            record.setCount(n);
        }
        int num = goods.getCount() + n;
        goods.setCount(num);

        goodsService.updateById(goods);
        boolean flag = recordService.save(record);
        return new Result(flag ? Code.OK : Code.ERR, flag ? "成功" : "失败");
    }

    //分页查询
    @PostMapping("/listPageC")
    public Result listPageC(@RequestBody QueryPageParam query) {
        Page<Record> page = new Page<>(query.getPageNum(), query.getPageSize());

        HashMap param = query.getParam();
        String name = (String) param.get("name");
        String storage = (String) param.get("storage");
        String goodstype = (String) param.get("goodstype");
        String roleId = (String) param.get("roleId");
        String userId = (String) param.get("userId");


        QueryWrapper<Record> queryWrapper = new QueryWrapper();
        queryWrapper.apply(" a.goods=b.id and b.`storage`=c.id and b.goodsType=d.id ");

        if ("2".equals(roleId)) {
            queryWrapper.apply(" a.userId= "+userId);
        }
        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            queryWrapper.like("b.name", name);
        }
        if (StringUtils.isNotBlank(storage) && !"null".equals(storage)) {
            queryWrapper.eq("c.id", storage);
        }
        if (StringUtils.isNotBlank(goodstype) && !"null".equals(goodstype)) {
            queryWrapper.eq("d.id", goodstype);
        }

        IPage<Record> res = recordService.pageC(page, queryWrapper);
        List<Record> list = res.getRecords();
        Result result = new Result(list != null ? Code.OK : Code.ERR, list != null ? "查询成功" : "查询失败，请重试", res.getTotal(), list);

        return result;
    }

}
