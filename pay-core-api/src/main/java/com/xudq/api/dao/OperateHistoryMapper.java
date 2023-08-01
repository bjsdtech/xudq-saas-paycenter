package com.xudq.api.dao;

import com.xudq.api.po.OperateHistory;
import com.xudq.api.po.OperateHistoryWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
public interface OperateHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OperateHistoryWithBLOBs record);

    int insertSelective(OperateHistoryWithBLOBs record);

    OperateHistoryWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OperateHistoryWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(OperateHistoryWithBLOBs record);

    int updateByPrimaryKey(OperateHistory record);

    List<OperateHistory> selectByParam(@Param("po") OperateHistory operateHistory, @Param("start") Integer start, @Param("end") Integer end);

    int selectCountByParam(@Param("po") OperateHistory operateHistory);

    int batchInsert(@Param("list") List<OperateHistory> list);
}