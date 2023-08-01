package com.xudq.api.mybatis.typeHandler;

import com.xudq.api.enums.AccountStatus;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
public class AccountStatusTypeHandler extends EnumTypeHandler<AccountStatus> {

    @Override
    protected Object getEnumValue(AccountStatus parameter) {
        return parameter.getId();
    }

    @Override
    protected AccountStatus getEnumFromValue(Object value) {
        return AccountStatus.getById((Integer)value);
    }
}
