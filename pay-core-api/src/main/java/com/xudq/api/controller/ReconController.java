package com.xudq.api.controller;

import com.xudq.api.service.ReconService;
import com.xudq.api.utils.ValidationUtil;
import com.xudq.api.vo.BaseResponse;
import com.xudq.api.vo.ReconRequestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 对账调用，修改流水的状态并发起记账
 * @date
 */

@RestController
@RequestMapping("/recon")
public class ReconController {

    protected static final Logger logger = LoggerFactory.getLogger(ReconController.class);

    @Autowired
    private ReconService reconService;

    /**
     * 对账成功
     * @param reconRequestVO
     * @return
     */
    @RequestMapping(value = "/success", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> reconSuccess(@Valid @RequestBody ReconRequestVO reconRequestVO, BindingResult bindingResult) {
        logger.info("对账成功流水修改支付单状态开始，参数: {}", reconRequestVO);
        if (bindingResult.hasErrors()) {
            String errMsg = ValidationUtil.getSingleErrorMsgFrom(bindingResult);
            return BaseResponse.buildParamFail("对账成功流水修改支付单状态失败，参数错误: " + errMsg);
        }
        try {
            //TODO do sth
            int result = reconService.reconSuccess(reconRequestVO);
            logger.info("对账成功流水修改支付单状态成功: {}", "");
            return BaseResponse.buildSucc("对账成功流水修改支付单状态成功", "" + "");
        } catch (Exception e) {
            logger.error("对账成功流水修改支付单状态失败: ", e);
            return BaseResponse.buildSysFail("对账成功流水修改支付单状态失败，" + e.getMessage());
        }
    }



    /**
     * 对账补单
     * @param reconRequestVO
     * @return
     */
    @RequestMapping(value = "/repairOrder", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> repairOrder(@Valid @RequestBody ReconRequestVO reconRequestVO, BindingResult bindingResult) {
        logger.info("对账补单开始，参数: {}", reconRequestVO);
        if (bindingResult.hasErrors()) {
            String errMsg = ValidationUtil.getSingleErrorMsgFrom(bindingResult);
            return BaseResponse.buildParamFail("对账补单失败，参数错误: " + errMsg);
        }
        try {
            //TODO do sth
            int result = reconService.repairOrder(reconRequestVO);
            logger.info("对账补单成功: {}", "");
            return BaseResponse.buildSucc("对账补单成功", "" + "");
        } catch (Exception e) {
            logger.error("对账补单失败: ", e);
            return BaseResponse.buildSysFail("对账补单失败，" + e.getMessage());
        }
    }

}
