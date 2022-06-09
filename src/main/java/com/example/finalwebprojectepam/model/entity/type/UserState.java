package com.example.finalwebprojectepam.model.entity.type;

public enum UserState {
    ENABLED(1),
    BANNED(2),
    DISABLED(3);

    private long stateId;

    UserState(long stateId) {
        this.stateId = stateId;
    }

    public long getStateId() {
        return stateId;
    }
}
