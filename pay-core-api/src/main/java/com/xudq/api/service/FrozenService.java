package com.xudq.api.service;

import com.google.common.collect.Lists;
import com.xudq.api.dao.FrozenPOMapper;
import com.xudq.api.enums.AccountType;
import com.xudq.api.exceptions.BusinessException;
import com.xudq.api.po.AcmFrozenBalance;
import com.xudq.api.po.FrozenPO;
import com.xudq.api.vo.AcmAccountPayDetail;
import com.xudq.api.vo.FlowRequestVO;
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
 * @Description 调用冻结服务，来冻结、解冻
 * @date
 */
@Service
public class FrozenService {

    private static final Logger logger = LoggerFactory.getLogger(FrozenService.class);

    @Autowired
    private FrozenPOMapper frozenPOMapper;

    @Autowired
    private AcmService acmService;
    /**
     * 冻结、解冻流水进冻结表
     */
    public FrozenPO insert(AcmFrozenBalance acmFrozenBalance) throws Exception {
        logger.info("冻结流水进入冻结单表， params = {}", acmFrozenBalance);

        //transfer acmFrozenBalance to frozenPO
        FrozenPO frozenPO = acmFrozenBalance2FrozenPO(acmFrozenBalance);


        // 判断是否重复的key
        FrozenPO frozenPOHis = queryByFrozenId(acmFrozenBalance.getBusinessOrderId());
        if (frozenPOHis != null) {
            logger.error("businessOrderId已存在 businessOrderId = {}", acmFrozenBalance.getBusinessOrderId());
            throw new BusinessException("businessOrderId已存在");
        }


        int result = frozenPOMapper.insertSelective(frozenPO);

        if(result <= 0){
            logger.error("冻结、解冻流水进入冻结单表失败");
            throw new Exception("冻结、解冻流水进入冻结单表失败");
        }
        logger.info("insert frozen into db success, params = {}, affact row = {}", frozenPO, result);
        return frozenPO;
    }


    public FrozenPO insertUnFrozenRecord(AcmFrozenBalance acmFrozenBalance) throws Exception {
        logger.info("解冻流水进入冻结单表， params = {}", acmFrozenBalance);

        //transfer acmFrozenBalance to frozenPO
        FrozenPO frozenPO = acmFrozenBalance2FrozenPO(acmFrozenBalance);

        // 判断是否重复的key
        FrozenPO frozenPOHis = queryByFrozenId(acmFrozenBalance.getBusinessOrderId());

        if(null == frozenPOHis){
            int result = frozenPOMapper.insertSelective(frozenPO);
            if(result <= 0){
                logger.error("冻结、解冻流水进入冻结单表失败");
                throw new Exception("冻结、解冻流水进入冻结单表失败");
            }
            logger.info("insert unfrozen into db success, params = {}, affact row = {}", frozenPO, result);
            return frozenPO;
        }

        if (frozenPOHis.getFrozenStatus()==4 || frozenPOHis.getFrozenStatus() == 2) {
            logger.error("businessOrderId已存在 businessOrderId = {}", acmFrozenBalance.getBusinessOrderId());
            throw new BusinessException("businessOrderId已存在");
        }
        /*
        frozenPOHis.setLastModifyTime(new Date());
        int count = frozenPOMapper.updateByPrimaryKeySelective(frozenPOHis);
        logger.info("update frozen  success, params = {}, affact row = {}", frozenPO, count);
        */
        return frozenPOHis;

    }

    /**
     * query frozenPO by frozenId
     */
    private FrozenPO queryByFrozenId(String businessOrderId) {

        FrozenPO frozenPO = frozenPOMapper.selectByFrozenId(businessOrderId);
        logger.info("query by transfer id success,params = {}, result = {}", businessOrderId, frozenPO);
        return frozenPO;
    }

    /**
     * 冻结、解冻流水进冻结表
     *
     */
    public void update(FrozenPO frozenPO) throws Exception {
        logger.info("修改冻结单状态， params = {}", frozenPO);

        //状态已冻结
        frozenPO.setFrozenStatus(2);
        frozenPO.setLastModifyTime(new Date());
        int result = frozenPOMapper.updateByPrimaryKeySelective(frozenPO);

        if(result <= 0){
            logger.error("修改冻结单状态失败");
            throw new Exception("修改冻结单状态失败");
        }
        logger.info("update frozen into db success, params = {}, affact row = {}", frozenPO, result);
    }


