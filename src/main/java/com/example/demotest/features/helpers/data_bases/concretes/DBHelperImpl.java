package com.example.demotest.features.helpers.data_bases.concretes;

import com.example.demotest.common.constants.DBConstAttributes;
import com.example.demotest.core.errors.exceptions.ServerException;
import com.example.demotest.core.mappers.MapperTo;
import com.example.demotest.features.helpers.data_bases.DBHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class DBHelperImpl implements DBHelper {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DBConstAttributes.URL,
                DBConstAttributes.USER,
                DBConstAttributes.PASS
        );
    }
    @Override
    public <Type> List<Type> executeSelect(String query, MapperTo<Type, ResultSet> mapper, List<?> attributes) throws ServerException {
        final List<Type> list = new ArrayList<>();
        try (
                final var connection = getConnection();
                final var stmt = connection.prepareStatement(query)
        ) {
            if (attributes != null) {
                for (var i = 0; i < attributes.size(); i ++) {
                    stmt.setObject(i + 1, attributes.get(i));
                }
            }
            final var rs = stmt.executeQuery();
            while (rs.next()) {
                final var value = mapper.mapTo(rs);
                list.add(value);
            }
            rs.close();
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
        return list;
    }

    @Override
    public void executeUpdate(String query, List<?> attributes) throws ServerException {
        try (
                final var connection = getConnection();
                final var stmt = connection.prepareStatement(query)
        ) {
            if (attributes != null) {
                for (var i = 0; i < attributes.size(); i ++) {
                    stmt.setObject(i + 1, attributes.get(i));
                }
            }
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }
}
