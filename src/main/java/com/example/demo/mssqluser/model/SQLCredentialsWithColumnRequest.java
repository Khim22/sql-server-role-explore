package com.example.demo.mssqluser.model;

import java.util.Objects;

public class SQLCredentialsWithColumnRequest {

    private UserCredential userCredential;
    private SQLRequest sqlTableRequest;
    private SQLRequest sqlColumnRequest;

    public SQLCredentialsWithColumnRequest() {
    }

    public SQLCredentialsWithColumnRequest(UserCredential userCredential, SQLRequest sqlTableRequest, SQLRequest sqlColumnRequest) {
        this.userCredential = userCredential;
        this.sqlTableRequest = sqlTableRequest;
        this.sqlColumnRequest = sqlColumnRequest;
    }

    public UserCredential getUserCredential() {
        return userCredential;
    }

    public void setUserCredential(UserCredential userCredential) {
        this.userCredential = userCredential;
    }

    public SQLRequest getSqlTableRequest() {
        return sqlTableRequest;
    }

    public void setSqlTableRequest(SQLRequest sqlTableRequest) {
        this.sqlTableRequest = sqlTableRequest;
    }

    public SQLRequest getSqlColumnRequest() {
        return sqlColumnRequest;
    }

    public void setSqlColumnRequest(SQLRequest sqlColumnRequest) {
        this.sqlColumnRequest = sqlColumnRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SQLCredentialsWithColumnRequest that = (SQLCredentialsWithColumnRequest) o;
        return Objects.equals(userCredential, that.userCredential) &&
                Objects.equals(sqlTableRequest, that.sqlTableRequest) &&
                Objects.equals(sqlColumnRequest, that.sqlColumnRequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userCredential, sqlTableRequest, sqlColumnRequest);
    }
}
