package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.Code;
import com.wms.entity.QueryPageParam;
import com.wms.entity.Result;
import com.wms.entity.Storage;
import com.wms.service.StorageService;
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
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/list")
    public Result list(){
        List list=storageService.list();
        return new Result(list.size()>0? Code.OK:Code.ERR,list.size()>0?"成功":"失败",0L,list);
    }

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Storage storage){
        boolean flag=storageService.save(storage);
        return new Result(flag? Code.OK:Code.ERR,flag?"成功":"失败");
    }

    //修改
    @PostMapping("/update")
    public Result update(@RequestBody Storage storage){
        boolean flag=storageService.updateById(storage);
        return new Result(flag? Code.OK:Code.ERR,flag?"成功":"失败");
    }

    //新增或修改
    @PostMapping("/saveOrMod")
    public Result saveOrMod(@RequestBody Storage storage){
        boolean flag=storageService.saveOrUpdate(storage);
        return new Result(flag? Code.OK:Code.ERR,flag?"成功":"失败");
    }

    //删除
    @GetMapping("/delete")
    public Result delete(Integer id){
        boolean flag=storageService.removeById(id);
        return new Result(flag? Code.OK:Code.ERR,flag?"成功":"失败");
    }

    //分页查询
    @PostMapping("/listPageC")
    public Result listPageC(@RequestBody QueryPageParam query){
        Page<Storage> page=new Page<>(query.getPageNum(),query.getPageSize());

        HashMap param=query.getParam();
        String name= (String) param.get("name");

        LambdaQueryWrapper<Storage> lambdaQueryWrapper=new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(name)&&!"null".equals(name)){
            lambdaQueryWrapper.like(Storage::getName,name);
        }

        IPage<Storage> res = storageService.pageC(page, lambdaQueryWrapper);
        List<Storage> list=res.getRecords();
        Result result=new Result(list!=null? Code.OK:Code.ERR,list!=null?"查询成功":"查询失败，请重试",res.getTotal(),list);

        return result;
    }
}
