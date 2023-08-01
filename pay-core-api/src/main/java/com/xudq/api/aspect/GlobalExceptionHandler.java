package com.xudq.api.aspect;
import com.xudq.api.exceptions.BusinessException;
import com.xudq.base.exception.BaseException;
import com.xudq.base.exception.DBException;
import com.xudq.base.exception.InvalidParaException;
import com.xudq.base.exception.NotFoundException;
import com.xudq.base.exception.PermissionBaseException;
import com.xudq.base.vo.ResultInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public Object handleAllException(Exception ex) {
		logger.error("request exception", ex);
		String msg = ex.getMessage();
		if (StringUtils.isBlank(msg)) {
			msg = "后台服务异常";
		}
		return new ResultInfoVO(-10, msg);
	}

	@ExceptionHandler(BaseException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public Object handleBaseException(BaseException ex) {
		logger.error("BaseException exception", ex);
		String msg = ex.getDescription() + ex.getMessage();
		if (StringUtils.isBlank(msg)) {
			msg = "后台服务异常";
		}else{
			msg = "后台服务异常 " + msg;
		}
		return new ResultInfoVO(-11, msg);
	}

	@ExceptionHandler(InvalidParaException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public Object handleInvalidParaException(InvalidParaException ex) {
		logger.error("InvalidParaException exception", ex);
		String msg = ex.getMessage();
		if (StringUtils.isBlank(msg)) {
			msg = "参数处理异常";
		}else{
			msg = "参数处理异常 " + msg;
		}
		return new ResultInfoVO(-12, msg);
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	public Object handleNotFoundException(NotFoundException ex) {
		logger.error("NotFoundException exception", ex);
		String msg = ex.getMessage();
		if (StringUtils.isBlank(msg)) {
			msg = "找不到需要处理的数据";
		}else{
			msg = "找不到需要处理的数据 " + msg;
		}
		return new ResultInfoVO(-13, msg);
	}

	@ExceptionHandler(DBException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public Object handleDBException(DBException ex) {
		logger.error("DBException exception", ex);
		String msg = ex.getMessage();
		if (StringUtils.isBlank(msg)) {
			msg = "DB操作失败";
		}else{
			msg = "DB操作失败 " + msg;
		}
		return new ResultInfoVO(-14, msg);
	}

	@ExceptionHandler(PermissionBaseException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public Object handlePermissionBaseException(PermissionBaseException ex) {
		logger.error("PermissionBaseException exception", ex);
		String msg = ex.getDescription() + ex.getMessage();
		if (StringUtils.isBlank(msg)) {
			msg = "当前用户权限不足";
		}else{
			msg = "当前用户权限不足 " + msg;
		}
		return new ResultInfoVO(-15, msg);
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public Object handleBusinessException(BusinessException ex) {
		logger.error("BusinessException exception", ex);
		String msg = ex.getMsg();
		if (StringUtils.isBlank(msg)) {
			msg = "业务错误";
		}else{
			msg = "业务错误 " + msg;
		}
		return new ResultInfoVO(-15, msg);
	}

}
