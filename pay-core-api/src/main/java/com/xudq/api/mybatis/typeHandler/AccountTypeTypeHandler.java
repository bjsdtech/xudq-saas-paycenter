package com.xudq.api.mybatis.typeHandler;

import com.xudq.api.enums.AccountType;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
public class AccountTypeTypeHandler extends EnumTypeHandler<AccountType> {

    @Override
    protected Object getEnumValue(AccountType parameter) {
        return parameter.getId();
    }

    @Override
    protected AccountType getEnumFromValue(Object value) {
        return AccountType.getById((Integer)value);
    }
}
