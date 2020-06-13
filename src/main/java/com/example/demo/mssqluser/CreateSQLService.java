package com.example.demo.mssqluser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateSQLService {

    @Autowired
    private DataAccessConnector dataAccessConnector;

    public String createSQLUser(UserCredential userCredential) throws Exception {
        if(dataAccessConnector.createUser(userCredential))
            return userCredential.getUserName();
        throw new Exception();
    }
}
