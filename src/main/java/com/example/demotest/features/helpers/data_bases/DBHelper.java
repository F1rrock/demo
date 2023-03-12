package com.example.demotest.features.helpers.data_bases;

import com.example.demotest.core.errors.exceptions.ServerException;
import com.example.demotest.core.mappers.MapperTo;

import java.sql.ResultSet;
import java.util.List;

public interface DBHelper {
   <Type> List<Type> executeSelect(String query, MapperTo<Type, ResultSet> mapper, List<?> attributes) throws ServerException;
   void executeUpdate(String query, List<?> attributes) throws ServerException;
}
