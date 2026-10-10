package com.zesto.app.model;

import java.util.Map;

public class AdminStats {
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
