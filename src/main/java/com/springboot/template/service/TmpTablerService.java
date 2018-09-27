package com.springboot.template.service;

import com.springboot.template.entity.TmpTables;

/**
 * Created by jinyu on 2018/8/22.
 */
public interface TmpTablerService {
    int deleteByPrimaryKey(Integer id);
    TmpTables selectByPrimaryKey(Integer id);
}
