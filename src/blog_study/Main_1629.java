package blog_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1629 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int A = Integer.parseInt(s[0]);
        int B = Integer.parseInt(s[1]);
        int C = Integer.parseInt(s[2]);
        br.close();

        System.out.println(DP(A, B, C) % C);
    }

    private static long DP(int a, int b, int c) {
        if (b == 0) return 1;
        if (b == 1) return a;
        long n = DP(a, b / 2, c) % c;
        if (b % 2 == 0) return (n * n) % c;
        return ((n * n) % c) * a;
    }
}
