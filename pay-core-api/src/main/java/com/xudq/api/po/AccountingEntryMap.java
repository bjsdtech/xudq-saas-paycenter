package com.xudq.api.po;

import com.google.common.collect.Lists;
import com.xudq.api.dao.AccountingEntryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 交易配置map
 * @date
 */
@Component
public class AccountingEntryMap {

    @Autowired
    private AccountingEntryMapper accountingEntryMapper;

    /*
    public AccountingEntryMap(){
        //TODO init AccountingEntrys,最好使用cache，五分钟失效
        accountingEntryMapper = (AccountingEntryMapper) SpringUtils.getBean("accountingEntryMapper");
        getAccountingEntryMap();
    }
    */

    @PostConstruct
    public void init(){
        //查全量的交易配置
        accountingEntrys = accountingEntryMapper.selectByParam(null, null, null);
    }

    private static List<AccountingEntry> accountingEntrys = Lists.newArrayList();

    /*
    public static List<AccountingEntry> getAccountingEntryMap(){


    }
    */

    /**
     * 根据busiType获取AccountingEntry
     * */
    public static List<AccountingEntry> getAccountingEntry(String busiType){
        if(StringUtils.isEmpty(busiType)){
            return Lists.newArrayList();
        }
        List<AccountingEntry> list = Lists.newArrayList();
        for(AccountingEntry accountingEntry : accountingEntrys){
            if(busiType.equals(accountingEntry.getBusiType())){
                list.add(accountingEntry);
            }
        }
        return list;
    }

}