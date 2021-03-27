//package com.company;
package roundA;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Allocation {
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
            int b = sc.nextInt();

            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = sc.nextInt();
            int ans = allocation(a, b, n);
            sb.append("Case #" + T + ": " + ans + "\n");
        }
        System.out.print(sb);
    }

    static int allocation(int[] a, int b, int n) {
        Arrays.sort(a);
        int ans = 0, i = 0, sum = 0;
        while (i < n) {
            if (sum + a[i] > b)
                break;
            sum += a[i++];
            ans++;
        }
        return ans;
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
