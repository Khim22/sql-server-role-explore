package com.example.demo.mssqluser.sqlconnector;

import com.example.demo.mssqluser.model.SQLRequest;
import com.example.demo.mssqluser.model.UserCredential;

import java.sql.SQLException;

public interface SQLConnector {
   boolean createUser(UserCredential userCredential) throws ClassNotFoundException, SQLException ;

   boolean createDatabaseUser(UserCredential userCredential, SQLRequest sqlRequest) throws ClassNotFoundException, SQLException ;

   boolean grantWholeTableAccess(UserCredential userCredential, SQLRequest sqlRequest) throws ClassNotFoundException, SQLException;

   boolean grantColumnAccess(UserCredential userCredential, SQLRequest sqlRequestTable, SQLRequest sqlRequestColumn) throws ClassNotFoundException, SQLException;
}
