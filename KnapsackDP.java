public class KnapsackDP {

    public static void main(String[] args) {

        int[] weights = {2, 3, 4, 5, 9, 7, 6, 8};
        int[] profits = {3, 4, 5, 8, 10, 7, 6, 9};

        int capacity = 20;
        int n = weights.length;

        int[][] dp = new int[n + 1][capacity + 1];

        // Build DP Table
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {

                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(
                            profits[i - 1] + dp[i - 1][w - weights[i - 1]],
                            dp[i - 1][w]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println("Maximum Profit: " + dp[n][capacity]);

        // Backtracking to find selected orders
        System.out.println("Selected Orders:");

        int w = capacity;

        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                System.out.println("Order " + i +
                        " (Weight = " + weights[i - 1] +
                        ", Profit = " + profits[i - 1] + ")");
                w -= weights[i - 1];
            }
        }
    }
}