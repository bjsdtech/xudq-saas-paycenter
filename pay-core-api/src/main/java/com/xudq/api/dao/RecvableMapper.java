package com.xudq.api.dao;

import com.xudq.api.po.Recvable;

import java.util.List;

import com.xudq.api.vo.FlowRequestVO;
import com.xudq.api.vo.ReconVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
@Repository
public interface RecvableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Recvable record);

    int insertSelective(Recvable record);

    Recvable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Recvable record);

    int updateByPrimaryKey(Recvable record);

    List<Recvable> selectByParam(@Param("po") Recvable recvable, @Param("start") Integer start, @Param("end") Integer end);

    int selectCountByParam(@Param("po") Recvable recvable);

    int batchInsert(List<Recvable> list);

    int reconSuccess(List<ReconVO> list);

    int repairOrder(List<ReconVO> list);

    int batchUpdateByRecvableId(List<Recvable> recvables);

    List<Recvable> queryRecvables(FlowRequestVO flowRequestVO);

    int queryRecvablesCount(FlowRequestVO flowRequestVO);

    int queryCountByBusinessOrderId(String businessOrderId);

    Recvable queryBySendChannelTransId(@Param("sendChannelTransId") String sendChannelTransId, @Param("accountType") int accountType);


}