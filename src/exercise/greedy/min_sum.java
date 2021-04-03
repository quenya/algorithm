package exercise.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class min_sum {
    static int N;
    static int[] M;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = new int[N];
        List<Integer> minusList = new ArrayList<>();
        List<Integer> plusList = new ArrayList<>();
        boolean hasZero = false;
        for (int i = 0; i < N; i++) {
            int n = sc.nextInt();
            M[i] = n;

            if (n == 0) hasZero = true;
            else if (n < 0) minusList.add(n);
            else plusList.add(n);
        }
        sc.close();

        if (N == 2) {
            print(M[0], M[1]);
            return;
        }

        int minSum = Integer.MAX_VALUE;
        int minA = Integer.MAX_VALUE;
        int minB = Integer.MAX_VALUE;
        // 0 이 있는 경우
        if (hasZero) {
            // 음수
            int minus = minusList.stream().sorted(Collections.reverseOrder()).findFirst().map(Math::abs).orElse(0);
            int plus = plusList.stream().sorted().findFirst().orElse(0);
            if (minus < plus) {
                minSum = minus * -1;
                minA = 0;
                minB = minSum;
            }
            else if (minus > plus) {
                minSum = plus;
                minA = 0;
                minB = minSum;
            }
        }
        // 음수 합
        if (!minusList.isEmpty() && minusList.size() > 1) {
            List<Integer> list = minusList.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
            int sum = list.get(0) + list.get(1);
            if (Math.abs(minSum) > Math.abs(sum)) {
                minSum = sum;
                minA = list.get(0);
                minB = list.get(1);
            }
        }
        // 양수 합
        if (!plusList.isEmpty() && plusList.size() > 1) {
            List<Integer> list = plusList.stream().sorted().collect(Collectors.toList());
            int sum = list.get(0) + list.get(1);
            if (Math.abs(minSum) > Math.abs(sum)) {
                minSum = sum;
                minA = list.get(0);
                minB = list.get(1);
            }
        }
        if (minusList.isEmpty() || plusList.isEmpty()) {
            System.out.println(minA + " " + minB);
            return;
        }

        // 최소 합
        int sum = minusList.get(0) + plusList.get(0);
        if (Math.abs(minSum) > Math.abs(sum)) {
            minSum = sum;
            minA = minusList.get(0);
            minB = plusList.get(0);
        }

        // 최대 합
        sum = minusList.get(minusList.size()-1) + plusList.get(plusList.size()-1);
        if (Math.abs(minSum) > Math.abs(sum)) {
            minSum = sum;
            minA = minusList.get(minusList.size()-1);
            minB = plusList.get(plusList.size()-1);
        }

        print(minA, minB);
    }

    static void print(int a, int b) {
        if (a < b)
            System.out.println(a + " " + b);
        else
            System.out.println(b + " " + a);
    }
}
