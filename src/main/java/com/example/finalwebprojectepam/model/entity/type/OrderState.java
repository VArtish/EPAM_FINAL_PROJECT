package com.example.finalwebprojectepam.model.entity.type;

public enum OrderState {
    EXPECT(1),
    APPROVED(2),
    DONE(3);

    private long orderStateId;

    OrderState(long orderStateId) {
        this.orderStateId = orderStateId;
    }

    public long getOrderStateId() {
        return orderStateId;
    }
}
