package com.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.*;
import com.wms.service.MenuService;
import com.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        List list=userService.lambdaQuery()
                .eq(User::getNo,user.getNo())
                .eq(User::getPassword,user.getPassword()).list();
        if(list.size()>0){
            User user1= (User) list.get(0);
            List menuList=menuService.lambdaQuery().like(Menu::getMenuright,user1.getRoleId()).list();
            HashMap hashMap=new HashMap();
            hashMap.put("user",user1);
            hashMap.put("menu",menuList);
            return new Result(Code.OK,"成功",0L,hashMap);
        }
        return new Result(Code.ERR,"失败");
    }

    //账号查询
    @GetMapping("/findByNo")
    public Result findByNo(@RequestParam String no){
        List list=userService.lambdaQuery().eq(User::getNo,no).list();
        return new Result(list.size()>0?Code.OK:Code.ERR,list.size()>0?"成功":"失败",0L,list);
    }

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody User user){
        boolean flag=userService.save(user);
        return new Result(flag? Code.OK:Code.ERR,flag?"成功":"失败");
    }

    //修改
    @PostMapping("/update")
    public Result update(@RequestBody User user){
        boolean flag=userService.updateById(user);
        return new Result(flag? Code.OK:Code.ERR,flag?"成功":"失败");
    }

    //新增或修改
    @PostMapping("/saveOrMod")
    public Result saveOrMod(@RequestBody User user){
        boolean flag=userService.saveOrUpdate(user);
        return new Result(flag? Code.OK:Code.ERR,flag?"成功":"失败");
    }

    //删除
    @GetMapping("/delete")
    public Result delete(Integer id){
        boolean flag=userService.removeById(id);
        return new Result(flag? Code.OK:Code.ERR,flag?"成功":"失败");
    }

    //模糊查询
    @PostMapping("/listP")
    public Result listP(@RequestBody User user){
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(user.getName())){
            lambdaQueryWrapper.like(User::getName,user.getName());
        }

        List<User> list=userService.list(lambdaQueryWrapper);
        Result result=new Result(list!=null? Code.OK:Code.ERR,list!=null?"查询成功":"查询失败，请重试",0L,list);

        return result;
    }

    //分页查询
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query){

        Page<User> page=new Page<>(query.getPageNum(),query.getPageSize());
        String name= (String) query.getParam().get("name");
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(name)&&!"null".equals(name)){
            lambdaQueryWrapper.like(User::getName,name);
        }

        IPage<User> res = userService.page(page, lambdaQueryWrapper);
        List<User> list=res.getRecords();
        Result result=new Result(list!=null? Code.OK:Code.ERR,list!=null?"查询成功":"查询失败，请重试",res.getTotal(),list);

        return result;
    }

    //分页查询
    @PostMapping("/listPageC")
    public Result listPageC(@RequestBody QueryPageParam query){
        Page<User> page=new Page<>(query.getPageNum(),query.getPageSize());

        HashMap param=query.getParam();
        String name= (String) param.get("name");
        String sex= (String) param.get("sex");
        String roleId= (String) param.get("roleId");

        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(name)&&!"null".equals(name)){
            lambdaQueryWrapper.like(User::getName,name);
        }
        if (StringUtils.isNotBlank(sex)){
            lambdaQueryWrapper.eq(User::getSex,sex);
        }
        if (StringUtils.isNotBlank(roleId)){
            lambdaQueryWrapper.eq(User::getRoleId,roleId);
        }

        IPage<User> res = userService.pageC(page, lambdaQueryWrapper);
        List<User> list=res.getRecords();
        Result result=new Result(list!=null? Code.OK:Code.ERR,list!=null?"查询成功":"查询失败，请重试",res.getTotal(),list);

        return result;
    }
}
