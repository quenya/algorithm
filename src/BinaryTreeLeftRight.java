import java.util.Scanner;

public class BinaryTreeLeftRight {
    static int[] n1;
    static int[] n2;
    static int[] n1Ind;
    static int[] n2Ind;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case=1; test_case<=T; test_case++) {
            N = sc.nextInt();
            n1 = new int[N+1];
            n2 = new int[N+1];
            n1Ind = new int[N+1];
            n2Ind = new int[N+1];
            for (int i=1; i<=N; i++) {
                n1[i] = sc.nextInt();
                n1Ind[n1[i]] = i;
            }
            for (int i=1; i<=N; i++) {
                n2[i] = sc.nextInt();
                n2Ind[n2[i]] = i;
            }
            if (containOther(n1, n2))
                System.out.println("#" + test_case + " IMPOSSIBLE");
            else
                System.out.println("#" + test_case + " POSSIBLE");
        }
        sc.close();
    }

    static boolean containOther(int[] l, int[] r) {
        if (l.length == 0 || l.length == 1)
            return false;
        else if (l.length == 2) {
            if (l[0] == r[1] || l[1] == r[0])
                return false;
            else
                return true;
        }
        int mid = n2Ind[l[1]];
        if (mid > N/2) {
            return containRight(l, r, mid);
//            int[] rright = new int[N-mid+1];
//            int[] lright = new int[rright.length];
//            int[] rleft = new int[N-rright.length+1];
//            int[] lleft = new int[rleft.length];
//            int minIndLeft = 100_000;
//            int maxIndLeft = -1;
//            int minIndRight = 100_000;
//            int maxIndRight = -1;
//            for (int i=1; i<=N; i++) {
//                if (i < mid) {
//                    rleft[i] = r[i];
//                    if (n1Ind[r[i]] < minIndLeft) minIndLeft = n1Ind[r[i]];
//                    if (maxIndLeft < n1Ind[r[i]]) maxIndLeft = n1Ind[r[i]];
//                }
//                else if (mid < i) {
//                    rright[i-mid] = r[i];
//                    if (n1Ind[r[i]] < minIndRight) minIndRight = n1Ind[r[i]];
//                    if (maxIndRight < n1Ind[r[i]]) maxIndRight = n1Ind[r[i]];
//                }
//            }
//            for (int i=minIndLeft; i<=maxIndLeft; i++)
//                lleft[i-1] = l[i];
//            for (int i=minIndRight; i<=maxIndRight; i++)
//                lright[i-minIndRight+1] = l[i];
//            if (minIndLeft <= minIndRight && minIndRight <= maxIndLeft
//                || minIndLeft <= maxIndRight && maxIndRight <= maxIndLeft)
//                return false;
//            return containOther(lleft, rleft) && containOther(lright, rright)
//                    && containOther(rleft, lleft) && containOther(rright, lright);
        }
        else {
            return containRight(r, l, mid);
        }
    }

    static boolean containRight(int[] l, int[] r, int mid) {
        int[] rright = new int[N-mid+1];
        int[] lright = new int[rright.length];
        int[] rleft = new int[N-rright.length+1];
        int[] lleft = new int[rleft.length];
        int minIndLeft = 100_000;
        int maxIndLeft = -1;
        int minIndRight = 100_000;
        int maxIndRight = -1;
        for (int i=1; i<=N; i++) {
            if (i < mid) {
                rleft[i] = r[i];
                if (n1Ind[r[i]] < minIndLeft) minIndLeft = n1Ind[r[i]];
                if (maxIndLeft < n1Ind[r[i]]) maxIndLeft = n1Ind[r[i]];
            }
            else if (mid < i) {
                rright[i-mid] = r[i];
                if (n1Ind[r[i]] < minIndRight) minIndRight = n1Ind[r[i]];
                if (maxIndRight < n1Ind[r[i]]) maxIndRight = n1Ind[r[i]];
            }
        }
        for (int i=minIndLeft; i<=maxIndLeft; i++)
            lleft[i-1] = l[i];
        for (int i=minIndRight; i<=maxIndRight; i++)
            lright[i-minIndRight+1] = l[i];
        if (minIndLeft <= minIndRight && minIndRight <= maxIndLeft
                || minIndLeft <= maxIndRight && maxIndRight <= maxIndLeft)
            return false;
        return containOther(lleft, rleft) && containOther(lright, rright)
                && containOther(rleft, lleft) && containOther(rright, lright);
    }
}
