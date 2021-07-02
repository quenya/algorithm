package dp;

import java.io.*;
import java.util.StringTokenizer;

public class Main_11723 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/dp/Main_11723.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int M = Integer.parseInt(br.readLine());
        int status = 0;
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if (cmd.equals("all")) {
                status = (1 << 20) - 1;
                continue;
            }
            if (cmd.equals("empty")) {
                status = 0;
                continue;
            }
            int num = Integer.parseInt(st.nextToken());
            switch (cmd) {
                case "add":
                    status |= (1 << (num - 1));
                    break;
                case "remove":
                    status &= ~(1 << (num - 1));
                    break;
                case "check":
                    int i1 = (status & (1 << (num - 1))) == 0 ? 0 : 1;
                    bw.write(i1 + "\n");
                    break;
                case "toggle":
                    status ^= (1 << (num - 1));
                    break;
                default:
                    break;
            }
        }
        bw.close();
        br.close();
    }
}
