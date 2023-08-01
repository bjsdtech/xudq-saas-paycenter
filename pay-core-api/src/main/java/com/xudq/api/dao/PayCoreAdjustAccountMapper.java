package com.xudq.api.dao;


import com.xudq.api.po.PayCoreAdjustAccount;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
public interface PayCoreAdjustAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PayCoreAdjustAccount record);

    int insertSelective(PayCoreAdjustAccount record);

    PayCoreAdjustAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PayCoreAdjustAccount record);

    int updateByPrimaryKey(PayCoreAdjustAccount record);

    PayCoreAdjustAccount getByBusiIdAndRequestID(String busiId,String requestId);

    PayCoreAdjustAccount getUnAdjustmentByAdjustmentId(String adjustmentId);
}