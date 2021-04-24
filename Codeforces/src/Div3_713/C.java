package Div3_713;

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
        int t = sc.nextInt();
        outer:
        while (t-- > 0) {
            int zero = sc.nextInt(), one = sc.nextInt();
            char[] a = sc.next().toCharArray();
            for (char c : a) {
                if (c == '0')
                    zero--;
                else if (c == '1')
                    one--;
            }
            int n = a.length;
            int i = 0, j = n - 1;

            // trying to fill out the ?x or x? cases

            while (i < j) {
//                System.out.println("1");
                if (a[i] != '?' && a[j] != '?' && a[i] != a[j]) {
                    pw.println("-1");
                    continue outer;
                } else if (a[i] == '?' && a[j] != '?') {
                    if (a[j] == '0') {
                        if (zero <= 0) {
                            pw.println("-1");
                            continue outer;
                        }
                        a[i] = '0';
                        zero--;
                    } else {
                        if (one <= 0) {
                            pw.println("-1");
                            continue outer;
                        }

                        a[i] = '1';
                        one--;
//                        i++;
//                        j--;
                    }
                } else if (a[i] != '?' && a[j] == '?') {
                    if (a[i] == '0') {
                        if (zero <= 0) {
                            pw.println("-1");
                            continue outer;
                        }
                        a[j] = '0';
                        zero--;
                    } else {
                        if (one <= 0) {
                            pw.println("-1");
                            continue outer;
                        }

                        a[j] = '1';
                        one--;

                    }
                }

                i++;
                j--;
            }

            // filling out the ?? case but inequal case would already have been handled in the prev loop

            i = 0;
            j = n - 1;

            while (i < j) {
//                System.out.println("2");
                if (a[i] == '?' && a[j] == '?') {
                    if (zero >= 2) {
                        a[i] = '0';
                        a[j] = '0';
                        zero -= 2;
                    } else if (one >= 2) {
                        a[i] = '1';
                        a[j] = '1';
                        one -= 2;
                    }
                    // not sufficient resources
                    else {
                        pw.println("-1");
                        continue outer;
                    }
                }
                i++;
                j--;
            }

            // fill whatever is available in the middle only if i==j
            int mid = n / 2;
            if (a[mid] == '?') {
                if (zero > 0) {
                    a[mid] = '0';
                    zero--;
                } else {
                    a[mid] = '1';
                    one--;
                }

            }

            if (zero == 0 && one == 0)
                pw.println(String.valueOf(a));

            else
                pw.println("-1");

        }

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

