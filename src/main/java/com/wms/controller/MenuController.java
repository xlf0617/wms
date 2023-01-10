package com.wms.controller;


import com.wms.entity.Code;
import com.wms.entity.Menu;
import com.wms.entity.Result;
import com.wms.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public Result list(@RequestParam String roleId){
        List list=menuService.lambdaQuery().like(Menu::getMenuright,roleId).list();
        return new Result(list.size()>0? Code.OK:Code.ERR,list.size()>0?"成功":"失败",0L,list);
    }

}
