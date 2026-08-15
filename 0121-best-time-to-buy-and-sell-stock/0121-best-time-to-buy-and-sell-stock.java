class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int buy = prices[0];
        int profit = 0;

        for (int i = 1; i < n; i++) {
            int sell = prices[i];
            int curr_profit = sell - buy;
            if (curr_profit > profit) profit = curr_profit;
            if (prices[i] < buy) buy = prices[i];
        }
        return profit;
    }
}