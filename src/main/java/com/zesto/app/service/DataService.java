package com.zesto.app.service;

import com.zesto.app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class DataService {

    private final PasswordEncoder passwordEncoder;

    // Concurrent thread-safe in-memory collections
    private final List<User> users = new CopyOnWriteArrayList<>();
    private final List<Restaurant> restaurants = new CopyOnWriteArrayList<>();
    private final List<FoodCategory> categories = new CopyOnWriteArrayList<>();
    private final List<Coupon> coupons = new CopyOnWriteArrayList<>();
    private final List<Rider> riders = new CopyOnWriteArrayList<>();
    private final List<Order> orders = new CopyOnWriteArrayList<>();

    // Active WebSocket sessions for live real-time updates
    private final List<WebSocketSession> activeSessions = new CopyOnWriteArrayList<>();

    @Autowired
    public DataService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        initSeedData();
    }

    public void addSession(WebSocketSession session) {
        activeSessions.add(session);
    }

    public void removeSession(WebSocketSession session) {
        activeSessions.remove(session);
    }

    public void broadcastMessage(String jsonPayload) {
        for (WebSocketSession session : activeSessions) {
            if (session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(jsonPayload));
                } catch (IOException e) {
                    // Ignore broken connections
                }
            }
        }
    }

    private void initSeedData() {
        // Initial Users
        users.add(new User("usr-1", "Aarav Mehta", "customer@zesto.com", passwordEncoder.encode("password123"), "+91 98765 43210", "CUSTOMER"));
        users.add(new User("usr-2", "Zesto Super Admin", "admin@zesto.com", passwordEncoder.encode("admin123"), "+91 99999 88888", "ADMIN"));

        // Categories
        categories.add(new FoodCategory("1", "Biryani", "https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?w=300&auto=format&fit=crop&q=80"));
        categories.add(new FoodCategory("2", "Pizzas", "https://images.unsplash.com/photo-1513104890138-7c749659a591?w=300&auto=format&fit=crop&q=80"));
        categories.add(new FoodCategory("3", "Burgers", "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=300&auto=format&fit=crop&q=80"));
        categories.add(new FoodCategory("4", "Chinese", "https://images.unsplash.com/photo-1585032226651-759b368d7246?w=300&auto=format&fit=crop&q=80"));
        categories.add(new FoodCategory("5", "South Indian", "https://images.unsplash.com/photo-1610192244261-3f33de3f55e4?w=300&auto=format&fit=crop&q=80"));
        categories.add(new FoodCategory("6", "Desserts", "https://images.unsplash.com/photo-1551024709-8f23befc6f87?w=300&auto=format&fit=crop&q=80"));

        // Coupons
        coupons.add(new Coupon("ZESTO50", 50, 120, 199, false, "50% OFF up to ₹120 on orders above ₹199"));
        coupons.add(new Coupon("SWIGGYSTYLE", 40, 100, 149, false, "40% OFF up to ₹100"));
        coupons.add(new Coupon("FREEDEL", 100, 40, 99, true, "Free Delivery on all orders"));
        coupons.add(new Coupon("WELCOME200", 60, 200, 399, false, "Flat 60% OFF up to ₹200 for new foodies"));

        // Riders
        riders.add(new Rider("rider-1", "Ramesh Kumar", "+91 98765 43210", 4.9, "KA-01-EQ-4821", "https://images.unsplash.com/photo-1534528741775-53994a69daeb?w=150&auto=format&fit=crop&q=80"));
        riders.add(new Rider("rider-2", "Vikram Singh", "+91 98123 45678", 4.8, "KA-03-HF-9012", "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=150&auto=format&fit=crop&q=80"));
        riders.add(new Rider("rider-3", "Aniket Sharma", "+91 99887 76655", 4.7, "KA-05-AB-1234", "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=150&auto=format&fit=crop&q=80"));

        // Restaurants & Menus
        List<MenuItem> menu1 = Arrays.asList(
            new MenuItem("item-101", "Hyderabadi Dum Chicken Biryani", 289, 350, "Biryani", false, true, 4.7, 320, "Authentic Basmati rice slow cooked with tender chicken pieces, aromatic herbs & spices.", "https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?w=400&auto=format&fit=crop&q=80"),
            new MenuItem("item-102", "Paneer Royal Dum Biryani", 249, 299, "Biryani", true, true, 4.4, 180, "Soft cottage cheese marinated in tikka spices layered with fragrant Basmati rice.", "https://images.unsplash.com/photo-1633945274405-b6c8069047b0?w=400&auto=format&fit=crop&q=80"),
            new MenuItem("item-103", "Chicken Reshmi Kebab (6 Pcs)", 319, null, "Starters", false, false, 4.6, 140, "Melt-in-mouth chicken boneless marinated in cashew paste and cream, charcoal grilled.", "https://images.unsplash.com/photo-1599487488170-d11ec9c172f0?w=400&auto=format&fit=crop&q=80")
        );
        restaurants.add(new Restaurant("rest-1", "Royal Biryani House", "https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?w=600&auto=format&fit=crop&q=80", Arrays.asList("Hyderabadi Biryani", "Mughlai", "Kebab"), 4.5, "5K+", "25-30 min", 400, "Indiranagar, Bangalore", false, "60% OFF up to ₹120", true, new ArrayList<>(menu1)));

        List<MenuItem> menu2 = Arrays.asList(
            new MenuItem("item-201", "Pepperoni & Cheese Loaded Pizza (Medium)", 399, 499, "Pizzas", false, true, 4.8, 540, "Classic mozzarella cheese with spicy pepperoni slices baked on sourdough base.", "https://images.unsplash.com/photo-1628840042765-356cda07504e?w=400&auto=format&fit=crop&q=80"),
            new MenuItem("item-202", "Farmhouse Special Veg Pizza (Medium)", 329, 399, "Pizzas", true, true, 4.5, 420, "Delightful combo of capsicum, sweet corn, crisp capsicum, paneer and black olives.", "https://images.unsplash.com/photo-1574071318508-1cdbab80d002?w=400&auto=format&fit=crop&q=80")
        );
        restaurants.add(new Restaurant("rest-2", "Pizza Oven Supreme", "https://images.unsplash.com/photo-1513104890138-7c749659a591?w=600&auto=format&fit=crop&q=80", Arrays.asList("Italian", "Pizzas", "Pasta"), 4.3, "10K+", "20-25 min", 500, "Koramangala, Bangalore", false, "EVERYTHING @ ₹199", true, new ArrayList<>(menu2)));

        List<MenuItem> menu3 = Arrays.asList(
            new MenuItem("item-301", "Smokey BBQ Chicken Crunch Burger", 219, 279, "Burgers", false, true, 4.6, 230, "Crispy fried chicken patty glazed in house smoked BBQ sauce, sharp cheddar & lettuce.", "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=400&auto=format&fit=crop&q=80"),
            new MenuItem("item-302", "Ultimate Cheese Lava Veg Burger", 189, null, "Burgers", true, true, 4.5, 310, "Molten cheese patty stuffed with jalapeños & veggies topped with spicy mayo.", "https://images.unsplash.com/photo-1550547660-d9450f859349?w=400&auto=format&fit=crop&q=80")
        );
        restaurants.add(new Restaurant("rest-3", "Burger & Craft Grill", "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=600&auto=format&fit=crop&q=80", Arrays.asList("American", "Burgers", "Fries"), 4.4, "2K+", "15-20 min", 350, "HSR Layout, Bangalore", false, "50% OFF up to ₹100", true, new ArrayList<>(menu3)));

        // Initial Orders
        Order ord1 = new Order();
        ord1.setOrderId("ORD-9821");
        ord1.setCustomerName("Aarav Mehta");
        ord1.setCustomerPhone("+91 98765 43210");
        ord1.setDeliveryAddress("Flat 402, Sunshine Apartments, Indiranagar, Bangalore");
        ord1.setRestaurantId("rest-1");
        ord1.setRestaurantName("Royal Biryani House");
        ord1.setItems(Arrays.asList(
            new Order.OrderItem("item-101", "Hyderabadi Dum Chicken Biryani", 289, 2),
            new Order.OrderItem("item-103", "Chicken Reshmi Kebab (6 Pcs)", 319, 1)
        ));
        ord1.setItemTotal(897);
        ord1.setTaxesAndFee(45);
        ord1.setDiscount(100);
        ord1.setGrandTotal(842);
        ord1.setPaymentMethod("UPI (GPay)");
        ord1.setPaymentStatus("Paid");
        ord1.setStatus("On The Way");
        ord1.setRider(riders.get(0));
        ord1.setPlacedAt(Instant.now().minusSeconds(900).toString());
        ord1.setEtaMinutes(12);
        orders.add(ord1);
    }

    public List<User> getUsers() { return users; }

    public Optional<User> findUserByEmail(String email) {
        return users.stream().filter(u -> u.getEmail().equalsIgnoreCase(email)).findFirst();
    }

    public User saveUser(User user) {
        users.add(user);
        return user;
    }

    public List<Restaurant> getRestaurants() { return restaurants; }
    public List<FoodCategory> getCategories() { return categories; }
    public List<Coupon> getCoupons() { return coupons; }
    public List<Rider> getRiders() { return riders; }
    public List<Order> getOrders() { return orders; }

    public AdminStats getAdminStats() {
        AdminStats stats = new AdminStats();
        int totalOrders = orders.size();
        int totalRevenue = orders.stream().filter(o -> !o.getStatus().equals("Cancelled")).mapToInt(Order::getGrandTotal).sum();
        int activeOrders = (int) orders.stream().filter(o -> !o.getStatus().equals("Delivered") && !o.getStatus().equals("Cancelled")).count();
        int activeRests = (int) restaurants.stream().filter(Restaurant::isOpen).count();

        Map<String, Integer> statusMap = new HashMap<>();
        statusMap.put("Placed", (int) orders.stream().filter(o -> o.getStatus().equals("Placed")).count());
        statusMap.put("Confirmed", (int) orders.stream().filter(o -> o.getStatus().equals("Confirmed")).count());
        statusMap.put("Preparing", (int) orders.stream().filter(o -> o.getStatus().equals("Preparing")).count());
        statusMap.put("On The Way", (int) orders.stream().filter(o -> o.getStatus().equals("On The Way")).count());
        statusMap.put("Delivered", (int) orders.stream().filter(o -> o.getStatus().equals("Delivered")).count());
        statusMap.put("Cancelled", (int) orders.stream().filter(o -> o.getStatus().equals("Cancelled")).count());

        stats.setTotalRevenue(totalRevenue);
        stats.setTotalOrders(totalOrders);
        stats.setActiveOrdersCount(activeOrders);
        stats.setActiveRestaurants(activeRests);
        stats.setActiveRiders(riders.size());
        stats.setOrdersByStatus(statusMap);
        return stats;
    }
}
