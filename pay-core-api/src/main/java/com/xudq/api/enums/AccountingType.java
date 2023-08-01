package com.xudq.api.enums;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 会计类型：描述会计借贷方向
 * @date
 */
public enum AccountingType {

    Dr(1, "借",12),
    Cr(-1, "贷",21),
    ;

    private int id;
    private String name;
    private int value;

    AccountingType(int id, String name,int value) {
        this.id = id;
        this.name = name;
        this.value=value;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getValue(){
        return value;
    }
}
