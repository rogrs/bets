package br.com.rogrs.bets.service;


import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MegaSenaService {


    public Map<String, List<String>> generateMultipleBets(int numberOfBets) {
        Map<String, List<String>> bets = new LinkedHashMap<>();

        for (int i = 1; i <= numberOfBets; i++) {
            bets.put("Bet " + i, generateSingleBet());
        }

        return bets;
    }

    private List<String> generateSingleBet() {
        Random random = new Random();
        Set<Integer> numbers = new HashSet<>();

        while (numbers.size() < 6) {
            int randomNumber = random.nextInt(60) + 1;
            numbers.add(randomNumber);
        }

        // Formata os números como strings com dois dígitos
        return numbers.stream()
                .sorted()
                .map(n -> String.format("%02d", n))
                .collect(Collectors.toList());
    }
}
