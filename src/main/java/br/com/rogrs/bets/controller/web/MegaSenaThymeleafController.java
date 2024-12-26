package br.com.rogrs.bets.controller.web;

import br.com.rogrs.bets.service.MegaSenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MegaSenaThymeleafController {

    private final MegaSenaService megaSenaService;

    @Autowired
    public MegaSenaThymeleafController(MegaSenaService megaSenaService) {
        this.megaSenaService = megaSenaService;
    }

    @GetMapping("/")
    public String showGeneratePage(Model model) {
        model.addAttribute("numberOfBets", 1);  // Valor padrão para o número de apostas
        model.addAttribute("numbersPerBet", 6); // Valor padrão para o número de números por aposta
        return "generate";
    }

    @PostMapping("/generate")
    public String generateNumbers(@RequestParam(defaultValue = "1") Integer numberOfBets,
                                  @RequestParam(defaultValue = "6") Integer numbersPerBet,
                                  Model model) {
        try {
            model.addAttribute("bets", megaSenaService.generateMultipleBets(numberOfBets, numbersPerBet));
        } catch (Exception e) {
            model.addAttribute("error", "Error generating numbers: " + e.getMessage());
        }
        return "generate";
    }
}
