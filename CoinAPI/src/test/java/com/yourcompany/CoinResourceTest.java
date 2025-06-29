package com.yourcompany;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CoinResourceTest {

    @Test
    void testExample1() {
        CoinResource r = new CoinResource();
        List<Double> res = r.calculateMinimumCoins(7.03, Arrays.asList(0.01,0.5,1.0,5.0,10.0));
        assertEquals(Arrays.asList(0.01,0.01,0.01,1.0,1.0,5.0), res);
    }

    @Test
    void testExample2() {
        CoinResource r = new CoinResource();
        List<Double> res = r.calculateMinimumCoins(103, Arrays.asList(1.0,2.0,50.0));
        assertEquals(Arrays.asList(1.0,2.0,50.0,50.0), res);
    }

    /*
    @Test
    void testImpossibleCase() {
        CoinResource r = new CoinResource();
        List<Double> res = r.calculateMinimumCoins(1.0, Arrays.asList(0.3,0.4));
        assertNull(res); // impossible to make exactly 1.0 with 0.3/0.4
    }
    */
}