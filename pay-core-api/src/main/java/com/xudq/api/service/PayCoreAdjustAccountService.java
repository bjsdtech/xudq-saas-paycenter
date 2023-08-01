package com.xudq.api.service;

import com.xudq.api.dao.PayCoreAdjustAccountMapper;
import com.xudq.api.po.PayCoreAdjustAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description JedisService
 * @date
 */
@Service
public class PayCoreAdjustAccountService {

    @Autowired
    private PayCoreAdjustAccountMapper payCoreAdjustAccountMapper;

    public PayCoreAdjustAccount insertSelective(PayCoreAdjustAccount p){
        payCoreAdjustAccountMapper.insertSelective(p);
        return p;
    }

    public PayCoreAdjustAccount getByBusiIdAndRequestID(String busiId,String requestId){

        return payCoreAdjustAccountMapper.getByBusiIdAndRequestID(busiId,requestId);
    }


    public PayCoreAdjustAccount getUnAdjustmentByAdjustmentId(String adjustmentId) {
        return payCoreAdjustAccountMapper.getUnAdjustmentByAdjustmentId(adjustmentId);
    }

    public int updateByPrimaryKeySelective(PayCoreAdjustAccount p){
        return payCoreAdjustAccountMapper.updateByPrimaryKeySelective(p);
    }
}
