package com.DPC.spring.entities;

public class ResetPassword {
    private long id;
    private String passwordA;
    private String passwordN;

    public String getPasswordA() {
        return passwordA;
    }

    public void setPasswordA(String passwordA) {
        this.passwordA = passwordA;
    }

    public String getPasswordN() {
        return passwordN;
    }

    public void setPasswordN(String passwordN) {
        this.passwordN = passwordN;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
