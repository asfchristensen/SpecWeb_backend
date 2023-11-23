package com.example.hovedopgave_game_backend.controllers;

import com.example.hovedopgave_game_backend.models.AccessCode;
import com.example.hovedopgave_game_backend.models.Spectator;
import com.example.hovedopgave_game_backend.services.AccessCodeService;
import com.example.hovedopgave_game_backend.services.SpectatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("spectator/access-code")
public class AccessCodeController {
    @Autowired
    private AccessCodeService accessCodeService;

    @Autowired
    private SpectatorService spectatorService;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(accessCodeService.findAll());
    }



    @PutMapping("/{tokenId}")
    public ResponseEntity updateAccessCode(@RequestBody AccessCode accessCode, @PathVariable("tokenId") String subID) {
        Optional<Spectator> spectator = spectatorService.findByTokenId(subID);
        Optional<AccessCode> accessCodeToUpdate = accessCodeService.findById(accessCode.getId());

        if (spectator.isPresent() && accessCodeToUpdate.isPresent()) {
            accessCodeToUpdate.get().setSpectator(accessCode.getSpectator());
            accessCodeToUpdate.get().setActivated(accessCode.isActivated());
            accessCodeService.save(accessCodeToUpdate.get());
            return new ResponseEntity<>(accessCodeToUpdate, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

