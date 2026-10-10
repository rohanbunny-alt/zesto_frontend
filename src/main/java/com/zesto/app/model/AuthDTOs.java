package com.zesto.app.model;

public class AuthDTOs {

    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class SignupRequest {
        private String name;
        private String email;
        private String password;
        private String phone;
        private String role; // "CUSTOMER" or "ADMIN"

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }

        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }

        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }

    public static class AuthResponse {
        private boolean success;
        private String token;
        private String message;
        private User user;

        public AuthResponse(boolean success, String token, String message, User user) {
            this.success = success;
            this.token = token;
            this.message = message;
            this.user = user;
        }

        public boolean isSuccess() { return success; }
        public void setSuccess(boolean success) { this.success = success; }

        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }

        public User getUser() { return user; }
        public void setUser(User user) { this.user = user; }
    }
}
