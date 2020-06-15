package com.example.demo.mssqluser.model;

import java.util.Objects;

public class SQLRequest {
    private String access;
    private String name;

    public SQLRequest() {
    }

    public SQLRequest(String access, String name) {
        this.access = access;
        this.name = name;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SQLRequest that = (SQLRequest) o;
        return Objects.equals(access, that.access) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(access, name);
    }
}
