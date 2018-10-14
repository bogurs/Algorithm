package bogurs.algorithm.offline.bfs;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

class Pair5 {
	int x;
	int y;
	public Pair5(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

/**
 * 탈옥
 * 
 * 두 죄수를 탈옥시키기 위해서 열어야 하는 문의 최소개수를 구하는 프로그램
 * 
 * ****#****
   *..#.#..*
   ****.****
   *$#.#.#$*
   *********
   
   -1 -1 -1 -1  1 -1 -1 -1 -1
   -1  0  0  1  0  1  0  0 -1
   -1 -1 -1 -1  0 -1 -1 -1 -1
   -1  0  1  0  1  0  1  0 -1
   -1 -1 -1 -1 -1 -1 -1 -1 -1
   
   <죄수1-bfs>
   -1 -1 -1 -1  3 -1 -1 -1 -1
   -1  3  3  3  2  3  3  3 -1
   -1 -1 -1 -1  2 -1 -1 -1 -1
   -1  0  1  1  2  2  3  3 -1
   -1 -1 -1 -1 -1 -1 -1 -1 -1
   
   <죄수2-bfs>
   -1 -1 -1 -1  3 -1 -1 -1 -1
   -1  3  3  3  2  3  3  3 -1
   -1 -1 -1 -1  2 -1 -1 -1 -1
   -1  3  3  2  2  1  1  0 -1
   -1 -1 -1 -1 -1 -1 -1 -1 -1
   
   <죄수1,2 같이출발-bfs>
   -1 -1 -1 -1  3 -1 -1 -1 -1
   -1  3  3  3  2  3  3  3 -1
   -1 -1 -1 -1  2 -1 -1 -1 -1
   -1  0  1  1  2  1  1  0 -1
   -1 -1 -1 -1 -1 -1 -1 -1 -1
   
   (0,0)에서 시작해서 bfs
   죄수1에서 시작해서 bfs
   죄수2에서 시작해서 bfs
   3개의 결과를 각각 합했을 때 (문인경우는 -2를 수행, 외부/죄수1/죄수2 가 중복해서 3번을 열게 되므로)
   최소값을 찾으면 된다.
   
 * @author thsong
 *
 */
public class Bfs_No12_PrisonBreak {
	private static final int[] dx = {0, 1, 0, -1};
	private static final int[] dy = {1, 0, -1, 0};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int c = sc.nextInt(); // 100이하
		
		for (int i = 0; i < c; i++) {
			int h = sc.nextInt(); // (2 ≤ h ≤ 100)
			int w = sc.nextInt(); // (2 ≤ w ≤ 100)
			int[][] goal = new int[h+2][w+2];
			int[][] prisoner1 = new int[h+2][w+2];
			int[][] prisoner2 = new int[h+2][w+2];
			boolean[][] ch = new boolean[h+2][w+2];
			boolean[][] isDoor = new boolean[h+2][w+2];
			int px1 = 0, py1 = 0, px2 = 0, py2 = 0;
			Deque<Pair5> q = new LinkedList<>();
			int prisonCnt = 0;
			for (int j = 1; j < h+1; j++) {
				String line = sc.next();
				
				for (int k = 1; k < w+1; k++) {
					if (j == h+1 || k == w+1) continue;
					char lineChar = line.charAt(k-1);
					if (lineChar == '#') {
						prisoner1[j][k] = 1;
						prisoner2[j][k] = 1;
						goal[j][k] = 1;
						isDoor[j][k] = true;
					} else if (lineChar == '*') {
						prisoner1[j][k] = -1;
						prisoner2[j][k] = -1;
						goal[j][k] = -1;
						ch[j][k] = true;
					} else if (lineChar == '$') {
						if (prisonCnt == 0) {
							px1 = j;
							py1 = k;
						} else if (prisonCnt == 1) {
							px2 = j;
							py2 = k;
						}
						prisonCnt++;
					}
				}
			}
			
			q.add(new Pair5(0, 0));
			ch[0][0] = true;
			while (!q.isEmpty()) { // 목적지로부터 시작했을 때 bfs
				Pair5 p = q.remove();
				for (int k = 0; k < 4; k++) {
					int nx = p.x + dx[k];
					int ny = p.y + dy[k];
					if (nx >= 0 && ny >= 0 && nx <= h+1 && ny <= w+1) {
						if (!ch[nx][ny]) {
							ch[nx][ny] = true;
							if (goal[nx][ny] == 0) {
								q.addFirst(new Pair5(nx, ny));
							} else if (goal[nx][ny] == 1) {
								q.addLast(new Pair5(nx, ny));
							}
							goal[nx][ny] += goal[p.x][p.y];
						}
					}
				}
			}
			
			q.add(new Pair5(px1, py1));
			ch = new boolean[h+2][w+2];
			ch[px1][py1] = true;
			while (!q.isEmpty()) { // 죄수1로부터 시작했을 때 bfs
				Pair5 p = q.remove();
				for (int k = 0; k < 4; k++) {
					int nx = p.x + dx[k];
					int ny = p.y + dy[k];
					if (nx >= 0 && ny >= 0 && nx <= h+1 && ny <= w+1) {
						if (!ch[nx][ny] && prisoner1[nx][ny] != -1) {
							ch[nx][ny] = true;
							if (prisoner1[nx][ny] == 0) {
								q.addFirst(new Pair5(nx, ny));
							} else if (prisoner1[nx][ny] == 1) {
								q.addLast(new Pair5(nx, ny));
							}
							prisoner1[nx][ny] += prisoner1[p.x][p.y];
						}
					}
				}
			}
			
			q.add(new Pair5(px2, py2));
			ch = new boolean[h+2][w+2];
			ch[px2][py2] = true;
			while (!q.isEmpty()) { // 죄수1로부터 시작했을 때 bfs
				Pair5 p = q.remove();
				for (int k = 0; k < 4; k++) {
					int nx = p.x + dx[k];
					int ny = p.y + dy[k];
					if (nx >= 0 && ny >= 0 && nx <= h+1 && ny <= w+1) {
						if (!ch[nx][ny] && prisoner2[nx][ny] != -1) {
							ch[nx][ny] = true;
							if (prisoner2[nx][ny] == 0) {
								q.addFirst(new Pair5(nx, ny));
							} else if (prisoner2[nx][ny] == 1) {
								q.addLast(new Pair5(nx, ny));
							}
							prisoner2[nx][ny] += prisoner2[p.x][p.y];
						}
					}
				}
			}
			
			int res = Integer.MAX_VALUE;
			for (int j = 0; j < h+2; j++) {
				for (int k = 0; k < w+2; k++) {
					int now_res = goal[j][k] + prisoner1[j][k] + prisoner2[j][k];
					if (isDoor[j][k]) now_res -= 2;
					if (now_res < res && now_res >= 0) res = now_res;
				}
			}
			System.out.println(res);
		}
		
		sc.close();
	}

}
