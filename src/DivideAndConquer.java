import java.util.stream.IntStream;

public class DivideAndConquer {
    private static int[] map;
    private static int MAX_COUNT = 10;
    public static void main(String[] args) {
        map = new int[MAX_COUNT + 1];

        IntStream.range(0, MAX_COUNT + 1)
                .forEach(i -> {
           if (i <= 1)
               map[i] = 1;
           else
               map[i] = map[i - 1] + map[i - 2];
        });
        System.out.println(map[MAX_COUNT]);
    }
}
