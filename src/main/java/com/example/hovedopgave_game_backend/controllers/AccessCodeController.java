package com.example.hovedopgave_game_backend.controllers;

import com.example.hovedopgave_game_backend.services.AccessCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("access-code")
public class AccessCodeController {
    @Autowired
    private AccessCodeService accessCodeService;

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(accessCodeService.findAll());
    }

}
