package com.sergsnmail.springdata.controllers;

import com.sergsnmail.springdata.entities.User;
import com.sergsnmail.springdata.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/score")
@RequiredArgsConstructor
public class ScoreController {

    private final UserService userService;

    @GetMapping("/inc")
    public void incScore(Principal principal){
         User user = userService.findByUsername(principal.getName()).orElseThrow();
         user.setScore(user.getScore() + 1);
         userService.saveOrUpdate(user);
    }

    @GetMapping("/dec")
    public void decScore(Principal principal){
        User user = userService.findByUsername(principal.getName()).orElseThrow();
        user.setScore(user.getScore() - 1);
        userService.saveOrUpdate(user);
    }

    @GetMapping("/get/current")
    public String currentScore(Principal principal){
        User user = userService.findByUsername(principal.getName()).orElseThrow();
        return "Current user: " + user.getUsername() + "; Score: " + user.getScore();
    }

    @GetMapping("/get/{id}")
    public String getUserScore(@PathVariable Long id){
        User user = userService.findByUserId(id).orElseThrow();
        return "User: " + user.getUsername() + "; Score: " + user.getScore();
    }
}
