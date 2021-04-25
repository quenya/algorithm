package theory_study.day4;

import java.io.*;

// 연료 게이지
public class Main_03 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < N; i++) {
            String trim = br.readLine().trim();
            int s = 0;
            int e = trim.length()-1;
            int index = -1;
            while (s <= e) {
                int mid = (s+e)/2;
                if (trim.charAt(mid) == '_') {
                    e = index = mid-1;
                }
                else {
                    s = mid+1;
                }
            }
            if (index == 0 && trim.charAt(0) == '#')
                index = 1;
            else if (index == -1 && trim.charAt(0) == '#')
                index = trim.length();
            else
                index++;
            bw.write(index * 100/trim.length()+ "%\n");
        }
        bw.close();
        br.close();
    }
}
