package com.example.teke.ESHOP.controller;

// AuthController.java

import com.example.teke.ESHOP.model.Customer;
import com.example.teke.ESHOP.model.LoginRequest;
import com.example.teke.ESHOP.model.LoginResponse;
import com.example.teke.ESHOP.repository.CustomerRepository;
import com.example.teke.ESHOP.service.CustomerService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        String user = request.getUser();
        String pass = request.getPass();

        Customer customer = customerRepository.findByUsername(user);

        if (customer != null && customer.getPassword().equals(pass)) {
            // Token oluşturma
            String token = generateToken(customer);

            // Yanıtı hazırlama
            LoginResponse response = new LoginResponse(token, user);

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }



    private String generateToken(Customer customer) {
        // Secret key for signing the token (choose a secure key and keep it secret)
        String secretKey = "2D4A614E645267556B58703273357638792F423F4428472B4B6250655368566D";

        // Token expiration time (e.g., 1 hour)
        long expirationTime = 3600000;

        // Current time and expiration time
        long currentTime = System.currentTimeMillis();
        Date expirationDate = new Date(currentTime + expirationTime);

        // Payload: Define the claims (data) you want to include in the token
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", customer.getId());
        claims.put("username", customer.getUsername());

        // Create and sign the token
        String token = Jwts.builder()
                .setClaims(claims) // Include the claims
                .setSubject(customer.getUsername()) // Set subject (user's username)
                .setIssuedAt(new Date(currentTime)) // Set the token's issue time
                .setExpiration(expirationDate) // Set the token's expiration time
                .signWith(SignatureAlgorithm.HS256, secretKey) // Sign the token with the secret key
                .compact(); // Build the token

        return token;
    }
}
