package com.xudq.api.service;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
public interface IOperateHistoryService<T> {

    <T> void insertOperateHistory(String entityId, String type, T oldPO, T newPO, String operateId, String operateName) throws Exception;
}
