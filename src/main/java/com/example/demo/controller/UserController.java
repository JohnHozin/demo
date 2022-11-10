package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/registration")
    public String registration(@ModelAttribute UserEntity user){
        ArrayList<UserEntity> users = (ArrayList<UserEntity>) userRepo.findAll();
        for (UserEntity u:users){
            if (u.getLogin().equals(user.getLogin())) return "Текущий логин занят";
        }
        userRepo.save(user);
        return "OK";
    }
}
