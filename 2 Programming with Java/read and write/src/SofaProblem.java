import java.util.*;

public class SofaProblem {

    static class State {
        int r1, c1, r2, c2, steps;

        State(int r1, int c1, int r2, int c2, int steps) {
            int[] a = {r1, c1};
            int[] b = {r2, c2};
            // normalize (smallest cell first)
            if (a[0] * 100 + a[1] > b[0] * 100 + b[1]) {
                int[] tmp = a; a = b; b = tmp;
            }
            this.r1 = a[0]; this.c1 = a[1];
            this.r2 = b[0]; this.c2 = b[1];
            this.steps = steps;
        }
    }

    static int M, N;
    static char[][] house;
    static int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    static List<int[]> startSofa = new ArrayList<>();
    static List<int[]> targetSofa = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        house = new char[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                house[i][j] = sc.next().charAt(0);
                if (house[i][j] == 's') startSofa.add(new int[]{i, j});
                else if (house[i][j] == 'S') targetSofa.add(new int[]{i, j});
            }
        }

        startSofa.sort(Comparator.comparingInt(a -> a[0] * N + a[1]));
        targetSofa.sort(Comparator.comparingInt(a -> a[0] * N + a[1]));

        int[] s1 = startSofa.get(0), s2 = startSofa.get(1);
        int[] t1 = targetSofa.get(0), t2 = targetSofa.get(1);

        boolean[][][][] visited = new boolean[M][N][M][N];
        Queue<State> queue = new LinkedList<>();
        State start = new State(s1[0], s1[1], s2[0], s2[1], 0);
        queue.add(start);
        visited[start.r1][start.c1][start.r2][start.c2] = true;

        while (!queue.isEmpty()) {
            State curr = queue.poll();

            // ✅ check if target reached
            if ((curr.r1 == t1[0] && curr.c1 == t1[1] &&
                 curr.r2 == t2[0] && curr.c2 == t2[1]) ||
                (curr.r1 == t2[0] && curr.c1 == t2[1] &&
                 curr.r2 == t1[0] && curr.c2 == t1[1])) {
                System.out.println(curr.steps);
                sc.close();
                return;
            }

            // ✅ move sofa in 4 directions
            for (int[] d : directions) {
                int nr1 = curr.r1 + d[0], nc1 = curr.c1 + d[1];
                int nr2 = curr.r2 + d[0], nc2 = curr.c2 + d[1];
                if (isValid(nr1, nc1) && isValid(nr2, nc2)) {
                    State next = new State(nr1, nc1, nr2, nc2, curr.steps + 1);
                    if (!visited[next.r1][next.c1][next.r2][next.c2]) {
                        visited[next.r1][next.c1][next.r2][next.c2] = true;
                        queue.add(next);
                    }
                }
            }

            // ✅ try rotations
            if (curr.r1 == curr.r2) { // horizontal
                int row = curr.r1, col = Math.min(curr.c1, curr.c2);
                if (canRotate(row, col)) {
                    // rotate around left cell
                    State next = new State(row, col, row + 1, col, curr.steps + 1);
                    if (!visited[next.r1][next.c1][next.r2][next.c2]) {
                        visited[next.r1][next.c1][next.r2][next.c2] = true;
                        queue.add(next);
                    }
                    // rotate around right cell
                    State next2 = new State(row, col + 1, row + 1, col + 1, curr.steps + 1);
                    if (!visited[next2.r1][next2.c1][next2.r2][next2.c2]) {
                        visited[next2.r1][next2.c1][next2.r2][next2.c2] = true;
                        queue.add(next2);
                    }
                }
            } else if (curr.c1 == curr.c2) { // vertical
                int row = Math.min(curr.r1, curr.r2), col = curr.c1;
                if (canRotate(row, col)) {
                    // rotate around top cell
                    State next = new State(row, col, row, col + 1, curr.steps + 1);
                    if (!visited[next.r1][next.c1][next.r2][next.c2]) {
                        visited[next.r1][next.c1][next.r2][next.c2] = true;
                        queue.add(next);
                    }
                    // rotate around bottom cell
                    State next2 = new State(row + 1, col, row + 1, col + 1, curr.steps + 1);
                    if (!visited[next2.r1][next2.c1][next2.r2][next2.c2]) {
                        visited[next2.r1][next2.c1][next2.r2][next2.c2] = true;
                        queue.add(next2);
                    }
                }
            }
        }

        // ✅ no path found
        System.out.println("Impossible");
        sc.close();
    }

    static boolean isValid(int r, int c) {
        return !(r < 0 || c < 0 || r >= M || c >= N || house[r][c] == 'H');
    }

    static boolean canRotate(int r, int c) {
        if (r + 1 >= M || c + 1 >= N) return false;
        for (int i = r; i <= r + 1; i++) {
            for (int j = c; j <= c + 1; j++) {
                if (house[i][j] == 'H') return false;
            }
        }
        return true;
    }
}
