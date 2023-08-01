package com.xudq.api.mybatis.typeHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 这是一个简化版，以后实现一个无继承版本
 * @date
 */
public abstract class EnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {


    protected abstract Object getEnumValue(E parameter);

    protected abstract E getEnumFromValue(Object value);


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, getEnumValue(parameter));
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        Object i = rs.getObject(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return getEnumFromValue(i);

        }
    }

    public E getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        Object i = rs.getObject(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return getEnumFromValue(i);

        }
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        Object i = cs.getObject(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return getEnumFromValue(i);
        }
    }
}
