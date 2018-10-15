package bogurs.algorithm.offline.bfs2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair9 {
	int x = 0;
	int y = 0;
	public Pair9(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
/**
 * 점프 게임
 * 
 * 가장 처음에 유저는 왼쪽 줄의 1번 칸 위에 서 있으며, 매 초마다 아래 세 가지 행동중 하나를 해야 한다.

한 칸 앞으로 이동한다. 예를 들어, 현재 있는 칸이 i번 칸이면, i+1번 칸으로 이동한다.
한 칸 뒤로 이동한다. 예를 들어, 현재 있는 칸이 i번 칸이면, i-1번 칸으로 이동한다.
반대편 줄로 점프한다. 이때, 원래 있던 칸보다 k칸 앞의 칸으로 이동해야 한다. 예를 들어, 현재 있는 칸이 왼쪽 줄의 i번 칸이면, 오른쪽 줄의 i+k번 칸으로 이동해야 한다.
N번 칸보다 더 큰 칸으로 이동하는 경우에는 게임을 클리어한 것이다.

 * 게임을 클리어할 수 있으면 1을, 없으면 0을 출력
 * 
 * i -> i+1 or i-1 or i+k (반대편으로 이동)
 * 
 * @author thsong
 *
 */
public class Bfs2_No4_ZumpGame {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int k = sc.nextInt(); // (1 ≤ N, k ≤ 100,000)
		int[][] a = new int[n][2];
		int[][] d = new int[n][2];
		for (int i = 0; i < 2; i++) {
			String line = sc.next();
			for (int j = 0; j < n; j++) {
				a[j][i] = line.charAt(j) - 48;
				d[j][i] = -1;
			}
		}
		
		int[] bomb = new int[n];
		for (int i = 1; i <= n; i++) {
			bomb[i-1] = i; //이동할수 있는 곳이 사라지는 시간 (1초에 한칸)
		}
		
		Queue<Pair9> q = new LinkedList<>();
		q.add(new Pair9(0, 0)); // 첫번째 라인의 첫번째 구역에서 시작
		d[0][0] = 0;
		int isSuccess = 0;
		while (!q.isEmpty()) {
			Pair9 p = q.remove();
			if (p.x+1 >= n || p.x+k >= n) {
				isSuccess++;
				break;
			}
			if (p.x+1 < n && a[p.x+1][p.y] == 1) { // 다음 이동할 구간이 n보다 작고, 이동할 수 있다면(1)
				if (d[p.x+1][p.y] == -1 && d[p.x][p.y]+1 < bomb[p.x+1]) { // 계단이 사라지는 것보다 이동할 수 있는 거리가 작다면
					d[p.x+1][p.y] = d[p.x][p.y]+1;
					q.add(new Pair9(p.x+1, p.y));
				}
			}
			if (p.x-1 >= 0 && a[p.x-1][p.y] == 1) { // 다음 이동할 구간이 0보다 크거나 같고, 이동할 수 있다면(1)
				if (d[p.x-1][p.y] == -1 && d[p.x][p.y]+1 < bomb[p.x-1]) { // 계단이 사라지는 것보다 이동할 수 있는 거리가 작다면
					d[p.x-1][p.y] = d[p.x][p.y]+1;
					q.add(new Pair9(p.x-1, p.y));
				}
			}
			if (p.x+k < n && a[p.x+k][(p.y+1)%2] == 1) { // 다음 이동할 구간이 n보다 작고, 이동할 수 있다면(1)
				if (d[p.x+k][(p.y+1)%2] == -1 && d[p.x][p.y]+1 < bomb[p.x+k]) { // 계단이 사라지는 것보다 이동할 수 있는 거리가 작다면
					d[p.x+k][(p.y+1)%2] = d[p.x][p.y]+1;
					q.add(new Pair9(p.x+k, (p.y+1)%2));
				}
			}
		}
		System.out.println(isSuccess);
		
		sc.close();
	}

}
