package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.Code;
import com.wms.entity.Goodstype;
import com.wms.entity.QueryPageParam;
import com.wms.entity.Result;
import com.wms.entity.Storage;
import com.wms.service.GoodstypeService;
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
 * @since 2022-11-30
 */
@RestController
@RequestMapping("/goodstype")
public class GoodstypeController {

    @Autowired
    private GoodstypeService goodstypeService;

    @GetMapping("/list")
    public Result list(){
        List list=goodstypeService.list();
        return new Result(list.size()>0? Code.OK:Code.ERR,list.size()>0?"成功":"失败",0L,list);
    }

    //新增或修改
    @PostMapping("/saveOrMod")
    public Result saveOrMod(@RequestBody Goodstype goodstype){
        boolean flag=goodstypeService.saveOrUpdate(goodstype);
        return new Result(flag? Code.OK:Code.ERR,flag?"成功":"失败");
    }

    @GetMapping("/delete")
    public Result delete(Integer id){
        boolean flag=goodstypeService.removeById(id);
        return new Result(flag? Code.OK:Code.ERR,flag?"成功":"失败");
    }

    //分页查询
    @PostMapping("/listPageC")
    public Result listPageC(@RequestBody QueryPageParam query){
        Page<Goodstype> page=new Page<>(query.getPageNum(),query.getPageSize());

        HashMap param=query.getParam();
        String name= (String) param.get("name");

        LambdaQueryWrapper<Goodstype> lambdaQueryWrapper=new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(name)&&!"null".equals(name)){
            lambdaQueryWrapper.like(Goodstype::getName,name);
        }

        IPage<Goodstype> res = goodstypeService.pageC(page, lambdaQueryWrapper);
        List<Goodstype> list=res.getRecords();
        Result result=new Result(list!=null? Code.OK:Code.ERR,list!=null?"查询成功":"查询失败，请重试",res.getTotal(),list);

        return result;
    }

}
