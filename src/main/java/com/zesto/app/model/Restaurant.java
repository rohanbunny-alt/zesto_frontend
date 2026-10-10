package com.zesto.app.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String id;
    private String name;
    private String image;
    private List<String> cuisine = new ArrayList<>();
    private double rating;
    private String ratingCount;
    private String deliveryTime;
    private int costForTwo;
    private String location;
    private boolean isPureVeg;
    private String offer;
    private boolean isOpen = true;
    private List<MenuItem> menu = new ArrayList<>();

    public Restaurant() {}

    public Restaurant(String id, String name, String image, List<String> cuisine, double rating, String ratingCount, String deliveryTime, int costForTwo, String location, boolean isPureVeg, String offer, boolean isOpen, List<MenuItem> menu) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.cuisine = cuisine;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.deliveryTime = deliveryTime;
        this.costForTwo = costForTwo;
        this.location = location;
        this.isPureVeg = isPureVeg;
        this.offer = offer;
        this.isOpen = isOpen;
        this.menu = menu;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public List<String> getCuisine() { return cuisine; }
    public void setCuisine(List<String> cuisine) { this.cuisine = cuisine; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String getRatingCount() { return ratingCount; }
    public void setRatingCount(String ratingCount) { this.ratingCount = ratingCount; }

    public String getDeliveryTime() { return deliveryTime; }
    public void setDeliveryTime(String deliveryTime) { this.deliveryTime = deliveryTime; }

    public int getCostForTwo() { return costForTwo; }
    public void setCostForTwo(int costForTwo) { this.costForTwo = costForTwo; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public boolean isPureVeg() { return isPureVeg; }
    public void setPureVeg(boolean pureVeg) { isPureVeg = pureVeg; }

    public String getOffer() { return offer; }
    public void setOffer(String offer) { this.offer = offer; }

    public boolean isOpen() { return isOpen; }
    public void setOpen(boolean open) { isOpen = open; }

    public List<MenuItem> getMenu() { return menu; }
    public void setMenu(List<MenuItem> menu) { this.menu = menu; }
}
