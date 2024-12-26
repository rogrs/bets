package br.com.rogrs.bets.service;


import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MegaSenaService {


    public Map<String, List<Integer>> generateMultipleBets(int numberOfBets, int numbersPerBet) {
        if (numbersPerBet < 6 || numbersPerBet > 20) {
            throw new IllegalArgumentException("The number of numbers per bet must be between 6 and 9.");
        }

        Map<String, List<Integer>> bets = new LinkedHashMap<>();
        Random random = new Random();

        for (int i = 1; i <= numberOfBets; i++) {
            List<Integer> bet = IntStream.rangeClosed(1, 60)
                    .boxed()
                    .collect(Collectors.toList());
            Collections.shuffle(bet, random);
            bets.put("Bet " + i, bet.subList(0, numbersPerBet).stream().sorted().collect(Collectors.toList()));
        }

        return bets;
    }
}
