package com.xudq.api.enums;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 调账类型
 * 0-初始,1-正常,2-冻结,3-悬户,4-销户
 * @date
 */
public enum AdjustmentStatus {

    INIT(0, "初始"),
    CREATE(1, "创建调账审批单"),
    FAIL(2, "调账失败"),
    SUCCESS(5, "调账成功"),
    ;

    private int id;
    private String name;

    AdjustmentStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static AdjustmentStatus getById(int id) {
        for (AdjustmentStatus type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }
}
