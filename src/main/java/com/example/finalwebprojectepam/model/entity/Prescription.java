package com.example.finalwebprojectepam.model.entity;

import com.example.finalwebprojectepam.model.entity.type.PrescriptionState;

import java.util.List;
import java.util.Objects;

public class Prescription extends AbstractEntity {
    private long prescriptionId;
    private User doctor;
    private User client;
    private List<Medicine> medicines;
    private PrescriptionState prescriptionState;

    public Prescription(User doctor, User client, List<Medicine> medicines, long prescriptionId) {
        this.doctor = doctor;
        this.client = client;
        this.prescriptionId = prescriptionId;
        this.medicines = medicines;
    }

    public long getPrescriptionId() {
        return prescriptionId;
    }

    public User getDoctor() {
        return doctor;
    }

    public User getClient() {
        return client;
    }

    public List<Medicine> getMedicine() {
        return medicines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prescription)) return false;
        Prescription that = (Prescription) o;
        return getPrescriptionId() == that.getPrescriptionId() && Objects.equals(getDoctor(), that.getDoctor()) && Objects.equals(getClient(), that.getClient()) && Objects.equals(getMedicine(), that.getMedicine()) && prescriptionState == that.prescriptionState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrescriptionId(), getDoctor(), getClient(), getMedicine(), prescriptionState);
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionId=" + prescriptionId +
                ", doctor=" + doctor +
                ", client=" + client +
                ", medicines=" + medicines +
                ", prescriptionState=" + prescriptionState +
                '}';
    }
}
