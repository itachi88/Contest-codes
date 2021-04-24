package Div3_713;

//package com.company;

import java.io.*;
import java.util.StringTokenizer;

public class E {
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
            int n = sc.nextInt(), l = sc.nextInt(), r = sc.nextInt(), s = sc.nextInt();
//            if (T == 62)
//                System.out.println("-->" + n + " " + l + " " + r + " " + s);
            int[] a = new int[n + 1];
            boolean[] vis = new boolean[n + 1];
            int k = r - l + 1;
            int min = (k * (k + 1)) / 2;
            int max = (k * (2 * n - k + 1)) / 2;

            if (s < min || s > max) {
                sb.append(-1 + "\n");
                continue;
            }

            int st = 1;
            int sum = min;
            for (int i = l; i <= r; i++) {
                a[i] = st++;
                vis[a[i]] = true;
            }

            boolean found = false;
            for (int i = r; i >= l; i--) { // pos
                if (sum == s) {
                    found = true;
                    break;
                }
                int prev = a[i];
                for (int j = a[i] + 1; j <= n; j++) {
                    if (vis[j])
                        continue;

                    sum += 1;
                    vis[j] = true;
                    vis[prev] = false;
                    prev = j;
                    a[i] = j;
                    if (sum == s) {
                        found = true;
//                        vis[a[i]] = false;
//                        vis[j] = true;

                        break;
                    }
                }

                if (found)
                    break;
            }

            // forming the array back
            if (!found)
                sb.append(-1 + "\n");
            else {
                for (int i = 1; i <= n; i++) {
                    if (a[i] != 0)
                        continue;

                    for (int j = 1; j <= n; j++) {
                        if (vis[j])
                            continue;
                        a[i] = j;
                        vis[j] = true;
                        break;
                    }
                }

                for (int i = 1; i <= n; i++)
                    sb.append(a[i] + " ");
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

