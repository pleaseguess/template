package com.springboot.template.service.impl;

import com.springboot.template.entity.TmpTables;
import com.springboot.template.mapper.TmpTablesMapper;
import com.springboot.template.service.TmpTablerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jinyu on 2018/8/22.
 */
@Service
public class TmpTableServiceImpl implements TmpTablerService{

    @Autowired
    private TmpTablesMapper tmpTablesMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public TmpTables selectByPrimaryKey(Integer id) {
        return tmpTablesMapper.selectByPrimaryKey(id);
    }
}
