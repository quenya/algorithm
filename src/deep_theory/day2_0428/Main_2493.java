package deep_theory.day2_0428;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2493 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        Stack<int[]> stack = new Stack<>();
        for (int i = 1; i <= N; i++) {
            int t = Integer.parseInt(st.nextToken());
            while (!stack.isEmpty()) {
                if (stack.peek()[1] >= t) {
                    bw.write(stack.peek()[0] + " ");
                    break;
                }
                stack.pop();
            }
            if (stack.isEmpty()) {
                bw.write("0 ");
            }
            stack.push(new int[] {i, t});
        }

        bw.close();
        br.close();
    }
}
