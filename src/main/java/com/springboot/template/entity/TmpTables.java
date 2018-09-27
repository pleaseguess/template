package com.springboot.template.entity;

public class TmpTables {
    private Integer id;

    private String wlTableName;

    private String xtTableName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWlTableName() {
        return wlTableName;
    }

    public void setWlTableName(String wlTableName) {
        this.wlTableName = wlTableName == null ? null : wlTableName.trim();
    }

    public String getXtTableName() {
        return xtTableName;
    }

    public void setXtTableName(String xtTableName) {
        this.xtTableName = xtTableName == null ? null : xtTableName.trim();
    }
}