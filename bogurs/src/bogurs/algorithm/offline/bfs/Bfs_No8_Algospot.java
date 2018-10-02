package bogurs.algorithm.offline.bfs;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

class Pair2 {
	int x;
	int y;
	public Pair2(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
/**
 * 알고스팟
 * 
 * (1,1) 에서 (n,m) 끝에서 끝으로 이동하려면 벽을 최소 몇개를 부숴야 하는지 구하는 문제
 * 
 * 숨바꼭질3 문제와 마찬가지로 벽이 없는 곳을 이동할 때 가중치 0,
 * 벽이 있는 곳을 이동할 때는 가중치가 1로
 * 덱으로 문제를 해결해야 하는 bfs문제이다.
 * 
 * 
 * @author thsong
 *
 */
public class Bfs_No8_Algospot {
	static final int[] dx = {0, 1, 0, -1}; // 우,하,좌,상
	static final int[] dy = {1, 0, -1, 0};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt(); // (1 ≤ N, M ≤ 100)
		int[][] a = new int[m][n];
		for (int i = 0; i < m; i++) {
			String temp = sc.next();
			for (int j = 0; j < n; j++) {
				a[i][j] = Integer.parseInt(temp.substring(j, j+1));
			}
		}
		
		Deque<Pair2> dq = new LinkedList<>();
		boolean[][] c = new boolean[m][n]; // 이동한 곳은 재방문하지 않게 하기 위해 check함
		int[][] d = new int[m][n]; // 이동한 후 그곳에 대해 이동거리를 구함(최소이동거리)
		c[0][0] = true;
		d[0][0] = 0;
		dq.addLast(new Pair2(0, 0));
		while (!dq.isEmpty()) {
			Pair2 p = dq.removeFirst();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (nx >= 0 && ny >= 0 && nx < m && ny < n) {
					if (!c[nx][ny]) {
						if (a[nx][ny] == 0) { // 벽이 아니므로 바로 이동이 가능
							c[nx][ny] = true;
							d[nx][ny] = d[p.x][p.y];
							dq.addFirst(new Pair2(nx, ny));
						} else { // 벽이므로 벽을 부수고 이동해야 한다.
							c[nx][ny] = true;
							d[nx][ny] = d[p.x][p.y] + 1;
							dq.addLast(new Pair2(nx, ny));
						}
					}
				}
			}
		}
		System.out.println(d[m-1][n-1]);
		
		sc.close();
	}

}
