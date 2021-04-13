package RoundA;

//package com.company;

import java.io.*;
import java.util.StringTokenizer;

public class B {
    static long TIME_START, TIME_END;
    private static final int M = (int) 1e9 + 7;
    private static final int inf = Integer.MAX_VALUE;

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

    static void solve(Scanner sc, PrintWriter out) throws IOException {
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int T = 1; T <= t; T++) {
            int r = sc.nextInt(), c = sc.nextInt();
            int[][] a = new int[r][c];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    a[i][j] = sc.nextInt();
                }
            }
            int ans = lShapedPlots(a, r, c);
            sb.append("Case #" + T + ": " + ans + "\n");
        }
        out.print(sb);
    }

    static int lShapedPlots(int[][] a, int r, int c) {
        int[][] f = new int[r][c], b = new int[r][c], d = new int[r][c], u = new int[r][c];

        // F

        for (int i = 0; i < r; i++) {
            for (int j = c - 1; j >= 0; j--) {
                if (j == c - 1)
                    f[i][j] = a[i][j];
                else
                    f[i][j] = a[i][j] == 1 ? 1 + f[i][j + 1] : a[i][j];
            }
        }
        // B

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (j == 0)
                    b[i][j] = a[i][j];
                else
                    b[i][j] = a[i][j] == 1 ? 1 + b[i][j - 1] : a[i][j];
            }
        }


        // D

        for (int j = 0; j < c; j++) {
            for (int i = r - 1; i >= 0; i--) {
                if (i == r - 1)
                    d[i][j] = a[i][j];
                else
                    d[i][j] = a[i][j] == 1 ? 1 + d[i + 1][j] : a[i][j];
            }
        }


        // U

        for (int j = 0; j < c; j++) {
            for (int i = 0; i < r; i++) {
                if (i == 0)
                    u[i][j] = a[i][j];
                else
                    u[i][j] = a[i][j] == 1 ? 1 + u[i - 1][j] : a[i][j];
            }
        }


        int cnt = 0;
        // considering every point as an endpoint

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                // bd, bu, fd, fu
                // considering each side to be smaller and greater
                cnt += count(i, j, b, d) + count(i, j, d, b) + count(i, j, b, u) + count(i, j, u, b) + count(i, j, f, d)
                        + count(i, j, d, f) + count(i, j, f, u) + count(i, j, u, f);

            }
        }
        return cnt;
    }


    static int count(int r, int c, int[][] a, int[][] b) {
        int big = a[r][c], small = b[r][c];
        if (big <= 1 || small <= 1)
            return 0;

        if (big >= small * 2)
            return small - 1;

        return big / 2 - 1;
    }

    static void print(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++)
                System.out.print(a[i][j] + " ");
            System.out.println();
        }
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
