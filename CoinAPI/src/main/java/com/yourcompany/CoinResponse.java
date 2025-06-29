package com.yourcompany;

import java.util.List;

public class CoinResponse {
    public List<Double> coins;

    public CoinResponse(List<Double> coins) {
        this.coins = coins;
    }
}