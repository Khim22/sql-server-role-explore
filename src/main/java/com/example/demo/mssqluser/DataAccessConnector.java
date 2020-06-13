package com.example.demo.mssqluser;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.MethodNotAllowedException;

import java.lang.reflect.MalformedParametersException;
import java.sql.*;

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
        // not very safe
        String sqlCreateLogin = String.format("CREATE LOGIN %s WITH PASSWORD = '%s'", userCredential.getUserName(), userCredential.getPassword());
        String sqlCreateUser = String.format("CREATE USER %s FOR LOGIN %s", userCredential.getUserName(),userCredential.getUserName());

        try(Connection conn = DriverManager.getConnection (dataSourceUrl, sqlSaUsername,sqlSaPassword);
            PreparedStatement ps = conn.prepareStatement(sqlCreateLogin)){
            ps.execute();

            PreparedStatement ps2 = conn.prepareStatement(sqlCreateUser);
            ps2.execute();

            return true;
        }
    }



}
