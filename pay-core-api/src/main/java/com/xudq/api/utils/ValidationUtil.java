package com.xudq.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidationUtil {

    private final static Logger logger = LoggerFactory.getLogger(ValidationUtil.class);
    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    /**
     * 从BindingResult中获取错误信息
     *
     * @return
     */
    public static Set<String> getErrorMsgFrom(BindingResult result) {
        if (result != null && result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            Set<String> errors = new HashSet<>();
            if (fieldErrors != null) {
                fieldErrors.forEach(fieldError -> {
                    logger.info(fieldError.getField());
                    errors.add(fieldError.getDefaultMessage());
                });
            }

            return errors;
        }

        return null;
    }

    /**
     * 从BindingResult中获取错误信息，只返回第一条
     *
     * @return
     */
    public static String getSingleErrorMsgFrom(BindingResult result) {
        if (result != null && result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();

            if (fieldErrors != null && fieldErrors.size() > 0) {
                FieldError error = fieldErrors.get(0);
                for (int i = 1; i < fieldErrors.size(); i++) {
                    if (fieldErrors.get(i).getDefaultMessage().compareTo(error.getDefaultMessage()) > 0) {
                        error = fieldErrors.get(i);
                    }
                }
                return error.getDefaultMessage();
            }
        }

        return null;
    }

    public static <T> List<String> validate(T t) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        List<String> messageList = new ArrayList<>();
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            messageList.add(constraintViolation.getMessage());
        }
        return messageList;
    }
}
