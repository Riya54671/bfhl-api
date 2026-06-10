package com.bfhl.bfhl_api.service;

import com.bfhl.bfhl_api.dto.BfhlRequest;
import com.bfhl.bfhl_api.dto.BfhlResponse;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    // ==== CHANGE THESE TO YOUR DETAILS ====
    private static final String USER_ID = "john_doe_17091999"; // full_name_ddmmyyyy (lowercase)
    private static final String EMAIL = "john@xyz.com";
    private static final String ROLL_NUMBER = "ABCD123";
    // ======================================

    @Override
    public BfhlResponse process(BfhlRequest request) {
        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        BigInteger sum = BigInteger.ZERO;
        StringBuilder allLetters = new StringBuilder();

        List<String> data = request.getData() == null ? new ArrayList<>() : request.getData();

        for (String token : data) {
            if (token == null) continue;

            if (isNumeric(token)) {
                BigInteger num = new BigInteger(token);
                sum = sum.add(num);
                if (num.testBit(0)) {       // odd
                    oddNumbers.add(token);
                } else {                    // even
                    evenNumbers.add(token);
                }
            } else if (isAlphabetic(token)) {
                alphabets.add(token.toUpperCase());
                allLetters.append(token);   // every letter feeds concat
            } else {
                specialCharacters.add(token);
                // pull any letters inside mixed tokens for concat
                for (char c : token.toCharArray()) {
                    if (Character.isLetter(c)) allLetters.append(c);
                }
            }
        }

        String concatString = buildConcatString(allLetters.toString());

        BfhlResponse response = new BfhlResponse();
        response.setSuccess(true);
        response.setUserId(USER_ID);
        response.setEmail(EMAIL);
        response.setRollNumber(ROLL_NUMBER);
        response.setOddNumbers(oddNumbers);
        response.setEvenNumbers(evenNumbers);
        response.setAlphabets(alphabets);
        response.setSpecialCharacters(specialCharacters);
        response.setSum(sum.toString());
        response.setConcatString(concatString);
        return response;
    }

    private boolean isNumeric(String s) {
        if (s.isEmpty()) return false;
        // allow optional leading minus
        int start = (s.charAt(0) == '-' && s.length() > 1) ? 1 : 0;
        for (int i = start; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }

    private boolean isAlphabetic(String s) {
        if (s.isEmpty()) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c)) return false;
        }
        return true;
    }

    // reverse all letters, then alternating caps starting UPPER
    private String buildConcatString(String letters) {
        StringBuilder reversed = new StringBuilder(letters).reverse();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            result.append(i % 2 == 0 ? Character.toUpperCase(c) : Character.toLowerCase(c));
        }
        return result.toString();
    }
}