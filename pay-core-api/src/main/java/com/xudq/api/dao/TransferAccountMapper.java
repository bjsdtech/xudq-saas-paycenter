package com.xudq.api.dao;

import com.xudq.api.po.TransferAccount;
import com.xudq.api.vo.FlowRequestVO;

import java.util.List;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
public interface TransferAccountMapper {

    int insert(TransferAccount record);

    int insertSelective(TransferAccount record);

    TransferAccount selectByPrimaryKey(Integer id);

    TransferAccount selectByTransferId(String transferId);

    int updateByPrimaryKeySelective(TransferAccount record);

    int updateByPrimaryKey(TransferAccount record);

    int batchInsert(List<TransferAccount> list);

    List<TransferAccount> queryTransferAccounts(FlowRequestVO flowRequestVO);

    int queryTransferAccountsCount(FlowRequestVO flowRequestVO);
}