package com.zesto.app.model;

public class MenuItem {
    private String id;
    private String name;
    private int price;
    private Integer originalPrice;
    private String category;
    private boolean isVeg;
    private boolean isBestseller;
    private double rating;
    private int ratingCount;
    private String description;
    private String image;

    public MenuItem() {}

    public MenuItem(String id, String name, int price, Integer originalPrice, String category, boolean isVeg, boolean isBestseller, double rating, int ratingCount, String description, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.originalPrice = originalPrice;
        this.category = category;
        this.isVeg = isVeg;
        this.isBestseller = isBestseller;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.description = description;
        this.image = image;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public Integer getOriginalPrice() { return originalPrice; }
    public void setOriginalPrice(Integer originalPrice) { this.originalPrice = originalPrice; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public boolean isVeg() { return isVeg; }
    public void setVeg(boolean veg) { isVeg = veg; }

    public boolean isBestseller() { return isBestseller; }
    public void setBestseller(boolean bestseller) { isBestseller = bestseller; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public int getRatingCount() { return ratingCount; }
    public void setRatingCount(int ratingCount) { this.ratingCount = ratingCount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}
