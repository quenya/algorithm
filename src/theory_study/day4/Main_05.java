package theory_study.day4;

import java.io.*;
import java.util.StringTokenizer;

// 머리 맞대기
public class Main_05 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[] arr;
    static int[] prefix;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        arr = new int[N * 2];
        prefix = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < N; i++) {
            arr[i + N] = arr[i] = Integer.parseInt(st.nextToken());
        }
        prefix[0] = arr[0];
        for (int i = 0; i < N * 2; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }
        int head_sum = 0;
        int s = 0;
        int e = 500 * 100_000;
        while (s <= e) {
            int mid = (s + e) / 2;
            int minTeamCount = getMinTeamCount(mid);
            // 만족한 head sum
            if (minTeamCount <= T) {
                head_sum = mid;
                e = mid - 1;
            }
            // 부족한 head sum
            else {
                s = mid + 1;
            }
        }

        bw.write(head_sum + "");
        bw.close();
        br.close();
    }

    static int getMinTeamCount(int head_sum) {
        // 시작 지점 loop
        int min_team = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            // 누적합 구하기
            int sum = 0;
            int s = i;
            int count = 1;
            for (int j = i; j < i + N; j++) {
                if (arr[j] > head_sum)
                    return Integer.MAX_VALUE;
                sum = prefix[j] - prefix[s] + arr[s];
                if (sum > head_sum) {
                    count++;
                    s = j;
                    continue;
                }
            }
            min_team = Math.min(min_team, count);
        }
        return min_team;
    }
}
