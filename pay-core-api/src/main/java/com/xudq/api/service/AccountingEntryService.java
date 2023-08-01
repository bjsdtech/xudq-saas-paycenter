package com.xudq.api.service;

import com.xudq.api.dao.AccountingEntryMapper;
import com.xudq.api.po.AccountingEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 交易配置service
 * @date
 */
@Service
public class AccountingEntryService {
    @Autowired
    private AccountingEntryMapper accountingEntryMapper;

    private static final Logger logger = LoggerFactory.getLogger(AccountingEntryService.class);

    /**
     * 查询交易配置
     * */
    public AccountingEntry queryAccountingEntry(AccountingEntry accountingEntry){
        logger.info("查询交易配置， params ： {}", accountingEntry);
        List<AccountingEntry> accountingEntries = accountingEntryMapper.selectByParam(accountingEntry, 0, 1);
        if(accountingEntries.size() <= 0){
            return null;
        }
        return accountingEntries.get(0);
    }

}
