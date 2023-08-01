package com.xudq.api.service;

import com.xudq.api.dao.RecvableMapper;
import com.xudq.api.vo.ReconRequestVO;
import com.xudq.api.vo.ReconVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 对账调用，修改流水的状态并发起记账
 * @date
 */
@Service
public class ReconService {

    private static final Logger logger = LoggerFactory.getLogger(ReconService.class);
    @Autowired
    private RecvableMapper recvableMapper;

    /**
     * 对账成功
     */
    public int reconSuccess(ReconRequestVO reconRequestVO){
        List<ReconVO> list = reconRequestVO.getList();
        logger.info("recon success, params = {}", reconRequestVO);
        return recvableMapper.reconSuccess(list);
    }

    /**
     * 补单
     */
    public int repairOrder(ReconRequestVO reconRequestVO){
        List<ReconVO> list = reconRequestVO.getList();
        logger.info("repair order, params = {}", reconRequestVO);
        return recvableMapper.repairOrder(list);
    }

}
