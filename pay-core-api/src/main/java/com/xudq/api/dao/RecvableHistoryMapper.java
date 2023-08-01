package com.xudq.api.dao;

import com.xudq.api.po.RecvableHistory;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
public interface RecvableHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecvableHistory record);

    int insertSelective(RecvableHistory record);

    RecvableHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecvableHistory record);

    int updateByPrimaryKeyWithBLOBs(RecvableHistory record);

    int updateByPrimaryKey(RecvableHistory record);

    int batchInsert(@Param("list") List<RecvableHistory> list);
}