import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// Foobar 3.1
public class HelpTheBunniesEscape {
    public static int solution(int[][] map) {
        int r = map.length, c = map[0].length;
        int ans = bfs(map, r, c); // without changing 1s
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 1) {
                    map[i][j] = 0;
                    ans = Math.min(ans, bfs(map, r, c));
                    map[i][j] = 1;
                }
            }
        }

        return ans;
    }

    private static int bfs(int[][] map, int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 1}); // x, y, pathLen
        int[] dx = {0, 0, 1, -1}, dy = {-1, 1, 0, 0};
        Set<Integer> vis = new HashSet<>();
        vis.add(0);
        while (!q.isEmpty()) {
//            System.out.println("Q--> " + q + "\n");
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                int[] poll = q.poll();
//                vis.add(transform(poll[0], poll[1], c));
                // cardinal dir
                for (int x = 0; x < 4; x++) {
                    int newX = poll[0] + dx[x];
                    int newY = poll[1] + dy[x];
                    if (isValid(newX, newY, r, c) && map[newX][newY] != 1 &&
                            !vis.contains(transform(newX, newY, c))) {
                        vis.add(transform(newX, newY, c));
                        if (newX == r - 1 && newY == c - 1)
                            return poll[2] + 1;
                        q.add(new int[]{newX, newY, poll[2] + 1});
                    }
                }
            }
        }


        return Integer.MAX_VALUE;
    }

    static boolean isValid(int x, int y, int r, int c) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }

    static int transform(int x, int y, int c) {
        return x * c + y;
    }

    public static void main(String[] args) {
        HelpTheBunniesEscape hb = new HelpTheBunniesEscape();
        System.out.println(hb.solution(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}}));

//        System.out.println(hb.solution(new int[][]{{0, 0}, {0, 0}}));

        System.out.println(hb.solution(new int[][]{{0, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 0}}));
        System.out.println(hb.solution(new int[][]{{0, 1, 1, 0}, {0, 0, 0, 1}, {1, 1, 0, 0}, {1, 1, 1, 0}}));
    }
}
