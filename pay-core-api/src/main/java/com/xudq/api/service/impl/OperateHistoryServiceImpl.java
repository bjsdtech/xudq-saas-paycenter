package com.xudq.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.xudq.api.dao.OperateHistoryMapper;
import com.xudq.api.po.OperateHistory;
import com.xudq.api.po.OperateHistoryWithBLOBs;
import com.xudq.api.service.IOperateHistoryService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
@Service
public class OperateHistoryServiceImpl implements IOperateHistoryService<OperateHistory> {

    private static final Logger logger = LoggerFactory.getLogger(OperateHistoryServiceImpl.class);

    @Resource
    private OperateHistoryMapper operateHistoryMapper;

    @Override
    public <T> void insertOperateHistory(String entityId, String type, T oldPO, T newPO, String operateId, String operateName) throws Exception {
        OperateHistoryWithBLOBs operateHistory = buildOperateHistory(entityId, type, oldPO, newPO, operateId, operateName, null);
        int affactRow = operateHistoryMapper.insertSelective(operateHistory);
        if (affactRow <= 0) {
            throw new Exception("操作记录插入数据库失败");
        }
        logger.info("insert operate history into db success, operate = update, affect row = {}, entity_id = {}, type = {}",
                affactRow, operateHistory.getEntityId(), operateHistory.getType());
    }

    public <T> OperateHistoryWithBLOBs buildOperateHistory(String entityId, String type, T oldPO, T newPO, String operateId, String operateName, String difference) throws IllegalAccessException {
        if (oldPO == null) {
            difference = "INSERT";
        } else {
            difference = getModifyList(oldPO, newPO);
        }
        String oldStr;
        if (oldPO == null) {
            oldStr = "";
        } else {
            oldStr = JSON.toJSONString(oldPO);
        }
        String newStr;
        if (newPO == null) {
            newStr = "";
        } else {
            newStr = JSON.toJSONString(newPO);
        }
        OperateHistoryWithBLOBs operateHistory = new OperateHistoryWithBLOBs();
        operateHistory.setType(type);
        operateHistory.setEntityId(entityId);
        operateHistory.setOperatorId(operateId);
        operateHistory.setOperatorName(operateName);
        operateHistory.setOriginJson(oldStr);
        operateHistory.setNewJson(newStr);
        operateHistory.setDifference(difference);
        return operateHistory;
    }

    public static <T> String getModifyList(T oldPO, T newPO) throws IllegalAccessException {
        if (oldPO == null || newPO == null) {
            return null;
        }
        String result = comparePojo(oldPO, newPO);
        if (result == null || result.isEmpty()) {
            return null;
        } else {
            return StringUtils.substringBeforeLast(result, ",");
        }
    }

    // 比较两个pojo,并给出结论
    private static <T> String comparePojo(T oldPojo, T newPojo)
            throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();
        Field[] fields = oldPojo.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object oldObj = field.get(oldPojo) == null ? "NULL" : field.get(oldPojo);
            Object newObj = field.get(newPojo) == null ? "NULL" : field.get(newPojo);
            if (oldObj.equals(newObj)) {
                continue;
            } else {
                String s = underLineName(fieldName) + ":" + oldObj + "-" + newObj;
                if (StringUtils.isEmpty(s)) {
                    continue;
                } else {
                    builder.append(s).append(",");
                }
            }
        }
        return StringUtils.substringBeforeLast(builder.toString(), ",");
    }

    public static String underLineName(String camelCaseName) {
        StringBuilder result = new StringBuilder();
        if (camelCaseName != null && camelCaseName.length() > 0) {
            result.append(camelCaseName.substring(0, 1).toLowerCase());
            for (int i = 1; i < camelCaseName.length(); i++) {
                char ch = camelCaseName.charAt(i);
                if (Character.isUpperCase(ch)) {
                    result.append("_");
                    result.append(Character.toLowerCase(ch));
                } else {
                    result.append(ch);
                }
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String a = "user_name:lll-lllxxx,email:aa.qom-111,last_login_ip:NULL-1sdjkaj,";
        System.out.println(StringUtils.substringBeforeLast(a, ","));
    }


}
