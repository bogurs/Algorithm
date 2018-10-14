package bogurs.algorithm.offline.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair6 {
	int x;
	int y;
	
	public Pair6(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}
/**
 * 열쇠
 * 
 * 훔칠 수 있는 문서의 최대 개수
 * 
 * 알파벳개수만큼의 큐를 만들어서 문에 도착했을 때 키로 열 수 있는 경우 메인큐에 넣고,
 * 그렇지 않은 경우 알파벳큐에 넣어서 열수 있는 기회가 올 때까지 대기한다.
 * @author thsong
 *
 */
public class Bfs_No13_Key {
	private static final int[] dx = {0, 1, 0, -1};
	private static final int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt(); // max 100
		for (int k = 0; k < t; k++) {
			int h = sc.nextInt();
			int w = sc.nextInt(); // (2 ≤ h, w ≤ 100)
			
			boolean[][] c = new boolean[h+2][w+2];
			int[][] d = new int[h+2][w+2];
			boolean[][] isFind = new boolean[h+2][w+2];
			Queue<Pair6> q = new LinkedList<>(); // 메인 큐
			@SuppressWarnings("unchecked")
			Queue<Pair6>[] qs = new Queue[26]; // 알파벳큐
			for (int i = 0; i < 26; i++) { //모든 알파벳큐 초기화
				qs[i] = new LinkedList<>(); 
			}
			
			for (int i = 1; i < h+1; i++) {
				String line = sc.next();
				for (int j = 1; j < w+1; j++) {
					char now = line.charAt(j-1);
					if (now == '*') {
						c[i][j] = true;
						d[i][j] = -1;
					} else if (now >= 'A' && now <= 'Z') {
						d[i][j] = now;
					} else if (now >= 'a' && now <= 'z') {
						d[i][j] = now;
					} else if (now == '$') {
						isFind[i][j] = true;
					}
				}
			}
			String keys = sc.next();
			int res = 0;
			
			q.add(new Pair6(0, 0)); // 가장 바깥에서부터 진행. 들어갈 수 있는 공간을 찾아야 한다.
			c[0][0] = true;
			while (!q.isEmpty()) {
				Pair6 p = q.remove();
				for (int i = 0; i < 4; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];
					if (nx >= 0 && ny >= 0 && nx <= h+1 && ny <= w+1) {
						if (!c[nx][ny]) {
							if (isFind[nx][ny]) { // 이동해야할 위치에 열쇠가 있는 경우
								res++;
							} else if (d[nx][ny] >= 65 && d[nx][ny] <= 90) { // 'A'~'Z' 사이의 값
								String nowChar = (char) d[nx][ny] + "";
								if (keys.contains(nowChar.toLowerCase())) { // 문을 열수있는 키를 갖고 있는 경우
									c[nx][ny] = true;
									q.add(new Pair6(nx, ny));
								} else { // 문을 열수있는 키가 없는 경우 (추후에 처리하기 위해 알파벳 큐에 넣는다.)
									qs[d[nx][ny]-65].add(new Pair6(nx, ny));
								}
								continue;
							} else if (d[nx][ny] >= 97 && d[nx][ny] <= 122) { // 'a'~'z' 사이의 값
								String nowChar = (char) d[nx][ny] + "";
								keys = keys.concat(nowChar);
								while (!qs[d[nx][ny]-97].isEmpty()) {
									q.add(qs[d[nx][ny]-97].remove());
								}
							} 
							c[nx][ny] = true;
							q.add(new Pair6(nx, ny));
						}
					}
				}
			}
			System.out.println(res);
		}
		
		sc.close();
	}

}