    /**
     * transfer acmFrozenBalance to frozenPO
     */
    private FrozenPO acmFrozenBalance2FrozenPO(AcmFrozenBalance acmFrozenBalance) throws Exception {
        FrozenPO frozenPO = new FrozenPO();
        frozenPO.setOrderId(acmFrozenBalance.getOrderId());
        frozenPO.setBusinessOrderId(acmFrozenBalance.getBusinessOrderId());
        frozenPO.setComUserId(acmFrozenBalance.getComUserId());
        // 根据 comUserId 从ACM获取 accountId
        String accountId = acmService.getAccountIdByComId(acmFrozenBalance.getComUserId());
        frozenPO.setAccountId(Long.valueOf(accountId));

        // 暂时写死 100，现金
        frozenPO.setAccountType((short) AccountType.U100.getId());
        frozenPO.setFrozenId(acmFrozenBalance.getBusinessOrderId());
        // 收款类型：1：冻结，2：解冻
        if(Integer.valueOf(1).equals(acmFrozenBalance.getType())){
            frozenPO.setFrozenType(1);
            frozenPO.setAmount(acmFrozenBalance.getFrozenAmount());
            frozenPO.setOrderAmount(acmFrozenBalance.getFrozenAmount());
            //状态未冻结
            frozenPO.setFrozenStatus(1);
        } else if(Integer.valueOf(2).equals(acmFrozenBalance.getType())){
            frozenPO.setFrozenType(2);
            frozenPO.setFrozenStatus(3);//3 未解冻  4 已解冻
            frozenPO.setAmount(acmFrozenBalance.getDefrozenAmount());
            frozenPO.setOrderAmount(acmFrozenBalance.getDefrozenAmount());
            frozenPO.setOrginFrozenId(acmFrozenBalance.getFrozenId());
        } else {
            logger.error("非法的操作类型，只允许冻结、解冻, type = {}", acmFrozenBalance.getType());
            throw new Exception("非法的操作类型，只允许冻结、解冻");
        }
        Date date = new Date();
        frozenPO.setFrozenTime(date);
        frozenPO.setLastModifyTime(date);
        frozenPO.setType(acmFrozenBalance.getType());
        return frozenPO;
    }


    /**
     * 查询冻结、解冻流水
     */
    public List<FrozenPO> query(FlowRequestVO flowRequestVO) throws Exception {
        logger.info("查询冻结、解冻流水， params = {}", flowRequestVO);
        List<FrozenPO> result = frozenPOMapper.queryFrozens(flowRequestVO);
        // 没有的时候返回值是空数组，不是null
        if (result == null  || result.size() <= 0) {
            return Lists.newArrayList();
        }
        List<String> payCoreIds = Lists.transform(result, input -> input.getBusinessOrderId());
        Map<String, Object> voMap = acmService.queryFrozenDetail(payCoreIds);
        for (FrozenPO frozenPO : result) {
            String key = frozenPO.getBusinessOrderId();
            if (voMap.containsKey(key)) {
                AcmAccountPayDetail detail = (AcmAccountPayDetail) voMap.get(key);
                if (detail.getAccountType() == AccountType.U102.getId()) {
                    frozenPO.setBalanceAmount(detail.getAfterAmount());
                    frozenPO.setFrozenAmount(detail.getAfterFrozenAmount());
                }
            }
        }
        logger.info("query frozens from db success, params = {}, result = {}", flowRequestVO, result);
        return result;
    }

    /**
     * 查询冻结、解冻流水条数
     */
    public int queryCount(FlowRequestVO flowRequestVO) throws Exception {
        logger.info("查询冻结、解冻流水条数， params = {}", flowRequestVO);
        int count = frozenPOMapper.queryFrozensCount(flowRequestVO);
        logger.info("query frozens count from db success, params = {}, result = {}", flowRequestVO, count);
        return count;
    }


}
