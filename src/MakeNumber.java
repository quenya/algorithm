import java.util.Scanner;

import static java.util.stream.IntStream.range;

public class MakeNumber {
    // 1. table 정의
    // 2. 점화식 세우기
    // T(i) = 1~M 까지 숫자를 사용해서 i를 만드는 경우의 수
    // T(i) = T(i - 1) + T(i - 2) + ...
    // Table[1] = 1
    // Table[2] = Table[1] + 1
    // Table[3] = Table[2] + Table[1] + 1
    // Table[4] = Table[3] + Table[2] + Table[1] + 1
    // Table[m] = Table[m-1] + ... + Table[3] + Table[2] + Table[1] + 1

    private static int Table[];
    private static int n, m;
    private static int sum = 0;
    private static int i;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        Table = new int[n + 1];
        Table[1] = 1;
        // 기저 조건
        range(2, m + 1).forEach(i -> {
            sum += Table[i - 1];
            Table[i] = sum + 1;
        });

        // 기저 조건 초과
        range(m + 1, n + 1).forEach(i ->
                range(i - m, i).forEach(j ->
                        Table[i] += Table[j]
                )
        );
//        for (int i=m+1; i<=n; i++) {
//            for (int j=i-m; j<=i-1; j++) {
//                Table[i] += Table[j];
//            }
//        }
        System.out.println(Table[n] % 1000007);
    }
}
