package bogurs.algorithm.offline.bfs2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair8 {
	int x;
	int y;
	public Pair8(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
/**
 * 레이저 통신
 * 
 * C를 연결하기 위해 설치해야 하는 거울 개수의 최솟값
 * 
 * C시작점은 두 좌표중 하나에서 시작하면 된다.
 * 4방면으로 일직선이 되는 구간은 가중치 0.
 * 0에서 꺾이는 구간은 1...
 * 1에서 꺾이는 구간은 2...
 * 이런식으로 가중치를 더해서 bfs를 진행하면 된다.
 * 
 * 이후 C도착점으로 지정한 곳의 거리를 출력하면 된다.
 * @author thsong
 *
 */
public class Bfs2_No2_LaserCommu {
	private static final int[] dx = {0, 1, 0, -1};
	private static final int[] dy = {1, 0, -1, 0}; // 우,하,좌,상
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int w = sc.nextInt();
		int h = sc.nextInt(); // (1 ≤ W, H ≤ 100)
		
		int cCnt = 0; // C를 찾은 갯수 (최대 2)
		int sx = 0, sy = 0, ex = 0, ey = 0;
		int[][] d = new int[h][w];
		String[] a = new String[h];
		for (int i = 0; i < h; i++) {
			a[i] = sc.next();
			for (int j = 0; j < w; j++) {
				char nowChar = a[i].charAt(j);
				if (nowChar == 'C') { // 현재 좌표가 출발점 또는 도착점이라면
					if (cCnt == 0) {
						sx = i;
						sy = j;
					} else {
						ex = i;
						ey = j;
					}
					cCnt++;
				}
			}
		}
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				d[i][j] = -1;
			}
		}
		
		Queue<Pair8> q = new LinkedList<>();
		q.add(new Pair8(sx, sy));
		d[sx][sy] = 0;
		while (!q.isEmpty()) {
			Pair8 p = q.remove();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				while (nx >= 0 && ny >= 0 && nx < h && ny < w) {
					if (a[nx].charAt(ny) == '*') break;
					if (d[nx][ny] == -1) {
						q.add(new Pair8(nx, ny));
						d[nx][ny] = d[p.x][p.y] + 1;
					}
					nx += dx[i];
					ny += dy[i];
				}
			}
		}
		
		System.out.println(d[ex][ey]-1);
		sc.close();
	}

}
