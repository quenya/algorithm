package theory_study.day1;

import java.io.*;
import java.util.StringTokenizer;

public class Main_05 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] MAP = new int[4][8];

    public static void main(String[] args) throws IOException {
        for(int i = 0; i < 4; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 8; j++)
            {
                MAP[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = 0;
        for(int i = 0 ; i < 4; i++)
            for(int j = 0; j < 8; j++)
            {
                //MAP[i][j] 시작
                for(int row_size = 1; row_size <= 4 - i; row_size++)
                    for(int col_size = 1; col_size <= 8 - j; col_size++)
                    {
                        //직사각형의 크기
                        int sum = 0;
                        boolean check = true;
                        for(int row = i; row < i + row_size; row++)
                            for(int col = j; col < j + col_size; col++)
                            {
                                //직사각형 내부
                                sum += MAP[row][col];
                                if(MAP[row][col] == 0) check = false;
                            }
                        if(check && ans < sum) ans = sum;
                    }
            }
        bw.write(ans + "");

        br.close();
        bw.close();
    }
}
