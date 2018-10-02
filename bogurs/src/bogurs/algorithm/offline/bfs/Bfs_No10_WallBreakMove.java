package bogurs.algorithm.offline.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair3 {
	int x;
	int y;
	int z;
	public Pair3(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
/**
 * 벽 부수고 이동하기
 * 
 * (0,0) -> (n-1, m-1) 까지 이동하는데 최소 거리를 구하기
 * 벽을 최대 1번 부술수 있고 부숴서 이동하는 것이 더 짧은 경우 그 경로의 거리를 출력한다.
 * 
 * 벽을 부쉈는지에 대한 여부를 위해 (0,0)이 아닌 (0,0,0)에서 시작하도록 한다.
 * d(0,0,0) = 1로 정해놓고 진행
 * d(x,y,0) = (x,y) 좌표에 벽을 부수지 않고 이동할 수 있는 거리
 * d(x,y,1) = (x,y) 좌표에 벽을 1번 부수고 이동할 수 있는 거리
 *  
 * @author thsong
 *
 */
public class Bfs_No10_WallBreakMove {
	static final int[] dx = {0, 1, 0, -1}; //우,하,좌,상
	static final int[] dy = {1, 0, -1, 0};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt(); //N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)
		int[][] a = new int[n][m];
		for (int i = 0; i < n; i++) {
			String temp = sc.next();
			for (int j = 0; j < m; j++) {
				a[i][j] = temp.charAt(j) - '0';
			}
		}
		
		int[][][] d = new int[n][m][2];
		Queue<Pair3> q = new LinkedList<>();
		q.add(new Pair3(0, 0, 0));
		d[0][0][0] = 1;
		while (!q.isEmpty()) {
			Pair3 p = q.remove();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				int z = p.z;
				if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
					if (a[nx][ny] == 0 && d[nx][ny][z] == 0) { // 다음 이동할 위치가 벽이 아니고, 방문하지 않은 경우
						d[nx][ny][z] = d[p.x][p.y][z] + 1;
						q.add(new Pair3(nx, ny, z));
					} else if (a[nx][ny] == 1 && z == 0 && d[nx][ny][z+1] == 0) { // 다음 이동할 위치가 벽이고, 방문하지 않은 경우, 지금까지 벽을 한번도 뚫지 않은 경우 
						d[nx][ny][z+1] = d[p.x][p.y][z] + 1;
						q.add(new Pair3(nx, ny, z+1));
					}
				}
			}
		}
		
		int result = -1;
		if (d[n-1][m-1][0] != 0 && d[n-1][m-1][1] != 0) { // 벽을 부수거나 부수지 않고 둘 다 갈 수 있는 경우 작은 값을 선택한다.
			result = Math.min(d[n-1][m-1][0], d[n-1][m-1][1]);
		} else if (d[n-1][m-1][0] == 0 && d[n-1][m-1][1] != 0) { // 벽을 부수고 가는 경우만 있는 경우
			result = d[n-1][m-1][1];
		} else if (d[n-1][m-1][0] != 0 && d[n-1][m-1][1] == 0) { // 벽을 부수지 않고 가는 경우만 있는 경우
			result = d[n-1][m-1][0];
		} 
		System.out.println(result);
		
		sc.close();
	}

}
