package com.xudq.api.po;

import java.io.Serializable;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
public class OperateHistoryWithBLOBs extends OperateHistory implements Serializable {
    /**
     * 原先的实体json
     */
    private String originJson;

    /**
     * 修改后的实体json
     */
    private String newJson;

    /**
     * 修改的数据
     */
    private String difference;

    private static final long serialVersionUID = 1L;

    public String getOriginJson() {
        return originJson;
    }

    public void setOriginJson(String originJson) {
        this.originJson = originJson;
    }

    public String getNewJson() {
        return newJson;
    }

    public void setNewJson(String newJson) {
        this.newJson = newJson;
    }

    public String getDifference() {
        return difference;
    }

    public void setDifference(String difference) {
        this.difference = difference;
    }
}