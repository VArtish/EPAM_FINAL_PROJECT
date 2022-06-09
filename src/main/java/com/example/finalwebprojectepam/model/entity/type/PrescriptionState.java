package com.example.finalwebprojectepam.model.entity.type;

public enum PrescriptionState {
    ACTUALLY(1),
    INVALID(2);

    private long prescriptionStateId;

    PrescriptionState(long prescriptionStateId) {
        this.prescriptionStateId = prescriptionStateId;
    }

    public long getPrescriptionStateId() {
        return prescriptionStateId;
    }
}
