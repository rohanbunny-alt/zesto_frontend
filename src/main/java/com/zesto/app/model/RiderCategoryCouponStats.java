package com.zesto.app.model;

import java.util.Map;

public class RiderCategoryCouponStats {

    public static class Rider {
        private String id;
        private String name;
        private String phone;
        private double rating;
        private String vehicle;
        private String avatar;

        public Rider() {}
        public Rider(String id, String name, String phone, double rating, String vehicle, String avatar) {
            this.id = id;
            this.name = name;
            this.phone = phone;
            this.rating = rating;
            this.vehicle = vehicle;
            this.avatar = avatar;
        }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }

        public double getRating() { return rating; }
        public void setRating(double rating) { this.rating = rating; }

        public String getVehicle() { return vehicle; }
        public void setVehicle(String vehicle) { this.vehicle = vehicle; }

        public String getAvatar() { return avatar; }
        public void setAvatar(String avatar) { this.avatar = avatar; }
    }

    public static class FoodCategory {
        private String id;
        private String name;
        private String image;

        public FoodCategory() {}
        public FoodCategory(String id, String name, String image) {
            this.id = id;
            this.name = name;
            this.image = image;
        }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getImage() { return image; }
        public void setImage(String image) { this.image = image; }
    }

    public static class Coupon {
        private String code;
        private int discountPercentage;
        private int maxDiscount;
        private int minOrder;
        private boolean isFreeDelivery;
        private String description;

        public Coupon() {}
        public Coupon(String code, int discountPercentage, int maxDiscount, int minOrder, boolean isFreeDelivery, String description) {
            this.code = code;
            this.discountPercentage = discountPercentage;
            this.maxDiscount = maxDiscount;
            this.minOrder = minOrder;
            this.isFreeDelivery = isFreeDelivery;
            this.description = description;
        }

        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }

        public int getDiscountPercentage() { return discountPercentage; }
        public void setDiscountPercentage(int discountPercentage) { this.discountPercentage = discountPercentage; }

        public int getMaxDiscount() { return maxDiscount; }
        public void setMaxDiscount(int maxDiscount) { this.maxDiscount = maxDiscount; }

        public int getMinOrder() { return minOrder; }
        public void setMinOrder(int minOrder) { this.minOrder = minOrder; }

        public boolean isFreeDelivery() { return isFreeDelivery; }
        public void setFreeDelivery(boolean freeDelivery) { isFreeDelivery = freeDelivery; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    public static class AdminStats {
        private int totalRevenue;
        private int totalOrders;
        private int activeOrdersCount;
        private int activeRestaurants;
        private int activeRiders;
        private Map<String, Integer> ordersByStatus;

        public AdminStats() {}

        public int getTotalRevenue() { return totalRevenue; }
        public void setTotalRevenue(int totalRevenue) { this.totalRevenue = totalRevenue; }

        public int getTotalOrders() { return totalOrders; }
        public void setTotalOrders(int totalOrders) { this.totalOrders = totalOrders; }

        public int getActiveOrdersCount() { return activeOrdersCount; }
        public void setActiveOrdersCount(int activeOrdersCount) { this.activeOrdersCount = activeOrdersCount; }

        public int getActiveRestaurants() { return activeRestaurants; }
        public void setActiveRestaurants(int activeRestaurants) { this.activeRestaurants = activeRestaurants; }

        public int getActiveRiders() { return activeRiders; }
        public void setActiveRiders(int activeRiders) { this.activeRiders = activeRiders; }

        public Map<String, Integer> getOrdersByStatus() { return ordersByStatus; }
        public void setOrdersByStatus(Map<String, Integer> ordersByStatus) { this.ordersByStatus = ordersByStatus; }
    }
}
