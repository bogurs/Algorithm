package bogurs.algorithm.offline.bruteforce.bitmask;

import java.util.Scanner;

class Result {
    boolean moved, hole;
    int x, y;
    Result(boolean moved, boolean hole, int x, int y) {
        this.moved = moved;
        this.hole = hole;
        this.x = x;
        this.y = y;
    }
}

/**
 * 구슬 탈출2
 * 
 * 빨간 구슬을 빠져 나가게 하는 최소 횟수를 구하는 문제. 파란 구슬이 빠져나가면 안됨.
 * 10번 만에 빠져나가지 못하면 -1을 출력.
 * 
 * 기울일 수 있는 경우 상/하/좌/우 네 방향. 총 10번 기울였을 때 결과가 나오므로
 * 4^10 = 1048576 경우의 수가 있다.
 * 
 * 
 * @author thsong
 *
 */
public class Brute_No5_BallExit {

	static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static final int LIMIT = 10;
    static int[] gen(int k) {
    	//00000000000000000000 ~ 11111111111111111111 까지 이진수를
    	//0000000000 ~ 3333333333 까지 4진수 형식으로 이동 경로 배열 생성
        int[] dir = new int[LIMIT];
        for (int i=0; i<LIMIT; i++) {
            dir[i] = (k&3); // 11을 &연산으로 거름
            k >>= 2; // 위에서 11을 검사했으므로 주어진 숫자를 2칸 줄임
        }
        return dir;
    }
    static Result simulate(char[][] a, int k, int x, int y) {
        int n = a.length;
        int m = a[0].length;
        if (a[x][y] == '.') return new Result(false, false, x, y);
        boolean moved = false;
        while (true) {
            int nx = x+dx[k];
            int ny = y+dy[k];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                return new Result(moved, false, x, y);
            }
            char ch = a[nx][ny];
            if (ch == '#') {
                return new Result(moved, false, x, y);
            } else if (ch == 'R' || ch == 'B') {
                return new Result(moved, false, x, y);
            } else if (ch == '.') {
                char temp = a[nx][ny];
                a[nx][ny] = a[x][y];
                a[x][y] = temp;
                x = nx;
                y = ny;
                moved = true;
            } else if (ch == 'O') {
                a[x][y] = '.';
                moved = true;
                return new Result(moved, true, x, y);
            }
        }
    }
    static int check(char[][] a, int[] dir) {
        int n = a.length;
        int m = a[0].length;
        int hx = 0, hy = 0;
        int rx = 0, ry = 0;
        int bx = 0, by = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (a[i][j] == 'O') {
                    hx = i; hy = j;
                } else if (a[i][j] == 'R') {
                    rx = i; ry = j;
                } else if (a[i][j] == 'B') {
                    bx = i; by = j;
                }
            }
        }
        int cnt = 0;
        for (int k : dir) {
            cnt += 1; // 기울인 횟수를 누적함
            boolean hole1 = false, hole2 = false;
            while (true) {
                Result p1 = simulate(a, k, rx, ry);
                rx = p1.x; ry = p1.y;
                Result p2 = simulate(a, k, bx, by);
                bx = p2.x; by = p2.y;
                if (p1.moved == false && p2.moved == false) {
                    break;
                }
                if (p1.hole) hole1 = true;
                if (p2.hole) hole2 = true;
            }
            if (hole2) return -1; // 파란 공이 구멍에 빠져나간경우는 실패한 경우이므로 -1을 반환한다.(실패)
            if (hole1) return cnt; // 빨간 공이 구멍에 빠져나간경우는 성공한 경우이므로 cnt를 반환한다.
        }        
        return -1;
    }
    static boolean valid(int[] dir) { 
    	//상에 이어 하로 움직이는 경우 제외
    	//하에 이어 상으로 움직이는 경우 제외
    	//좌에 이어 우로 움직이는 경우 제외
    	//우에 이어 좌로 움직이는 경우 제외
    	//같은 방향으로 연속해서 움직이는 경우 제외
        int l = dir.length;
        for (int i=0; i+1<l; i++) {
            if (dir[i] == 0 && dir[i+1] == 1) return false;
            if (dir[i] == 1 && dir[i+1] == 0) return false;
            if (dir[i] == 2 && dir[i+1] == 3) return false;
            if (dir[i] == 3 && dir[i+1] == 2) return false;
            if (dir[i] == dir[i+1]) return false;
        }
        return true;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        String[] map = new String[n];
        char[][] a = new char[n][m];
        for (int i=0; i<n; i++) {
            map[i] = sc.next();
        }
        int ans = -1;
        for (int k=0; k<(1<<(LIMIT*2)); k++) {
            int[] dir = gen(k);
            if (!valid(dir)) continue;
            for (int i=0; i<n; i++) {
                a[i] = map[i].toCharArray();
            }
            int cur = check(a, dir);
            if (cur == -1) continue;
            if (ans == -1 || ans > cur) ans = cur;
        }
        System.out.println(ans);
        
        sc.close();
    }

}
