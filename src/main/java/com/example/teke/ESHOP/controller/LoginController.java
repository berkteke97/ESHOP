package com.example.teke.ESHOP.controller;

import com.example.teke.ESHOP.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        // Kullanıcı doğrulaması yapılabilir, örneğin bir UserService kullanılabilir.
        // Bu örnekte, sadece basit bir örnek amacıyla doğrudan "admin" kullanıcısını kabul ediyoruz.
        if ("admin".equals(username) && "password".equals(password)) {
            // Kullanıcı doğrulandı, session oluşturulabilir.
            session.setAttribute("username", username);
            return "redirect:/home"; // Giriş başarılıysa yönlendirilecek sayfa
        } else {
            // Kullanıcı doğrulanamadı, hata mesajı gösterilebilir.
            return "redirect:/login?error";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Oturumu sonlandır
        session.invalidate();
        return "redirect:/login";
    }

    // Diğer controller metotları ve işlemler...

}