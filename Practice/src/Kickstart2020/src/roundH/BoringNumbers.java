package roundH;


//package com.company;

import java.io.*;
import java.util.StringTokenizer;

public class BoringNumbers {
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
            long L = sc.nextLong(), R = sc.nextLong();
            long r = boringNumbers(R + "");
            long l = boringNumbers((L - 1) + "");
//            System.out.println("r= " + r + " l= " + l);
            long ans = r - l;
            sb.append("Case #" + T + ": " + ans + "\n");
        }
        out.print(sb);
    }

    static long boringNumbers(String r) {
        long fixedPart = 0;
        int digs = r.length();
        for (int i = 1; i < digs; i++) {
            fixedPart += (long) Math.pow(5, i);
        }

        long recPart = recPart(r, 0);
        return fixedPart + recPart;
    }

    static long recPart(String r, int ind) {
        if (ind == r.length())
            return 0;
        int curr = r.charAt(ind) - '0';
        boolean evenPlace = (ind + 1) % 2 == 0;
        int lessThanCurr = lessThan(curr, evenPlace);
        int digs = r.length() - ind;
        long ans = lessThanCurr * (long) Math.pow(5, digs - 1);

        if (curr % 2 == (ind + 1) % 2)
            ans += recPart(r, ind + 1);

        if (ind == r.length() - 1 && (ind + 1) % 2 == curr % 2 && curr <= r.charAt(ind) - '0')
            ans++;

        return ans;
    }

    static int lessThan(int curr, boolean evenPlace) {
        int cnt = 0;
        if (evenPlace) {
            for (int i = 0; i < 9; i += 2) {
                if (i < curr)
                    cnt++;
                else
                    break;
            }
        } else {
            for (int i = 1; i <= 9; i += 2) {
                if (i < curr)
                    cnt++;

                else
                    break;
            }
        }


        return cnt;
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

