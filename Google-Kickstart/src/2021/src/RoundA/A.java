package RoundA;

//package com.company;

import java.io.*;
import java.util.StringTokenizer;

public class A {
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
            int n = sc.nextInt();
            int k = sc.nextInt();
            String s = sc.next();
            int ans = kGoodnessString(s, n, k);
            sb.append("Case #" + T + ": " + ans + "\n");
        }
        out.print(sb);
    }

    static int kGoodnessString(String s, int n, int k) {
        int currScore = 0;
        int i = 0, j = n - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                currScore++;
            i++;
            j--;
        }

        return currScore == k ? 0 : Math.abs(k - currScore);
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
