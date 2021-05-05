package deep_theory.day2_0428;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

// 히스토그램에서 가장 큰 직사각형 - stack
public class Main_6549_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            if (N == 0)
                break;
            arr = new long[N+1];
            // 0: index, 1: height
            Stack<long[]> stack = new Stack<>();
            for (int i = 0; i < N; i++) {
                arr[i] = Long.parseLong(st.nextToken());
            }
            long ans = 0;
            for (int i = 0; i <= N; i++) {
                long h = arr[i];
                long index = i;
                while (!stack.isEmpty()) {
                    long[] peek = stack.peek();
                    if (peek[1] > h) {
                        long[] prev = stack.pop();
                        index = prev[0];
                        long width = i-prev[0];
                        ans = Math.max(ans, width*prev[1]);
                        if (stack.isEmpty()) {
                            stack.push(new long[] {index, h});
                            break;
                        }
                    }
                    else if (peek[1] == h) {
                        long width = i- peek[0];
                        ans = Math.max(ans, width*h);
                        break;
                    }
                    else {
                        stack.push(new long[] {index, h});
                        break;
                    }
                }
                if (i==0)
                    stack.push(new long[] {0, h});
            }
            bw.write(ans + "\n");
        }

        bw.close();
        br.close();
    }
}
