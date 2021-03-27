package ABC196;

//package com.company;

import java.io.*;
import java.util.StringTokenizer;

public class D {
    static long TIME_START, TIME_END;
    private static final int M = (int) 1e9 + 7;
    static int ans = 0;

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
        int h = sc.nextInt(), w = sc.nextInt(), a = sc.nextInt(), b = sc.nextInt();
        int[][] g = new int[h][w];
        ways(0, 0, g, h, w, a, b); // a - 2x1, b = 1x1
        System.out.println(ans);
    }

    /*
        color 1x1 = 1; 2x1 = 2; 1x2 = 3
    * */
    static void ways(int r, int c, int[][] g, int h, int w, int a, int b) {
//        print(g);

        if (r == h)
            return;

        if (a == 0 && b == 0) {
            ans++;
//            print(g);
            return;
        }

        if (c >= w) {
            c = 0;
            ways(r + 1, c, g, h, w, a, b);
            return;
        }

        if (g[r][c] != 0) {
            ways(r, c + 1, g, h, w, a, b);
            return;
        }

        if (b > 0 && g[r][c] == 0) {
            g[r][c] = 1;
            ways(r, c + 1, g, h, w, a, b - 1);
            g[r][c] = 0;
        }

        if (a > 0 && g[r][c] == 0 && r + 1 < h && g[r + 1][c] == 0) {
            g[r][c] = 2;
            g[r + 1][c] = 2;
            ways(r, c + 1, g, h, w, a - 1, b);
            g[r][c] = 0;
            g[r + 1][c] = 0;
        }

        if (a > 0 && g[r][c] == 0 && c + 1 < w && g[r][c + 1] == 0) {
            g[r][c] = 3;
            g[r][c + 1] = 3;
            ways(r, c + 2, g, h, w, a - 1, b);
            g[r][c] = 0;
            g[r][c + 1] = 0;
        }
    }

    static void print(int[][] g) {

        System.out.println("printing config ================");
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[0].length; j++)
                System.out.print(g[i][j] + " ");
            System.out.println();
        }

        System.out.println("CURR WAYS = " + ans);
        System.out.println("================================");
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
