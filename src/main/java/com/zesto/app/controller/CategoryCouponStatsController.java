package com.zesto.app.controller;

import com.zesto.app.model.*;
import com.zesto.app.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("/api")
public class CategoryCouponStatsController {

    private final DataService dataService;

    @Autowired
    public CategoryCouponStatsController(DataService dataService) {
        this.dataService = dataService;
    }

    // Categories
    @GetMapping("/categories")
    public List<FoodCategory> getCategories() {
        return dataService.getCategories();
    }

    // Coupons
    @GetMapping("/coupons")
    public List<Coupon> getCoupons() {
        return dataService.getCoupons();
    }

    @PostMapping("/coupons/apply")
    public ResponseEntity<Map<String, Object>> applyCoupon(@RequestBody Map<String, Object> body) {
        String code = (String) body.get("code");
        Number cartAmountNum = (Number) body.get("cartAmount");
        int cartAmount = cartAmountNum != null ? cartAmountNum.intValue() : 0;

        Map<String, Object> response = new HashMap<>();

        Optional<Coupon> couponOpt = dataService.getCoupons().stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst();

        if (couponOpt.isEmpty()) {
            response.put("success", false);
            response.put("message", "Invalid coupon code");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Coupon coupon = couponOpt.get();
        if (cartAmount < coupon.getMinOrder()) {
            response.put("success", false);
            response.put("message", "Minimum order amount for " + coupon.getCode() + " is ₹" + coupon.getMinOrder());
            return ResponseEntity.badRequest().body(response);
        }

        int discount = 0;
        if (coupon.isFreeDelivery()) {
            discount = 35;
        } else {
            discount = Math.min(Math.round((cartAmount * coupon.getDiscountPercentage()) / 100.0f), coupon.getMaxDiscount());
        }

        response.put("success", true);
        response.put("coupon", coupon);
        response.put("discountAmount", discount);
        response.put("message", "Coupon '" + coupon.getCode() + "' applied successfully!");
        return ResponseEntity.ok(response);
    }

    // Orders
    @GetMapping("/orders")
    public List<Order> getOrders() {
        return dataService.getOrders();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        Optional<Order> found = dataService.getOrders().stream().filter(o -> o.getOrderId().equals(id)).findFirst();
        return found.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/orders")
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody Order order) {
        String newId = "ORD-" + (1000 + new Random().nextInt(9000));
        order.setOrderId(newId);
        if (order.getCustomerName() == null) order.setCustomerName("Valued Customer");
        if (order.getCustomerPhone() == null) order.setCustomerPhone("+91 98765 43210");
        if (order.getDeliveryAddress() == null) order.setDeliveryAddress("123 Swiggy Street, Bangalore");
        if (order.getPaymentMethod() == null) order.setPaymentMethod("UPI");
        order.setPaymentStatus("Paid");
        order.setStatus("Placed");
        order.setPlacedAt(Instant.now().toString());
        order.setEtaMinutes(30);

        List<Rider> riders = dataService.getRiders();
        if (!riders.isEmpty()) {
            order.setRider(riders.get(new Random().nextInt(riders.size())));
        }

        dataService.getOrders().add(0, order);

        Map<String, Object> res = new HashMap<>();
        res.put("success", true);
        res.put("order", order);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping("/orders/{id}/status")
    public ResponseEntity<Map<String, Object>> updateOrderStatus(@PathVariable String id, @RequestBody Map<String, String> body) {
        String newStatus = body.get("status");
        Optional<Order> found = dataService.getOrders().stream().filter(o -> o.getOrderId().equals(id)).findFirst();
        if (found.isEmpty()) return ResponseEntity.notFound().build();

        Order order = found.get();
        order.setStatus(newStatus);
        if ("Delivered".equalsIgnoreCase(newStatus)) {
            order.setEtaMinutes(0);
        } else if ("On The Way".equalsIgnoreCase(newStatus)) {
            order.setEtaMinutes(10);
        } else if ("Preparing".equalsIgnoreCase(newStatus)) {
            order.setEtaMinutes(20);
        }

        Map<String, Object> res = new HashMap<>();
        res.put("success", true);
        res.put("order", order);
        return ResponseEntity.ok(res);
    }

    // Stats & Fleet
    @GetMapping("/stats")
    public AdminStats getStats() {
        return dataService.getAdminStats();
    }

    @GetMapping("/riders")
    public List<Rider> getRiders() {
        return dataService.getRiders();
    }
}
