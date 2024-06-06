package com.example.teke.ESHOP.controller;

import com.example.teke.ESHOP.model.Customer;
import com.example.teke.ESHOP.model.LoginRequest;
import com.example.teke.ESHOP.model.LoginResponse;
import com.example.teke.ESHOP.repository.CustomerRepository;
import io.jsonwebtoken.Claims;
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

    private static final String SECRET_KEY = "2D4A614E645267556B58703273357638792F423F4428472B4B6250655368566D";
    private static final long EXPIRATION_TIME = 3600000; // 1 hour

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        String user = request.getUser();
        String pass = request.getPass();

        Customer customer = customerRepository.findByUsername(user);

        if (customer != null && customer.getPassword().equals(pass)) {
            String token = generateToken(customer);
            LoginResponse response = new LoginResponse(token, user);
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    private String generateToken(Customer customer) {
        long currentTime = System.currentTimeMillis();
        Date expirationDate = new Date(currentTime + EXPIRATION_TIME);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", customer.getId());
        claims.put("username", customer.getUsername());
        claims.put("email", customer.getEmail());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(customer.getUsername())
                .setIssuedAt(new Date(currentTime))
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    private Claims decodeToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    @GetMapping("/getUserInfo")
    public ResponseEntity<Map<String, Object>> getUserInfo(@RequestHeader("Authorization") String token) {
        Claims claims = decodeToken(token.replace("Bearer ", ""));
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("userId", claims.get("userId"));
        userInfo.put("username", claims.get("username"));
        userInfo.put("email", claims.get("email"));

        return ResponseEntity.ok(userInfo);
    }
}
