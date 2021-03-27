package ABC196;

//package com.company;

import java.io.*;
import java.util.StringTokenizer;

public class C {
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
        long n = sc.nextLong();
        pw.println(doubled(n));
    }

    static long doubled(long n) {
        long ans = 0;
        int digs = digs(n);

        for (int i = 2; i < digs; i += 2)
            ans += (int) (Math.pow(10, i / 2) - Math.pow(10, i / 2 - 1));

        // for digs, consider from 10^digs till the given number
        String N = n + "";
        int half = N.length() % 2 == 0 && N.length() >= 2 ? Integer.parseInt(N.substring(0, N.length() / 2)) : 0;
        long lastHalf = half - (int) Math.pow(10, digs / 2 - 1);
        if (lastHalf > 0)
            ans += lastHalf;
        if (half != 0 && Long.parseLong(half + "" + half) <= n)
            ans++;
        return ans;
    }

    static int digs(long n) {
        return (int) (Math.log10(n) + 1);
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
