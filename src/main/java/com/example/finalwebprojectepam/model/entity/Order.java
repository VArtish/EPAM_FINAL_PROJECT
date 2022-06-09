package com.example.finalwebprojectepam.model.entity;

import com.example.finalwebprojectepam.model.entity.type.OrderState;

import java.util.Map;
import java.util.Objects;

public class Order extends AbstractEntity {
    private String orderId;
    private Map<Medicine, Integer> medicines;
    private long clientId;
    private OrderState orderState;

    public Order() {

    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setMedicines(Map<Medicine, Integer> medicines) {
        this.medicines = medicines;
    }

    public Map<Medicine, Integer> getMedicines() {
        return medicines;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", medicines=" + medicines +
                ", clientId=" + clientId +
                ", orderState=" + orderState +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getClientId() == order.getClientId() && Objects.equals(getOrderId(), order.getOrderId()) && Objects.equals(getMedicines(), order.getMedicines()) && getOrderState() == order.getOrderState();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getMedicines(), getClientId(), getOrderState());
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
