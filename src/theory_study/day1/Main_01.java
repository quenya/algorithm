package theory_study.day1;

import java.io.*;
import java.util.StringTokenizer;

public class Main_01 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int n = Integer.parseInt(st.nextToken());
        switch (n) {
            case 1:
                st = new StringTokenizer(br.readLine().trim());
                int len = Integer.parseInt(st.nextToken());
                st = new StringTokenizer(br.readLine().trim());
                int sum = 0;
                long multi = 1;
                for (int i = 0; i < len; i++) {
                    int num = Integer.parseInt(st.nextToken());
                    sum += num;
                    multi *= num;
                }
                bw.write(sum + " " + multi);
                break;
            case 2:
                st = new StringTokenizer(br.readLine().trim());
                len = Integer.parseInt(st.nextToken());
                String min = "";
                String max = "";
                int min_len = Integer.MAX_VALUE;
                int max_len = Integer.MIN_VALUE;
                for (int i = 0; i < len; i++) {
                    String s = br.readLine().trim();
                    if (max_len < s.length()) {
                        max_len = s.length();
                        max = s;
                    }
                    if (min_len > s.length()) {
                        min_len = s.length();
                        min = s;
                    }
                }
                bw.write(max + "\n" + min);
                break;
            case 3:
                st = new StringTokenizer(br.readLine().trim());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                int min_value = Integer.MAX_VALUE;
                int count = 0;
                for (int i = 0; i < y; i++) {
                    st = new StringTokenizer(br.readLine().trim());
                    for (int j = 0; j < x; j++) {
                        int a = Integer.parseInt(st.nextToken());
                        if (min_value > a) {
                            min_value = a;
                            count = 1;
                        }
                        else if (min_value == a)
                            count++;
                    }
                }
                bw.write(min_value + "\n" + count + "개");
                break;
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
