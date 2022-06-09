package com.example.finalwebprojectepam.model.entity;

import java.util.Objects;

public class Medicine extends AbstractEntity {
    private long medicineId;
    private String name;
    private int amountInPackage;
    private int amountInPlate;
    private String ingredients;
    private String producingCountry;
    private boolean needPrescription;
    private double price;
    private int quantity;
    private Pharmacy pharmacy;
    private String imageLink;

    public Medicine() {
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public void setMedicineId(long medicineId) {
        this.medicineId = medicineId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmountInPackage(int amountInPackage) {
        this.amountInPackage = amountInPackage;
    }

    public void setAmountInPlate(int amountInPlate) {
        this.amountInPlate = amountInPlate;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setProducingCountry(String producingCountry) {
        this.producingCountry = producingCountry;
    }

    public void setNeedPrescription(boolean needPrescription) {
        this.needPrescription = needPrescription;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getMedicineId() {
        return medicineId;
    }

    public String getName() {
        return name;
    }


    public int getAmountInPackage() {
        return amountInPackage;
    }

    public int getAmountInPlate() {
        return amountInPlate;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getProducingCountry() {
        return producingCountry;
    }

    public boolean isNeedPrescription() {
        return needPrescription;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getImageLink() {
        return imageLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medicine)) return false;

        Medicine medicine = (Medicine) o;
        return getMedicineId() == medicine.getMedicineId() && getAmountInPackage() == medicine.getAmountInPackage() && getAmountInPlate() == medicine.getAmountInPlate() && isNeedPrescription() == medicine.isNeedPrescription() && Double.compare(medicine.getPrice(), getPrice()) == 0 && getQuantity() == medicine.getQuantity() && Objects.equals(getName(), medicine.getName()) && Objects.equals(getIngredients(), medicine.getIngredients()) && Objects.equals(getProducingCountry(), medicine.getProducingCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMedicineId(), getName(), getAmountInPackage(), getAmountInPlate(), getIngredients(), getProducingCountry(), isNeedPrescription(), getPrice(), getQuantity());
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "medicineId=" + medicineId +
                ", name='" + name + '\'' +
                ", amountInPackage=" + amountInPackage +
                ", amountInPlate=" + amountInPlate +
                ", ingredients='" + ingredients + '\'' +
                ", producingCountry='" + producingCountry + '\'' +
                ", needPrescription=" + needPrescription +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    public static class MedicineBuilder {
        private final Medicine medicine;

        public MedicineBuilder() {
            medicine = new Medicine();
        }

        public MedicineBuilder withMedicineId(long medicineId) {
            medicine.medicineId = medicineId;
            return this;
        }

        public MedicineBuilder withImage(String imageLink) {
            medicine.imageLink = imageLink;
            return this;
        }

        public MedicineBuilder withPharmacy(Pharmacy pharmacy) {
            medicine.pharmacy = pharmacy;
            return this;
        }

        public MedicineBuilder withName(String name) {
            medicine.name = name;
            return this;
        }

        public MedicineBuilder withAmountInPackage(int amountInPackage) {
            medicine.amountInPackage = amountInPackage;
            return this;
        }

        public MedicineBuilder withAmountInPlate(int amountInPlate) {
            medicine.amountInPlate = amountInPlate;
            return this;
        }

        public MedicineBuilder withIngredients(String ingredients) {
            medicine.ingredients = ingredients;
            return this;
        }

        public MedicineBuilder withProducingCountry(String producingCountry) {
            medicine.producingCountry = producingCountry;
            return this;
        }

        public MedicineBuilder withNeedPrescription(boolean needPrescription) {
            medicine.needPrescription = needPrescription;
            return this;
        }

        public MedicineBuilder withPrice(double price) {
            medicine.price = price;
            return this;
        }

        public MedicineBuilder withQuantity(int quantity) {
            medicine.quantity = quantity;
            return this;
        }

        public Medicine build() {
            return medicine;
        }
    }
}