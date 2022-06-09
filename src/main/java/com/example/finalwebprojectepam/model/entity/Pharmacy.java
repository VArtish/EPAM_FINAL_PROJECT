package com.example.finalwebprojectepam.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Pharmacy extends AbstractEntity {
    private long pharmacyId;
    private String name;
    private String address;
    private List<Medicine> medicineList;

    public Pharmacy(String name, String address, long pharmacyId) {
        this.name = name;
        this.pharmacyId = pharmacyId;
        this.address = address;
        medicineList = new ArrayList<Medicine>();
    }

    public Pharmacy(String name, String address) {
        this.name = name;
        this.address = address;
        medicineList = new ArrayList<Medicine>();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public long getPharmacyId() {
        return pharmacyId;
    }

    public boolean addToMedicineList(Medicine medicine) {
        return medicineList.add(medicine);
    }

    public boolean removeFromMedicineList(Medicine medicine) {
        return medicineList.remove(medicine);
    }

    public Optional<Medicine> getMedicineAtIndex(int index) {
        if(index < medicineList.size()) {
            return Optional.of(medicineList.get(index));
        }

        return Optional.empty();
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "pharmacyId=" + pharmacyId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", medicineList=" + medicineList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pharmacy)) return false;
        Pharmacy pharmacy = (Pharmacy) o;
        return pharmacyId == pharmacy.pharmacyId && Objects.equals(getName(), pharmacy.getName()) && Objects.equals(getAddress(), pharmacy.getAddress()) && Objects.equals(medicineList, pharmacy.medicineList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pharmacyId, getName(), getAddress(), medicineList);
    }
}