package theory_study.day1;

import java.io.*;
import java.util.StringTokenizer;

// 예식장 서빙
public class Main_11 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine().trim());
            int[] food = new int[N+R];
            for (int i = 0; i < N; i++) {
                food[i] = Integer.parseInt(st.nextToken());
            }
            boolean result = sol1(food, R, N);
//            boolean result = sol2(food, R, N);

            bw.write("#" + tc + " " + (result? "YES":"NO") + "\n");
        }
        br.close();
        bw.close();
    }

    private static boolean sol2(int[] food, int r, int n) {
        boolean result = true;
        int[] check = new int[201];
        for (int i = 0; i < n; i++) {
            check[food[i%n]]++;
            if (check[food[i%n]] > 2) {
                return false;
            }
            if (i>=r*2) {
                check[food[(i-(r*2))%n]]--;
            }
        }
        return result;
    }

    static boolean sol1(int[] food, int R, int N) {
        boolean result = true;
        for (int i = 0; i < R; i++)
            food[N + i] = food[i];
        int[] check = new int[201];
        for (int i = 0; i < R * 2+1; i++) {
            check[food[i]]++;
            if (check[food[i]] > 2)
                return false;
        }
        for (int i = 1; i < N-R; i++) {
            check[food[i-1]]--;
            check[food[i+R*2]]++;
            if (check[food[i+R*2]] > 2) {
                result = false;
                break;
            }
        }
        return result;
    }
}
