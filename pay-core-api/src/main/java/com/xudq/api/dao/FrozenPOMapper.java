package com.xudq.api.dao;

import com.xudq.api.po.FrozenPO;
import java.util.List;

import com.xudq.api.vo.FlowRequestVO;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */

public interface FrozenPOMapper {

    int insert(FrozenPO record);

    int insertSelective(FrozenPO record);

    FrozenPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FrozenPO record);

    int updateByPrimaryKey(FrozenPO record);

    int batchInsert(List<FrozenPO> list);

    List<FrozenPO> queryFrozens(FlowRequestVO flowRequestVO);

    int queryFrozensCount(FlowRequestVO flowRequestVO);

    FrozenPO selectByFrozenId(String businessOrderId);
}