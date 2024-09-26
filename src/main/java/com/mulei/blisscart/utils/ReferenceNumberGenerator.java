package com.mulei.blisscart.utils;

import com.mulei.blisscart.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

@Service
public class ReferenceNumberGenerator {

    @Autowired
    private OrderRepository orderRepository;

    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random RANDOM = new SecureRandom();



    public String generateUniqueReferenceNumber(int minLength, int maxLength) {
        String referenceNumber;
        boolean exists;

        // Loop until a unique reference number is found
        do {
            referenceNumber = generateReferenceNumber(minLength, maxLength);
            exists = orderRepository.existsByReferenceNumber(referenceNumber);
        } while (exists);

        return referenceNumber;
    }


    public static String generateReferenceNumber(int minLength, int maxLength) {
        int length = RANDOM.nextInt((maxLength - minLength) + 1) + minLength;
        StringBuilder reference = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            reference.append(ALPHANUMERIC.charAt(RANDOM.nextInt(ALPHANUMERIC.length())));
        }

        return reference.toString();
    }
}
