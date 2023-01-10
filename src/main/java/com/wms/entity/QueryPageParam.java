package com.wms.entity;

import lombok.Data;

import java.util.HashMap;

@Data
public class QueryPageParam {
    private static Integer PAGE_SIZE=20;
    private static Integer PAGE_NUM=1;

    private Integer pageSize=PAGE_SIZE;
    private Integer pageNum=PAGE_NUM;
    private HashMap param=new HashMap();

}
