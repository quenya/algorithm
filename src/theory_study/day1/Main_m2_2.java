package theory_study.day1;

import java.io.*;
import java.util.StringTokenizer;

class Node {
    int r, c;

    Node(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main_m2_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= t; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int num[] = new int[400001];
            int index = 0;
            long cnt[] = new long[400001];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int temp = Integer.parseInt(st.nextToken()) % m;
                if (temp < 0) temp += m;
                // 버튼 1회 입력
                if (cnt[temp] == 0) num[index++] = temp;
                cnt[temp]++;
            }

            int hap_num[] = new int[800002];
            int hap_index = 0;
            long hap_cnt[] = new long[400001];

            // 버튼 2회 입력 - 1회*1 조합
            for (int i = 0; i < index; i++) {
                for (int j = i; j < index; j++) {
                    int temp = (num[i] + num[j]);
                    if (temp >= m) temp -= m;
                    if (hap_cnt[temp] == 0) hap_num[hap_index++] = temp;
                    if (i == j)
                        hap_cnt[temp] += cnt[num[i]] * cnt[num[j]];
                    else
                        hap_cnt[temp] += cnt[num[i]] * cnt[num[j]] * 2;
                }
            }
            long ans = 0;

            // 버튼 4회 입력 - 2회*2회 조합
            for (int i = 0; i < hap_index; i++) {
                int temp = k - hap_num[i];
                if (temp < 0) temp += m;
                ans += hap_cnt[temp] * hap_cnt[hap_num[i]];
            }
            bw.write("#" + test_case + " " + ans + "\n");
        }

        br.close();
        bw.close();
    }
}