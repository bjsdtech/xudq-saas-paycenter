package com.xudq.api.enums;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 业务类型枚举
 * @date
 */
public enum PayServiceType {

    PERCASHSHORT("035001", "调账-个人现金短款"),
    PERCASHLONG("035002", "调账-个人现金长款"),
    PERDEPOSITSHORT("035021", "调账-个人保证金短款"),
    PERDEPOSITLONG("035022", "调账-个人保证金长款"),
    ASSETSTRANSFERACCOUNT("035991", "资产类同账户之间转账"),
    DEBTTRANSFERACCOUNT("035992", "负债类同账户之间转账"),
    ;

    private String  id;
    private String name;

    PayServiceType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static PayServiceType getById(String id) {
        for (PayServiceType type : values()) {
            if (type.id.equals(id)) {
                return type;
            }
        }
        return null;
    }
}
