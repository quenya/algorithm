package deep_theory.day1_0331;

import java.io.*;
import java.util.StringTokenizer;

public class Main_11054 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 앞에서부터 LIS
        int[] front_lis = new int[N];
        front_lis[0] = 1;
        for (int i = 1; i < N; i++) {
            front_lis[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && front_lis[j]+1 > front_lis[i]) {
                    front_lis[i] = front_lis[j]+1;
                }
            }
        }
        // 뒤에서부터 LIS
        int[] rear_lis = new int[N];
        rear_lis[N-1] = 1;
        for (int i = N-1; i >= 0; i--) {
            rear_lis[i] = 1;
            for (int j = N-1; j >=i; j--) {
                if (arr[i] > arr[j] && rear_lis[j]+1 > rear_lis[i]) {
                    rear_lis[i] = rear_lis[j]+1;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans = Math.max(ans, front_lis[i]+rear_lis[i]);
        }
        // 출력
        bw.write((ans-1)+"");

        bw.close();
        br.close();
    }
}
