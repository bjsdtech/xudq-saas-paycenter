package com.xudq.api.dao;

import com.xudq.api.po.Payment;
import java.util.List;

import com.xudq.api.vo.FlowRequestVO;
import org.apache.ibatis.annotations.Param;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 对账调用，修改流水的状态并发起记账
 * @date
 *//**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
public interface PaymentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Payment record);

    int insertSelective(Payment record);

    Payment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Payment record);

    int updateByPrimaryKey(Payment record);

    int batchInsert(@Param("list") List<Payment> list);

    Payment selectByPaymentId(String paymentId);

    List<Payment> queryPayment(FlowRequestVO flowRequestVO);

    int queryPaymentCount(FlowRequestVO flowRequestVO);
}