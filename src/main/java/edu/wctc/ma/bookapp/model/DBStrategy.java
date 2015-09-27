package edu.wctc.ma.bookapp.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jlombardo
 */
public interface DBStrategy {

    void closeConnection() throws SQLException;

    int deleteById(String tableName, String primaryKeyFieldName, Object primaryKeyValue) throws SQLException;

    List<Map<String, Object>> findAllRecords(String tableName) throws SQLException;

    void openConnection(String driverClass, String url, String userName, String password) throws Exception;
    
    int updateRecords(String tableName, String whereFieldName, Object whereValue, List columnNames, List columnValues) throws SQLException;
    
    int insertRecords(String tableName, List columNames, List columnValues) throws SQLException;
}
