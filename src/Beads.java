import java.util.Scanner;

public class Beads {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        boolean result = true;
        if (n == 0) result = false;
        else if (n == 1) result = true;
        else if (n % 4 == 0) result = false;

        System.out.println(result? "YES": "NO");
    }
}
