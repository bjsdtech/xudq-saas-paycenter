package com.xudq.api.enums;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 会计科目的类型
 * 注1：这里不是严格的会计科目种类
 * 注2：这里不使用拼音，熟悉下会计术语
 * @date
 */

public enum AccountCategory {
    ASSETS(1, "资产类"),
    LIABILITY(2, "负债类"),
    COST(3, "支出类"),
    INCOME(4, "收入类"),
    ;

    private int id;
    private String name;

    AccountCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static AccountCategory getById(int id) {
        for (AccountCategory type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }
}
