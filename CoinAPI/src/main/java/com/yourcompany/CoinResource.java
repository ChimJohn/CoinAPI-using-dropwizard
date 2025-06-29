package com.yourcompany;

import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import java.util.*;

@Path("/coin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CoinResource {

    @POST
    public Response getMinimumCoins(CoinRequest request) {
        if (request.targetAmount < 0 || request.targetAmount > 10_000) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Target amount out of range.").build();
        }
        List<Double> denominations = request.denominations;
        List<Double> result = calculateMinimumCoins(request.targetAmount, denominations);
        if (result == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Cannot make target with given denominations.").build();
        }
        return Response.ok(new CoinResponse(result)).build();
    }

    protected List<Double> calculateMinimumCoins(double target, List<Double> denoms) {
        int targetCents = (int)Math.round(target * 100);
        int[] coinCents = denoms.stream()
                .mapToInt(d -> (int)Math.round(d * 100))
                .sorted()
                .toArray();

        int[] dp = new int[targetCents + 1];
        int[] prev = new int[targetCents + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i=1; i<=targetCents; i++) {
            for (int coin : coinCents) {
                if (coin <= i && dp[i-coin] != Integer.MAX_VALUE && dp[i-coin]+1 < dp[i]) {
                    dp[i] = dp[i-coin]+1;
                    prev[i] = coin;
                }
            }
        }

        if (dp[targetCents] == Integer.MAX_VALUE) return null;

        List<Double> result = new ArrayList<>();
        int k = targetCents;
        while (k > 0) {
            int coin = prev[k];
            result.add(coin/100.0);
            k -= coin;
        }
        Collections.sort(result);
        return result;
    }
}