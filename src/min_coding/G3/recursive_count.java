package min_coding.G3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class recursive_count {
    static int branch;
    static int level;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        branch = Integer.parseInt(st.nextToken());
        level = Integer.parseInt(st.nextToken());
        br.close();
        tree(0);
        System.out.println(count);
    }

    static void tree(int n) {
        if (n>level) return;
        if (n == level) {
            count++;
            return;
        }
        count++;
        for (int i = 0; i < branch; i++) {
            tree(n + 1);
        }
    }

}
