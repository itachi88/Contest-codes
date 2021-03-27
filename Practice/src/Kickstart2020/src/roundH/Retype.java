package roundH;

//package com.company;

import java.io.*;
import java.util.StringTokenizer;

public class Retype {
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
            int lvls = sc.nextInt(), curr = sc.nextInt(), swd = sc.nextInt();
            int ans = retype(lvls, curr, swd);
            sb.append("Case #" + T + ": " + ans + "\n");
        }
        pw.print(sb);
    }

    static int retype(int lvls, int curr, int swd) {
        int min = Integer.MAX_VALUE;
        int currTime = curr - 1;
        // option1 - backtrack and continue the game
        int opt1 = curr - swd + lvls - swd + 1;

        // option2 restart the game

        int opt2 = 1 + lvls;

//        System.out.println("opt1 " + opt1 + " opt2 " + opt2);
        return currTime + Math.min(opt1, opt2);
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

