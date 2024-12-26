package br.com.rogrs.bets.controller.rest;

import br.com.rogrs.bets.service.MegaSenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/numbers")
public class MegaSenaController {

    private final MegaSenaService megaSenaService;
    @Autowired
    public MegaSenaController(MegaSenaService megaSenaService) {
        this.megaSenaService = megaSenaService;
    }

    @GetMapping("/generate/{numberOfBets}")
    public ResponseEntity<?> generateNumbers(@PathVariable(required = false) Integer numberOfBets) {
        try {
            int bets = (numberOfBets == null || numberOfBets <= 0) ? 1 : numberOfBets;
            return ResponseEntity.ok(megaSenaService.generateMultipleBets(bets));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error generating numbers: " + e.getMessage());
        }
    }
}