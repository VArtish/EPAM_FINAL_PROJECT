package com.example.finalwebprojectepam.model.entity.type;

public enum UserRole {
    ADMIN(1),
    PHARMACIST(2),
    DOCTOR(3),
    CUSTOMER(4),
    GUEST(5);

    private long roleId;

    UserRole(long id) {
        this.roleId = id;

    }

    public long getRoleId() {
        return roleId;
    }
}
