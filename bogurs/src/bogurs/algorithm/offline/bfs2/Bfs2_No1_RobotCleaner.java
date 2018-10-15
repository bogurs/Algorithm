package bogurs.algorithm.offline.bfs2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair7 {
	int x;
	int y;
	public Pair7(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
/**
 * 로봇청소기
 * 
 * 방의 정보가 주어졌을 때, 더러운 칸을 모두 깨끗한 칸으로 만드는데 필요한 이동 횟수의 최솟값
 * 
 * 먼지 지점을 방문할 수 있는 최소값을 찾는 다익스트라.
 * 먼지 지점에 갈 수 있는 최소횟수를 찾아서 계속 교체해 주어야 한다.
 * @author thsong
 *
 */
public class Bfs2_No1_RobotCleaner {
	private static final int[] dx = {0, 1, 0, -1};
	private static final int[] dy = {1, 0, -1, 0};
	
	private static int[][] bfs(String[] lines, int x, int y) {
		int n = lines.length;
		int m = lines[0].length();
		int[][] dist = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				dist[i][j] = -1;
			}
		}
		
		Queue<Pair7> q = new LinkedList<>();
		q.add(new Pair7(x, y));
		dist[x][y] = 0;
		while (!q.isEmpty()) {
			Pair7 p = q.remove();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
					if (dist[nx][ny] == -1 && lines[nx].charAt(ny) != 'x') {
						dist[nx][ny] = dist[p.x][p.y] + 1;
						q.add(new Pair7(nx, ny));
					}
				}
			}
		}
		return dist;
	}
	
	private static boolean next_permutaion(int[] arr) {
		int i = arr.length -1;
		while (i > 0 && arr[i-1] > arr[i]) i--;
		if (i <= 0) return false;
		
		int j = arr.length -1;
		while (arr[i-1] > arr[j]) j--;
		
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		
		for (int x = i, y = arr.length-1; x < y; x++, y--) {
			temp = arr[x];
			arr[x] = arr[y];
			arr[y] = temp;
		}
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (sc.hasNext()) {
			int w = sc.nextInt(); // (1 ≤ w, h ≤ 20)
			int h = sc.nextInt(); // (1 ≤ w, h ≤ 20)
			if (w==0 && h==0) break;
			
			ArrayList<Pair7> spots = new ArrayList<>();
			String[] lines = new String[h];
			spots.add(new Pair7(0, 0)); // 로봇청소기의 위치를 가장 먼저 저장하기 위해서 임시로 0,0을 넣는다.
			int sx = 0, sy = 0;
			for (int i = 0; i < h; i++) {
				lines[i] = sc.next();
				for (int j = 0; j < w; j++) {
					if (lines[i].charAt(j) == 'o') { // 로봇청소기의 최초 위치
						sx = i;
						sy = j;
					} else if (lines[i].charAt(j) == '*') { // 더러운 위치
						spots.add(new Pair7(i, j));
					}
				}
			}
			spots.set(0, new Pair7(sx, sy)); // 로봇청소기의 위치를 가장 앞에 설정
			int size = spots.size();
			int[][] d = new int[size][size];
			boolean isDirty = false;
			for (int i = 0; i < size; i++) {
				int[][] dist = bfs(lines, spots.get(i).x, spots.get(i).y);
				for (int j = 0; j < size; j++) {
					d[i][j] = dist[spots.get(j).x][spots.get(j).y];
					if (d[i][j] == -1) {
						isDirty = true;
					}
				}
			}
			
			if (isDirty) {
				System.out.println(-1);
				continue;
			}
			
			int res = Integer.MAX_VALUE;
			int[] p = new int[size-1];
			for (int i = 1; i < size; i++) {
				p[i-1] = i;
			}
			
			do {
				int now_res = d[0][p[0]];
				for (int i = 0; i < size-2; i++) {
					now_res += d[p[i]][p[i+1]];
				}
				if (now_res < res) {
					res = now_res;
				}
			} while (next_permutaion(p));
			System.out.println(res);
		}
		
		sc.close();
	}

}
