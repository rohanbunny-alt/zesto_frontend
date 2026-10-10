package com.zesto.app.controller;

import com.zesto.app.model.MenuItem;
import com.zesto.app.model.Restaurant;
import com.zesto.app.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final DataService dataService;

    @Autowired
    public RestaurantController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping
    public List<Restaurant> getRestaurants(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String category,
            @RequestParam(required = false, defaultValue = "false") boolean vegOnly
    ) {
        List<Restaurant> list = dataService.getRestaurants();

        return list.stream().filter(r -> {
            if (vegOnly && !r.isPureVeg()) return false;

            if (category != null && !category.trim().isEmpty()) {
                String catLower = category.toLowerCase().trim();
                boolean matchCuisine = r.getCuisine().stream().anyMatch(c -> c.toLowerCase().contains(catLower));
                boolean matchMenu = r.getMenu().stream().anyMatch(m -> m.getCategory().toLowerCase().contains(catLower));
                if (!matchCuisine && !matchMenu) return false;
            }

            if (search != null && !search.trim().isEmpty()) {
                String q = search.toLowerCase().trim();
                boolean matchName = r.getName().toLowerCase().contains(q);
                boolean matchCuisine = r.getCuisine().stream().anyMatch(c -> c.toLowerCase().contains(q));
                boolean matchDish = r.getMenu().stream().anyMatch(m -> m.getName().toLowerCase().contains(q));
                return matchName || matchCuisine || matchDish;
            }

            return true;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable String id) {
        Optional<Restaurant> found = dataService.getRestaurants().stream().filter(r -> r.getId().equals(id)).findFirst();
        return found.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant req) {
        String newId = "rest-" + System.currentTimeMillis();
        req.setId(newId);
        if (req.getImage() == null || req.getImage().isEmpty()) {
            req.setImage("https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?w=600&auto=format&fit=crop&q=80");
        }
        if (req.getRating() == 0) req.setRating(4.5);
        if (req.getRatingCount() == null) req.setRatingCount("NEW");
        if (req.getDeliveryTime() == null) req.setDeliveryTime("25-30 min");
        if (req.getLocation() == null) req.setLocation("Bangalore Central");
        if (req.getOffer() == null) req.setOffer("20% OFF");
        req.setOpen(true);
        if (req.getMenu() == null) req.setMenu(new ArrayList<>());

        dataService.getRestaurants().add(0, req);
        return ResponseEntity.status(HttpStatus.CREATED).body(req);
    }

    @PostMapping("/{id}/menu")
    public ResponseEntity<MenuItem> addMenuItem(@PathVariable String id, @RequestBody MenuItem item) {
        Optional<Restaurant> found = dataService.getRestaurants().stream().filter(r -> r.getId().equals(id)).findFirst();
        if (found.isEmpty()) return ResponseEntity.notFound().build();

        item.setId("item-" + System.currentTimeMillis());
        if (item.getCategory() == null) item.setCategory("Main Course");
        if (item.getImage() == null) item.setImage("https://images.unsplash.com/photo-1546069901-ba9599a7e63c?w=400&auto=format&fit=crop&q=80");
        item.setRating(4.5);
        item.setRatingCount(1);

        found.get().getMenu().add(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @DeleteMapping("/{id}/menu/{itemId}")
    public ResponseEntity<Map<String, Object>> deleteMenuItem(@PathVariable String id, @PathVariable String itemId) {
        Optional<Restaurant> found = dataService.getRestaurants().stream().filter(r -> r.getId().equals(id)).findFirst();
        if (found.isEmpty()) return ResponseEntity.notFound().build();

        found.get().getMenu().removeIf(m -> m.getId().equals(itemId));
        Map<String, Object> res = new HashMap<>();
        res.put("success", true);
        res.put("message", "Item deleted");
        return ResponseEntity.ok(res);
    }
}
