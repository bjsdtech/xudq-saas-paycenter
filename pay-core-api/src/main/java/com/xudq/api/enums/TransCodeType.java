package com.xudq.api.enums;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 支付交易类型
 * @date
 */
public enum TransCodeType {
    FREEZE("00", "冻结"),
    DEFROZEN("01", "解冻"),
    RECEIVE("001", "收款"),
    PAYMENT("002", "收款"),
    DEDUCT("003", "扣款"),
    ;

    private String code;
    private String name;

    TransCodeType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
