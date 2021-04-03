package exercise.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class fractional_knapsack {
    static int N;
    static double M;
    static Snack[] snacks;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextDouble();
        snacks = new Snack[N];
        for (int i = 0; i < N; i++) {
            double w = sc.nextDouble();
            double c = sc.nextDouble();
            snacks[i] = new Snack(w, c);
        }
        sc.close();

        double maxCost = 0;
        List<Snack> snackList = Arrays.stream(snacks).sorted(Comparator.comparingDouble(Snack::getRate).reversed()).collect(Collectors.toList());
        for (int i = 0; i < snackList.size(); i++) {
            Snack snack = snackList.get(i);
            if (snack.w <= M) {
                maxCost += snack.c;
                M -= snack.w;
            }
            else {
                maxCost += snack.rate * M;
                M = 0;
            }

            if (M <= 0) break;
        }

        System.out.println(String.format("%.3f", maxCost));
    }

    static class Snack {
        double w;
        double c;
        double rate;

        public double getRate() {
            return rate;
        }

        public Snack(double w, double c) {
            this.w = w;
            this.c = c;
            this.rate = c / w;
        }
    }
}
