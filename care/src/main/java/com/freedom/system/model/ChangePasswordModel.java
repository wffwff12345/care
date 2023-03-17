package com.freedom.system.model;

import jakarta.validation.constraints.NotEmpty;

public class ChangePasswordModel {

    private String username;
    @NotEmpty
    private String oldPassword;
    @NotEmpty
    private String newPassword;

    // region getter and setter

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }


    // endregion
}
