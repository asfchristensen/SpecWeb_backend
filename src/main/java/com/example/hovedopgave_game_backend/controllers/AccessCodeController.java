package com.example.hovedopgave_game_backend.controllers;

import com.example.hovedopgave_game_backend.models.AccessCode;
import com.example.hovedopgave_game_backend.services.AccessCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @PutMapping
    public ResponseEntity updateAccessCode(@RequestBody AccessCode accessCodeToUpdate) {
        Optional<AccessCode> accessCode_ = accessCodeService.findById(accessCodeToUpdate.getId());
        if (accessCode_.isPresent()) {

            accessCodeService.save(accessCodeToUpdate);
            return new ResponseEntity<>(accessCodeService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
