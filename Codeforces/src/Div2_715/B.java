package Div2_715;

//package com.company;

import java.io.*;
import java.util.StringTokenizer;

public class B {
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
        while (t-- > 0) {
            int n = sc.nextInt();
            String s = sc.next();
            if (!init(s, n)) {
                sb.append("NO\n");
                continue;
            }

            if (check(s) && check(new StringBuilder(s).reverse().toString()))
                sb.append("YES\n");

            else
                sb.append("NO\n");
        }
        pw.print(sb);
    }

    static boolean init(String s, int n) {
        char[] a = s.toCharArray();
        int t = 0, m = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == 'T')
                t++;
            else
                m++;
        }

        return t == m * 2;
    }


    static boolean check(String s) {
        int n = s.length();
        char[] a = s.toCharArray();
        int[][] cnt = new int[n][2];
        if (a[0] == 'T')
            cnt[0][0]++;
        else
            return false;

        for (int i = 1; i < n; i++) {
            if (a[i] == 'T') {
                cnt[i][0] = 1 + cnt[i - 1][0];
                cnt[i][1] = cnt[i - 1][1];
            } else {
                cnt[i][1] = 1 + cnt[i - 1][1];
                cnt[i][0] = cnt[i - 1][0];
                if (cnt[i][0] < cnt[i][1])
                    return false;
            }
        }

        return true;
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
