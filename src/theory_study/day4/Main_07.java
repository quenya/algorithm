package theory_study.day4;

import java.io.*;
import java.util.StringTokenizer;

// 순환회로 검사
public class Main_07 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] map;
    static char[] root;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine().trim());
        map = new int[N][N];
        boolean result = true;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        root = new char[256];
        for (char i = 'A'; i < 'Z'; i++) {
            root[i] = i;
        }
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (map[i][j] == 1) {
                    char x = (char) ('A'+i);
                    char y = (char) ('A'+j);
                    boolean union = union(x, y);
                    if (!union) result = false;
                }
            }
        }

        bw.write(result? "STABLE" : "WARNING");
        bw.close();
        br.close();
    }
    static boolean union(char x, char y) {
        x = find(x);
        y = find(y);
        if (x == y)
            return false;
        root[y] = x;
        return true;
    }
    static char find(char x) {
        if (root[x] == x)
            return x;
        return find(root[x]);
    }
}
