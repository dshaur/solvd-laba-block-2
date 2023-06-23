package com.solvd.block2.mybatis;

import com.solvd.block2.sql.models.TransactionType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionTypeHandler extends BaseTypeHandler<TransactionType> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, TransactionType parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getTypeId());
    }

    @Override
    public TransactionType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int typeId = rs.getInt(columnName);
        String typeName = rs.getString("type_name");
        return new TransactionType(typeId, typeName);
    }

    @Override
    public TransactionType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int typeId = rs.getInt(columnIndex);
        String typeName = rs.getString("type_name");
        return new TransactionType(typeId, typeName);
    }

    @Override
    public TransactionType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int typeId = cs.getInt(columnIndex);
        String typeName = cs.getString("type_name");
        return new TransactionType(typeId, typeName);
    }
}

