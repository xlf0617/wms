package com.wms;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.User;
import com.wms.mapper.UserMapper;
import com.wms.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WmsApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
        IPage<User> page=new Page<>(1,3);
        LambdaQueryWrapper<User> lqw=new LambdaQueryWrapper();
        userMapper.pageC(page,lqw).getRecords();
        System.out.println(page.getRecords());
    }

}
