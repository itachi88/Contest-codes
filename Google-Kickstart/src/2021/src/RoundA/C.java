package RoundA;

//package com.company;

import java.io.*;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class C {
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
            int[][] g = new int[r][c];

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    g[i][j] = sc.nextInt();
                }
            }

            long ans = rabbitHouse(g, r, c);
            sb.append("Case #" + T + ": " + ans + "\n");
        }
        out.print(sb);
    }

    static long rabbitHouse(int[][] g, int r, int c) {
//        int max = findMax(g, r, c);
//        Queue<int[]> q = new LinkedList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        HashSet<Integer> vis = new HashSet<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
//                if (g[i][j] == max) {
//                    pq.add(new int[]{i, j, max});
//                    vis.add(transform(i, j, c));
//                }

                pq.add(new int[]{i, j, g[i][j]});
            }
        }

        long ans = 0;
        int[] dx = {0, 0, -1, 1}, dy = {1, -1, 0, 0};
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            // adjust the adjacent wrt to current's height
            int x = poll[0], y = poll[1], ht = poll[2];
            for (int d = 0; d < 4; d++) {
                int newX = x + dx[d], newY = y + dy[d];
                if (isValid(newX, newY, r, c) && !vis.contains(transform(newX, newY, c))) {
                    vis.add(transform(newX, newY, c));

                    if (ht - g[newX][newY] > 1) {
                        ans += ht - g[newX][newY] - 1;
//                    pq.add(new int[]{newX, newY, g[x][y] - g[newX][newY] - 1}); // add new height
                        pq.add(new int[]{newX, newY, ht - 1}); // add new height
                    }
//                    else
//                        pq.add(new int[]{newX, newY, g[newX][newY]});
                }
            }
        }

        return ans;
    }

    static boolean isValid(int x, int y, int r, int c) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }

    static int transform(int x, int y, int c) {
        return x * c + y;
    }

    static int findMax(int[][] g, int r, int c) {
        int max = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (max <= g[i][j])
                    max = g[i][j];
            }
        }
        return max;

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
