package com.example.demo.mssqluser.service;

import com.example.demo.mssqluser.sqlconnector.DataAccessConnector;
import com.example.demo.mssqluser.model.SQLCredentialsAndRequest;
import com.example.demo.mssqluser.model.SQLCredentialsWithColumnRequest;
import com.example.demo.mssqluser.model.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class SQLService implements CreateService {

    @Autowired
    private DataAccessConnector dataAccessConnector;

    public String createSQLUser(UserCredential userCredential) throws Exception {
        if(dataAccessConnector.createUser(userCredential))
            return userCredential.getUserName();
        throw new Exception();
    }

    public String assignSQLUserToDatabase(SQLCredentialsAndRequest sqlCredentialsAndRequest) throws SQLException, ClassNotFoundException {
        dataAccessConnector.createDatabaseUser(sqlCredentialsAndRequest.getUserCredential(), sqlCredentialsAndRequest.getSqlRequest());
        return sqlCredentialsAndRequest.getSqlRequest().getName();
    }

    public String grantTableAccessToSQLUser(SQLCredentialsAndRequest sqlCredentialsAndRequest) throws SQLException, ClassNotFoundException {
        dataAccessConnector.grantWholeTableAccess(sqlCredentialsAndRequest.getUserCredential(), sqlCredentialsAndRequest.getSqlRequest());
        return sqlCredentialsAndRequest.getSqlRequest().getName();
    }

    public String grantColumnAccessToSQLUser(SQLCredentialsWithColumnRequest sqlCredentialsWithColumnRequest) throws SQLException, ClassNotFoundException {
        dataAccessConnector.grantColumnAccess(sqlCredentialsWithColumnRequest.getUserCredential(), sqlCredentialsWithColumnRequest.getSqlTableRequest(), sqlCredentialsWithColumnRequest.getSqlColumnRequest());
        return sqlCredentialsWithColumnRequest.getSqlColumnRequest().getName();
    }
}
