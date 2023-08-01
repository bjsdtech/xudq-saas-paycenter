package com.xudq.api.service;

import com.google.common.collect.Lists;
import com.xudq.api.dao.TransferAccountMapper;
import com.xudq.api.enums.AccountType;
import com.xudq.api.exceptions.BusinessException;
import com.xudq.api.po.TransferAccount;
import com.xudq.api.vo.AcmAccountPayDetail;
import com.xudq.api.vo.FlowRequestVO;
import com.xudq.api.vo.TransferAccountVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 转账交易表的各种操作
 * @date
 */
@Service
public class TransferAccountService {

    private static final Logger logger = LoggerFactory.getLogger(TransferAccountService.class);

    @Autowired
    private TransferAccountMapper transferAccountMapper;

    @Autowired
    private AcmService acmService;

    /**
     * 流水进入转账表
     * */
    public TransferAccount insert(TransferAccountVO transferAccountVO) throws Exception {
        logger.info("扣款流水进扣款表， params = {}", transferAccountVO);

        //transfer transferAccountVO to transferAccount
        TransferAccount transferAccount = transferAccountVO2TransferAccount(transferAccountVO);
        //未转账
        transferAccount.setTransferStatus(1);

        // 判断是否重复的key
        TransferAccount transferHis = queryByTransferId(transferAccountVO.getBusinessOrderId());
        if (transferHis != null) {
            logger.error("businessOrderId已存在 businessOrderId = {}", transferAccountVO.getBusinessOrderId());
            throw new BusinessException("businessOrderId已存在");
        }

        int result = transferAccountMapper.insertSelective(transferAccount);

        if(result <= 0){
            logger.error("扣款流水进扣款表失败");
            throw new Exception("扣款流水进扣款表失败");
        }
        logger.info("insert transferAccount into db success, params = {}, affact row = {}", transferAccount, result);
        return transferAccount;
    }

    /**
     * 更新转账表的状态
     * */
    public void update(TransferAccount transferAccount) throws Exception {
        logger.info("修改扣款单状态， params = {}", transferAccount);

        //转账成功
        transferAccount.setTransferStatus(2);
        Date date = new Date();
        transferAccount.setLastModifyTime(date);
        transferAccount.setTransferTime(date);
        int result = transferAccountMapper.updateByPrimaryKeySelective(transferAccount);

        if(result <= 0){
            logger.error("修改扣款单状态失败");
            throw new Exception("修改扣款单状态失败");
        }
        logger.info("update transferAccount into db success, params = {}, affact row = {}", transferAccount, result);
    }


    /**
     * query transferAccount by transferId
     * */
    public TransferAccount queryByTransferId(String transferId){
        TransferAccount transferAccount = transferAccountMapper.selectByTransferId(transferId);
        logger.info("query by transfer id success,params = {}, result = {}", transferId, transferAccount);
        return transferAccount;
    }


    /**
     * transferAccountVO 2 TransferAccount
     * */
    public TransferAccount transferAccountVO2TransferAccount(TransferAccountVO transferAccountVO){
        //转换实体，填充 transferAccount
        TransferAccount transferAccount = new TransferAccount();
        transferAccount.setBusinessOrderId(transferAccountVO.getBusinessOrderId());
        transferAccount.setTransferId(transferAccountVO.getBusinessOrderId());
        transferAccount.setOrderId(transferAccountVO.getOrderId());
        transferAccount.setAmount(transferAccountVO.getAmount());
        String comUserId = transferAccountVO.getComUserId();
        //根据source获取业务线信息
        String source = transferAccountVO.getSource();
        String accountId = acmService.getAccountIdByComId(comUserId);
        transferAccount.setFromAccountId(Long.valueOf(accountId));
        transferAccount.setFromAccountType((short) AccountType.U102.getId());
        transferAccount.setToAccountId((long) 1000001002);
        transferAccount.setToAccountType((short) AccountType.B466.getId());
        transferAccount.setTransCode("003");

        transferAccount.setTransferType(2);
        transferAccount.setComUserId(comUserId);
        transferAccount.setLastModifyTime(new Date());
        transferAccount.setFrozenId(transferAccountVO.getFrozenId());
        return transferAccount;
    }


    /**
     * 查询支付流水扣款记录
     * */
    public List<TransferAccount> query(FlowRequestVO flowRequestVO) throws Exception {
        logger.info("查询支付流水扣款记录， params = {}", flowRequestVO);
        List<TransferAccount> result = transferAccountMapper.queryTransferAccounts(flowRequestVO);
        if (result == null  || result.size() <= 0) {
            return Lists.newArrayList();
        }
        List<String> payCoreIds = Lists.transform(result, input -> input.getBusinessOrderId());
        Map<String, Object> voMap = acmService.queryPayDetail(payCoreIds);
        for (TransferAccount transferAccount : result) {
            String key = transferAccount.getBusinessOrderId();
            if (voMap.containsKey(key)) {
                AcmAccountPayDetail detail = (AcmAccountPayDetail) voMap.get(key);
                if (detail.getAccountType() == AccountType.U102.getId()) {
                    transferAccount.setBalanceAmount(detail.getAfterAmount());
                    transferAccount.setFrozenAmount(detail.getAfterFrozenAmount());
                }
            }
        }
        logger.info("query transferAccounts from db success, params = {}, result = {}", flowRequestVO, result);
        return result;
    }

    /**
     * 查询支付流水扣款记录总条数
     * */
    public int queryCount(FlowRequestVO flowRequestVO) throws Exception {
        logger.info("查询支付流水扣款记录总条数， params = {}", flowRequestVO);
        int count = transferAccountMapper.queryTransferAccountsCount(flowRequestVO);
        logger.info("query transferAccounts count from db success, params = {}, result = {}", flowRequestVO, count);
        return count;
    }

}
