package com.example.demo.mssqluser.model;

import java.util.Objects;

public class SQLCredentialsAndRequest {

    private UserCredential userCredential;
    private SQLRequest sqlRequest;

    public SQLCredentialsAndRequest() {
    }

    public SQLCredentialsAndRequest(UserCredential userCredential, SQLRequest sqlRequest) {
        this.userCredential = userCredential;
        this.sqlRequest = sqlRequest;
    }

    public UserCredential getUserCredential() {
        return userCredential;
    }

    public void setUserCredential(UserCredential userCredential) {
        this.userCredential = userCredential;
    }

    public SQLRequest getSqlRequest() {
        return sqlRequest;
    }

    public void setSqlRequest(SQLRequest sqlRequest) {
        this.sqlRequest = sqlRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SQLCredentialsAndRequest that = (SQLCredentialsAndRequest) o;
        return Objects.equals(userCredential, that.userCredential) &&
                Objects.equals(sqlRequest, that.sqlRequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userCredential, sqlRequest);
    }
}
