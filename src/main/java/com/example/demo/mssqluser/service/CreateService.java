package com.example.demo.mssqluser.service;

import com.example.demo.mssqluser.model.SQLCredentialsAndRequest;
import com.example.demo.mssqluser.model.SQLCredentialsWithColumnRequest;
import com.example.demo.mssqluser.model.UserCredential;

import java.sql.SQLException;

public interface CreateService {
    String createSQLUser(UserCredential userCredential) throws Exception;
    String assignSQLUserToDatabase(SQLCredentialsAndRequest sqlCredentialsAndRequest) throws SQLException, ClassNotFoundException;
    String grantTableAccessToSQLUser(SQLCredentialsAndRequest sqlCredentialsAndRequest) throws SQLException, ClassNotFoundException;
    String grantColumnAccessToSQLUser(SQLCredentialsWithColumnRequest sqlCredentialsWithColumnRequest) throws SQLException, ClassNotFoundException;
}
