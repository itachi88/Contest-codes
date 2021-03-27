package roundA;

//package com.company;

import java.io.*;
import java.util.StringTokenizer;

public class Workout {
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
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = sc.nextInt();
            int ans = workout(a, n, k);
            sb.append("Case #" + T + ": " + ans + "\n");
        }
        System.out.print(sb);
    }

    static int workout(int[] a, int n, int k) {
        int lo = 0, hi = 0; // hi answer
        for (int i = 1; i < n; i++)
            hi = Math.max(hi, a[i] - a[i - 1]);

        while (lo < hi) {
            if (lo == hi - 1)
                break;
            int mid = lo + (hi - lo) / 2;
            if (possible(mid, a, n, k))
                hi = mid;

            else
                lo = mid;

        }
        return hi;
    }


    static boolean possible(int mid, int[] a, int n, int k) {
        int rem = k;
        for (int i = 1; i < n; i++) {
            int diff = a[i] - a[i - 1];
            if (diff <= mid)
                continue;
            rem -= ((diff + mid - 1) / mid) - 1;
            if (rem < 0)
                return false;

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

