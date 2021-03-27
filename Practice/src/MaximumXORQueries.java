import java.util.Arrays;

class MaximumXORQueries {
    final int inf = Integer.MAX_VALUE;

    class Trie {
        Trie left;
        Trie right;
        int L, R;

        public Trie() {
            L = inf;
            R = inf;
        }
    }


    void insert(Trie root, int val) {
        Trie curr = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (val >> i) & 1;
            if (bit == 0) {
                if (curr.left == null)
                    curr.left = new Trie();

                curr.L = Math.min(val, curr.L);
                curr = curr.left;
            } else {
                if (curr.right == null)
                    curr.right = new Trie();
                curr.R = Math.min(val, curr.R);
                curr = curr.right;
            }
        }
    }

    int maximumXORinLimit(Trie root, int num, int limit) {
        Trie curr = root;
        int ans = -1;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (bit == 0) {
                if (curr.right != null && curr.R <= limit) {
                    ans = curr.R;
                    curr = curr.right;
                } else {
                    if (curr.left != null && curr.L <= limit) {
                        ans = curr.L;
                        curr = curr.left;
                    } else
                        return -1;
                }

            } else {

                if (curr.left != null && curr.L <= limit) {
                    ans = curr.L;
                    curr = curr.left;
                } else {
                    if (curr.right != null && curr.R <= limit) {
                        ans = curr.R;
                        curr = curr.right;
                    } else
                        return -1;
                }
            }
        }

        return ans;
    }

    public int[] solve(int[] nums, int[][] queries) {
        Trie root = new Trie();
        for (int x : nums)
            insert(root, x);

        int[] ans = new int[queries.length];
        int index = 0;
        for (int[] q : queries) {
            int res = maximumXORinLimit(root, q[0], q[1]);
            ans[index++] = res;
        }

        return ans;
    }

    public static void main(String[] args) {
        MaximumXORQueries mxq = new MaximumXORQueries();
        int[] ans1 = mxq.solve(new int[]{2, 4, 8}, new int[][]{{3, 5}, {2, 0}});
        int[] ans2 = mxq.solve(new int[]{0}, new int[][]{{6, 5}});
        int[] ans3 = mxq.solve(new int[]{0, 1, 3, 3}, new int[][]{{9, 2}});
        int[] ans = mxq.solve(new int[]{1, 1}, new int[][]{{3, 4}});
        System.out.println(Arrays.toString(ans1) + "\n" + Arrays.toString(ans2) + "\n" + Arrays.toString(ans3));
        System.out.println(Arrays.toString(ans));
    }
}
