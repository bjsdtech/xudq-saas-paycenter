package com.xudq.api.enums;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 账户状态
 * 0-初始,1-正常,2-冻结,3-悬户,4-销户
 * @date
 */
public enum AccountStatus {

    INIT(0, "初始"),
    NORMAL(1, "正常"),
    FROZEN(2, "冻结"),
    UNUSED(3, "悬户"),
    CLOSE(4, "销户"),
    ;

    private int id;
    private String name;

    AccountStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static AccountStatus getById(int id) {
        for (AccountStatus type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }
}
