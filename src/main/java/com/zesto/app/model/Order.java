package com.zesto.app.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private String customerName;
    private String customerPhone;
    private String deliveryAddress;
    private String restaurantId;
    private String restaurantName;
    private List<OrderItem> items = new ArrayList<>();
    private int itemTotal;
    private int taxesAndFee;
    private int discount;
    private int grandTotal;
    private String paymentMethod;
    private String paymentStatus;
    private String status;
    private Rider rider;
    private String placedAt;
    private int etaMinutes;

    public static class OrderItem {
        private String id;
        private String name;
        private int price;
        private int quantity;

        public OrderItem() {}
        public OrderItem(String id, String name, int price, int quantity) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public int getPrice() { return price; }
        public void setPrice(int price) { this.price = price; }

        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }

    public Order() {}

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }

    public String getRestaurantId() { return restaurantId; }
    public void setRestaurantId(String restaurantId) { this.restaurantId = restaurantId; }

    public String getRestaurantName() { return restaurantName; }
    public void setRestaurantName(String restaurantName) { this.restaurantName = restaurantName; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    public int getItemTotal() { return itemTotal; }
    public void setItemTotal(int itemTotal) { this.itemTotal = itemTotal; }

    public int getTaxesAndFee() { return taxesAndFee; }
    public void setTaxesAndFee(int taxesAndFee) { this.taxesAndFee = taxesAndFee; }

    public int getDiscount() { return discount; }
    public void setDiscount(int discount) { this.discount = discount; }

    public int getGrandTotal() { return grandTotal; }
    public void setGrandTotal(int grandTotal) { this.grandTotal = grandTotal; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Rider getRider() { return rider; }
    public void setRider(Rider rider) { this.rider = rider; }

    public String getPlacedAt() { return placedAt; }
    public void setPlacedAt(String placedAt) { this.placedAt = placedAt; }

    public int getEtaMinutes() { return etaMinutes; }
    public void setEtaMinutes(int etaMinutes) { this.etaMinutes = etaMinutes; }
}
