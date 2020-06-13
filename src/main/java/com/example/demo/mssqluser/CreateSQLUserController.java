package com.example.demo.mssqluser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateSQLUserController {

    @Autowired
    private CreateSQLService createSQLService;

    @PostMapping("/sql-user")
    public String createSQLUser(@RequestBody UserCredential userCredential) throws Exception {
        return createSQLService.createSQLUser(userCredential);
    }
}
