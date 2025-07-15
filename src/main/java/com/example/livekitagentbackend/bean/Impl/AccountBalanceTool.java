package com.example.livekitagentbackend.bean.Impl;

import com.example.livekitagentbackend.bean.Tool;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;

@Component
public class AccountBalanceTool implements Tool {

    @Override
    public String getName() {
        return "account_balance";
    }

    @Override
    public String invoke(Map<String, Object> context) {
        String customerName = (String) context.getOrDefault("customerName", "User");
        int balance = new Random().nextInt(100000);
        return "Account balance for " + customerName + " is ₹" + balance;
    }
}
