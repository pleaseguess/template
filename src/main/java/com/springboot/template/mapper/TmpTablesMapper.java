package com.springboot.template.mapper;

import com.springboot.template.entity.TmpTables;

public interface TmpTablesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TmpTables record);

    int insertSelective(TmpTables record);

    TmpTables selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TmpTables record);

    int updateByPrimaryKey(TmpTables record);
}