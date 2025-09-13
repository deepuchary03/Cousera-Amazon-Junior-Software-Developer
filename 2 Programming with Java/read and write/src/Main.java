import java.util.*;

public class Main {
    static int S;
    static char[][] matrix;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        S = sc.nextInt();
        sc.nextLine();
        matrix = new char[S][S];
        for (int i = 0; i < S; i++) {
            matrix[i] = sc.nextLine().toCharArray();
        }

        int overlaps = countOverlaps();
        if (overlaps == -1) {
            System.out.print("Impossible"); // no extra spaces or newlines
        } else {
            System.out.print(overlaps);
        }
    }

    static int countOverlaps() {
        int count = 0;

        for (int i = 0; i < S; i++) {
            for (int j = 0; j < S; j++) {
                if (matrix[i][j] == '1') {
                    if (!checkBand(i, j, '1', '2')) return -1;
                }
                if (matrix[i][j] == '2') {
                    if (!checkBand(i, j, '2', '1')) return -1;
                }
            }
        }

        // Count overlaps
        for (int i = 0; i < S; i++) {
            for (int j = 0; j < S; j++) {
                if (matrix[i][j] == 'X') count++;
            }
        }

        return count;
    }

    // DFS to mark valid cross positions as 'X'
    static boolean checkBand(int i, int j, char top, char bottom) {
        int[] dr = {0, 1};
        int[] dc = {1, 0};

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{i, j});

        while (!stack.isEmpty()) {
            int[] pos = stack.pop();
            int r = pos[0], c = pos[1];

            if (matrix[r][c] == top) {
                matrix[r][c] = '.'; // mark visited
                for (int k = 0; k < 2; k++) {
                    int nr = r + dr[k], nc = c + dc[k];
                    if (nr < S && nc < S) {
                        if (matrix[nr][nc] == top) stack.push(new int[]{nr, nc});
                        else if (matrix[nr][nc] == bottom) matrix[nr][nc] = 'X'; // overlap
                        else if (matrix[nr][nc] != '.') continue;
                    }
                }
            }
        }

        return true;
    }
}
