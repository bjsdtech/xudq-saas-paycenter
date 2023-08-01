package com.xudq.api.enums;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 用户类型
 * 1-个人用户，2-企业用户（也可以叫商户）
 * @date
 */
public enum UserType {

    PERSON(1, "个人"),
    MERCHANT(2, "商户"),
    ;

    private int id;
    private String name;

    UserType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static UserType getById(int id) {
        for (UserType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }

}
