package theory_study.day4;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// Root 계산기
public class Main_02 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine().trim());
        int s = 1;
        int e = 3170;
        int index = -1;
        while (s <= e) {
            int mid = (s+e)/2;
            if (mid*mid == N) {
                index = N;
                break;
            }
            if (mid*mid < N)
                s = mid+1;
            else if (mid*mid > N)
                index = e = mid - 1;
        }
        bw.write(index + "");
        bw.close();
        br.close();
    }
}
