package com.chengzg.wxshop.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dhy on 2017-06-15.
 */
public enum NoType {
    /**
     * 否
     */
    NO("否", 1),
    /**
     * 是
     */
    YES("是", 0),
    ;
    private String name;
    private int index;

    private NoType(String name, int index) {
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
        for (NoType s : EnumSet.allOf(NoType.class)) {
            lookup.put(s.getIndex(), s.getName());
        }
    }
}
