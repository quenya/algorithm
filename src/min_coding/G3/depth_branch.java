package min_coding.G3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class depth_branch {
    static int level;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        level = Integer.parseInt(s[0]);
        tree(0);
        br.close();
    }

    static void tree(int n) {
        if (n>level) return;
        if (n == level) {
            System.out.printf("%d", n);
            return;
        }
        System.out.printf("%d", n);
        for (int i = 0; i < 2; i++) {
            tree(n + 1);
        }
    }
}
