package roundA;

import java.io.*;
import java.util.StringTokenizer;

public class Plates {
    static long TIME_START, TIME_END;
    private static final int M = (int) 1e9 + 7;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        //      Scanner sc = new Scanner(new FileInputStream("in.txt"));
        PrintWriter pw = new PrintWriter(new BufferedOutputStream(System.out));
//        PrintWriter pw = new PrintWriter(new FileOutputStream("nondec.out"));

        TIME_START = System.currentTimeMillis();
        solve(sc, pw);
        TIME_END = System.currentTimeMillis();
        pw.close();
        System.err.println("Time used: " + (TIME_END - TIME_START) + ".");

    }

    static void solve(Scanner sc, PrintWriter pw) throws IOException {
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int T = 1; T <= t; T++) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int p = sc.nextInt();

            int[][] a = new int[n][k];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < k; j++) {
                    a[i][j] = sc.nextInt();
                }
            }
            int ans = plates(n, k, p, a);
            sb.append("Case #" + T + ": " + ans + "\n");
        }
        System.out.print(sb);
    }

    static int plates(int n, int k, int p, int[][] a) {
        Integer[][][] dp = new Integer[n][p][k];
        return dfs(0, 0, 0, n, k, p, a, dp);
    }

    static int dfs(int curr, int moves, int top, int n, int k, int p, int[][] a, Integer[][][] dp) {
//        System.out.println("curr = " + curr + " ind = " + top + " moves = " + moves);
        if (curr >= n)
            return 0;

        if (moves == p)
            return 0;

        if (top >= k)
            return dfs(curr + 1, moves, 0, n, k, p, a, dp);

        if (dp[curr][moves][top] != null) {
//            System.out.println("dp");
            return dp[curr][moves][top];
        }

        int takeFromCurr = dfs(curr, moves + 1, top + 1, n, k, p, a, dp) + a[curr][top];
        int leaveCurr = dfs(curr + 1, moves, 0, n, k, p, a, dp);
//        System.out.println("curr = " + curr + " ind = " + top +
//                " moves = " + moves + " take = " + takeFromCurr + " leave = " + leaveCurr);
        return dp[curr][moves][top] = Math.max(takeFromCurr, leaveCurr);
    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner(FileReader s) throws FileNotFoundException {
            br = new BufferedReader(s);
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        public boolean ready() throws IOException {
            return br.ready();
        }
    }

}
