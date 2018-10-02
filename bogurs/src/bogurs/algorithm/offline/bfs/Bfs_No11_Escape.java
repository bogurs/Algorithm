package bogurs.algorithm.offline.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair4 {
	int x;
	int y;
	public Pair4(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
/**
 * 탈출
 * 
 * 고슴도치와 비버굴의 위치가 주어지고 물이 차오를 때 고슴도치가 물에 빠지지 않고 비버굴로
 * 이동할 수 있는 최단거리
 * 
 * 물이 차오르는 것에 대한 bfs 결과를 별도로 계산하고,
 * 비버가 이동하는 것에 대한 bfs를 구한다.
 * 이때 비버는 물이 차오르는 bfs결과값보다 작을 때에만 이동할 수 있다.
 * 
 * 안전하게 이동할 수 없다면 KAKTUS 를 출력한다.
 * @author thsong
 *
 */
public class Bfs_No11_Escape {
	static final int[] dx = {0, 1, 0, -1};
	static final int[] dy = {1, 0, -1, 0}; //우,하,좌,상

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int r = sc.nextInt(); //50보다 작거나 같은 자연수 R과 C
		int c = sc.nextInt();
		String[][] m = new String[r][c];
		Queue<Pair4> q = new LinkedList<>();
		int[][] water = new int[r][c]; // 물이 차오르는 것에 대한 bfs계산 결과
		int[][] gosm = new int[r][c]; // 고슴도치의 이동하는 것에 대한 bfs계산 결과
		int sx = 0, sy = 0, ex = 0, ey = 0;
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				water[i][j] = -1; // 물이 차오를 때 자체 플래그로 사용하기 위해 -1로 정한다.
				gosm[i][j] = -1; // 고슴도치가 이동할 때 자체 플래그로 사용하기 위해 -1로 정한다.
			}
		}
		
		for (int i = 0; i < r; i++) {
			String temp = sc.next();
			for (int j = 0; j < c; j++) {
				m[i][j] = temp.charAt(j) + "";
				if (m[i][j].equals("*")) {
					water[i][j] = 0;
					q.add(new Pair4(i, j));
				} else if (m[i][j].equals("D")) { // 비버의 굴
					ex = i;
					ey = j;
				} else if (m[i][j].equals("S")) { // 고슴도치의 위치
					gosm[i][j] = 0;
					sx = i;
					sy = j;
				} 
			}
		}
		
		//물이 차오르는 것에 대해 bfs계산
		while (!q.isEmpty()) {
			Pair4 wp = q.remove();
			for (int i = 0; i < 4; i++) {
				int nwx = wp.x + dx[i];
				int nwy = wp.y + dy[i];
				if (nwx >= 0 && nwy >= 0 && nwx < r && nwy < c) {
					if (water[nwx][nwy] != -1) continue;
					if (m[nwx][nwy].equals("X")) continue;
					if (m[nwx][nwy].equals("D")) continue;
					water[nwx][nwy] = water[wp.x][wp.y] + 1;
					q.add(new Pair4(nwx, nwy));
				}
			}
		}
		
		q.add(new Pair4(sx, sy));
		while (!q.isEmpty()) {
			Pair4 gp = q.remove();
			for (int i = 0; i < 4; i++) {
				int ngx = gp.x + dx[i];
				int ngy = gp.y + dy[i];
				if (ngx >= 0 && ngy >= 0 && ngx < r && ngy < c) {
					if (gosm[ngx][ngy] == -1) { // 고슴도치가 방문하지 않은 곳이어야 한다.
						if (m[ngx][ngy].equals("X")) continue;
						if (gosm[gp.x][gp.y] + 1 >= water[ngx][ngy] && water[ngx][ngy] != -1) continue;
						gosm[ngx][ngy] = gosm[gp.x][gp.y] + 1;
						q.add(new Pair4(ngx, ngy));
					}
				}
			}
		}
		
		if (gosm[ex][ey] < 0) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(gosm[ex][ey]);
		}
		
		sc.close();
	}

}
