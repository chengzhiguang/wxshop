package com.chengzg.wxshop.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by czg on 2016-07-20.
 */
public enum OkType {
    /**
     * 启用
     */
    NO("启用", 0),
    /**
     * 禁用
     */
    YES("禁用", 1),
    ;
    private String name;
    private int index;

    private OkType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * enum lookup map
     */
    public static final Map<Integer, String> lookup = new HashMap<Integer, String>();

    static {
        for (OkType s : EnumSet.allOf(OkType.class)) {
            lookup.put(s.getIndex(), s.getName());
        }
    }
}
