package theory_study.day2;

public class Main_01 {

    static void gogo() {
        System.out.println("GOGO");
    }

    static void bts() {
        gogo();
        System.out.println("BTS Last");
    }

    static void abc() {
        bts();
        gogo();
        System.out.println("ABC Last");
    }

    public static void main(String[] args) {
        gogo();
        abc();
        bts();
        System.out.println("HOME");
    }
}