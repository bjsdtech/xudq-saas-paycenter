package com.xudq.api.mybatis.typeHandler;

import com.xudq.api.enums.UserType;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
public class UserTypeTypeHandler extends EnumTypeHandler<UserType> {

    @Override
    protected Object getEnumValue(UserType parameter) {
        return parameter.getId();
    }

    @Override
    protected UserType getEnumFromValue(Object value) {
        return UserType.getById((Integer)value);
    }
}
