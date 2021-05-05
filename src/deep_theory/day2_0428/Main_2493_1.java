package deep_theory.day2_0428;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2493_1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        Stack<int[]> stack = new Stack<>();
        int[] ans = new int[N+1];
        int[] arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = N; i >= 1; i--) {
            while (!stack.isEmpty()) {
                if (arr[i] >= stack.peek()[1]) {
                    ans[stack.peek()[0]] = i;
                    stack.pop();
                }
                else
                    break;
            }
            stack.push(new int[] {i, arr[i]});
        }
        for (int i = 1; i <=N; i++) {
            bw.write(ans[i] + " ");
        }

        bw.close();
        br.close();
    }
}
