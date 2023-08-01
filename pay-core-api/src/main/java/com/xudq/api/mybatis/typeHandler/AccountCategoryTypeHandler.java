package com.xudq.api.mybatis.typeHandler;

import com.xudq.api.enums.AccountCategory;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
public class AccountCategoryTypeHandler extends EnumTypeHandler<AccountCategory> {

    @Override
    protected Object getEnumValue(AccountCategory parameter) {
        return parameter.getId();
    }

    @Override
    protected AccountCategory getEnumFromValue(Object value) {
        return AccountCategory.getById((Integer)value);
    }
}
