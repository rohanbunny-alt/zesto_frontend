package com.zesto.app.model;

public class Rider {
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
