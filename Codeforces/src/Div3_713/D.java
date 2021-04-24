package Div3_713;

//package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class D {
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
        // sort
        // last 2 elements can be sum or the random one
        // check against both

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = sc.nextInt();
            List<Integer> a = new ArrayList<>(n + 2);
            for (int i = 0; i < n + 2; i++)
                a.add(sc.nextInt());

            Collections.sort(a);

            // case 1 : if n is the ans
            int poss = a.get(n);
            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum += a.get(i);
            }

            if (sum == a.get(n)) {
                for (int i = 0; i < n; i++) {
                    sb.append(a.get(i) + " ");
                }
                sb.append("\n");
                continue;
            }

            // case 2: if n+1 is the ans
            poss = a.get(n + 1);
            sum += a.get(n);

            // check if omitting curr will yield the sum == a[n+1]

            int x = -1;
            for (int i = 0; i <= n; i++) {
                if (sum - a.get(i) == a.get(n + 1)) {
                    x = i;
                    break;
                }
            }

            if (x == -1)
                sb.append("-1\n");
            else {
                for (int i = 0; i <= n; i++) {
                    if (i == x)
                        continue;
                    sb.append(a.get(i) + " ");
                }

                sb.append("\n");
            }
        }

        System.out.print(sb);
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

