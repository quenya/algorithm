package exercise.greedy;

import java.util.Scanner;

public class min_coin {
    static int N;
    static int[] coins = {1, 5, 50, 100, 500};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sc.close();

        int count = 0;
        for (int i = coins.length - 1; i >= 0; i--) {
            int coin = coins[i];
            if (coin > N) continue;
            int c = N / coin;
            count += c;
            N -= coin * c;
            if (N <= 0) break;
        }

        System.out.println(count);
    }
}
