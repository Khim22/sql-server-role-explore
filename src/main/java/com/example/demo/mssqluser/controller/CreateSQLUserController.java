package com.example.demo.mssqluser.controller;

import com.example.demo.mssqluser.service.SQLService;
import com.example.demo.mssqluser.model.SQLCredentialsAndRequest;
import com.example.demo.mssqluser.model.SQLCredentialsWithColumnRequest;
import com.example.demo.mssqluser.model.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateSQLUserController {

    @Autowired
    private SQLService createSQLService;

    @PostMapping("/sql-user")
    public String createSQLUser(@RequestBody UserCredential userCredential) throws Exception {
        return createSQLService.createSQLUser(userCredential);
    }

    @PostMapping("/database-user")
    public String assignSQLUserToDatabase(@RequestBody SQLCredentialsAndRequest sqlCredentialsAndRequest) throws Exception {
        return createSQLService.assignSQLUserToDatabase(sqlCredentialsAndRequest);
    }

    @PostMapping("/table-user")
    public String grantSQLUserToTable(@RequestBody SQLCredentialsAndRequest sqlCredentialsAndRequest) throws Exception {
        return createSQLService.grantTableAccessToSQLUser(sqlCredentialsAndRequest);
    }

    @PostMapping("/column-user")
    public String grantSQLUserToColumn(@RequestBody SQLCredentialsWithColumnRequest sqlCredentialsWithColumnRequest) throws Exception {
        return createSQLService.grantColumnAccessToSQLUser(sqlCredentialsWithColumnRequest);
    }
}
