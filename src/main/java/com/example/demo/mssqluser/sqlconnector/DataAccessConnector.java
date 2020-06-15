package com.example.demo.mssqluser.sqlconnector;

import com.example.demo.mssqluser.model.SQLRequest;
import com.example.demo.mssqluser.model.UserCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.MalformedParametersException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// FIXME: not very safe, should shift to Stored Procedure
@Component
public class DataAccessConnector {

    @Value("${sql.sa.username}")
    private String sqlSaUsername;

    @Value("${sql.sa.password}")
    private String sqlSaPassword;

    @Value("${sql.connection.string}")
    private String dataSourceUrl;

    @Value("${sql.driver.name}")
    private String driverName;


    public boolean createUser(UserCredential userCredential) throws ClassNotFoundException, SQLException {
        Class.forName(driverName);

        if(StringUtils.containsWhitespace(userCredential.getUserName()) || StringUtils.containsWhitespace(userCredential.getPassword()))
            throw new MalformedParametersException("Wrong 'Username' or 'Password' Field");

        String sqlCreateLogin = String.format("CREATE LOGIN %s WITH PASSWORD = '%s'", userCredential.getUserName(), userCredential.getPassword());


        try(Connection conn = DriverManager.getConnection (dataSourceUrl, sqlSaUsername,sqlSaPassword);
            PreparedStatement ps = conn.prepareStatement(sqlCreateLogin)){
            ps.execute();

            return true;
        }
    }

    public boolean createDatabaseUser(UserCredential userCredential, SQLRequest sqlRequest) throws ClassNotFoundException, SQLException {
        Class.forName(driverName);

        if(StringUtils.containsWhitespace(userCredential.getUserName()) || StringUtils.containsWhitespace(userCredential.getPassword()))
            throw new MalformedParametersException("Wrong 'Username' or 'Password' Field");
        String sqlCreateUser = String.format("USE %s; CREATE USER %s FOR LOGIN %s;", sqlRequest.getName(), userCredential.getUserName(),userCredential.getUserName());

        try(Connection conn = DriverManager.getConnection (dataSourceUrl, sqlSaUsername,sqlSaPassword);
            PreparedStatement ps = conn.prepareStatement(sqlCreateUser)){
            ps.execute();

            return true;
        }

    }

    public boolean grantWholeTableAccess(UserCredential userCredential, SQLRequest sqlRequest) throws ClassNotFoundException, SQLException {
        Class.forName(driverName);

        //fixme: no check for table/ column/ database exist

        if(StringUtils.containsWhitespace(userCredential.getUserName()) || StringUtils.containsWhitespace(userCredential.getPassword()))
            throw new MalformedParametersException("Wrong 'Username' or 'Password' Field");
        String grantTable = String.format("GRANT SELECT ON %s TO %s", userCredential.getUserName(), sqlRequest.getName());

        try(Connection conn = DriverManager.getConnection (dataSourceUrl, sqlSaUsername,sqlSaPassword);
            PreparedStatement ps = conn.prepareStatement(grantTable)){
            ps.execute();

            return true;
        }

    }

    public boolean grantColumnAccess(UserCredential userCredential, SQLRequest sqlRequestTable, SQLRequest sqlRequestColumn) throws ClassNotFoundException, SQLException {
        Class.forName(driverName);

        //fixme: no check for table/ column/ database exist

        if(StringUtils.containsWhitespace(userCredential.getUserName()) || StringUtils.containsWhitespace(userCredential.getPassword()))
            throw new MalformedParametersException("Wrong 'Username' or 'Password' Field");
        String grantTable = String.format("GRANT SELECT ON %s (%s) TO %s",  sqlRequestTable.getName(), sqlRequestColumn.getName(), userCredential.getUserName());

        try(Connection conn = DriverManager.getConnection (dataSourceUrl, sqlSaUsername,sqlSaPassword);
            PreparedStatement ps = conn.prepareStatement(grantTable)){
            ps.execute();

            return true;
        }

    }
}
