package exercise.DP;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class subSumMax {
    static int N;
    static int[] M;
    static int[] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = new int[N];
        map = new int[N];
        for (int i = 0; i < N; i++) {
            M[i] = sc.nextInt();
        }
        sc.close();

        map[0] = M[0];
        for (int i = 1; i < N; i++) {
            map[i] = Math.max(M[i], M[i] + map[i - 1]);
        }


        Integer integer = Arrays.stream(map).boxed()
                .max(Comparator.comparingInt(o -> o)).orElse(M[0]);
        System.out.println(integer);
    }
}
