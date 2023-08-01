package com.xudq.api.dao;

import com.xudq.api.po.AccountingEntry;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */

public interface AccountingEntryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountingEntry record);

    int insertSelective(AccountingEntry record);

    AccountingEntry selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountingEntry record);

    int updateByPrimaryKey(AccountingEntry record);

    List<AccountingEntry> selectByParam(@Param("po") AccountingEntry accountingEntry, @Param("start") Integer start, @Param("end") Integer end);

    int selectCountByParam(AccountingEntry accountingEntry);

    int batchInsert(List<AccountingEntry> list);
}