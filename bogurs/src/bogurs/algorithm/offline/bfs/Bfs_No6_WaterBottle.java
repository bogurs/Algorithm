package bogurs.algorithm.offline.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair{
	int a;
	int b;
	public Pair(int a, int b) {
		this.a = a;
		this.b = b;
	}
}
/**
 * 물통
 * 
 * 세 개의 물통 중 A물통에 물이 없을때 용량이 C인 C물통에 담겨있을 수 있는 물의 양을 모두 구하기
 * 
 * A,B,C 물통의 용량이 계속 바뀔수있으므로 다이나믹으로 풀 수 없다.
 * C 물통에 담길 수 있는 물의 양 종류
 * C -> A
 * C -> B
 * A -> C
 * B -> C
 * 
 * 위 네 가지처럼 보이지만 A -> B, B -> A 에 대한 결과도 C 용량에 영향을 
 * 끼치므로 큐에는 넣어준 후 결과에는 저장하지 않으면 된다.
 * 모든 결과 중에서 A의 용량이 0이고, C의 용량이 변하는 결과에만 체크하면서 진행하면 된다.
 * @author thsong
 *
 */
public class Bfs_No6_WaterBottle {
	final static int[] from = {0, 0, 1, 1, 2, 2}; // 0:a, 1:b, 2:c
	final static int[] to = {1, 2, 0, 2, 0, 1}; //a->b, a->c, b->a, b->c, c->a, c->b

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] cap = new int[3];
		for (int i = 0; i < 3; i++) {
			cap[i] = sc.nextInt();
		}
		
		boolean[][] ab = new boolean[cap[0]+1][cap[1]+1];
		boolean[] result = new boolean[cap[2]+1];
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(0, 0)); // a, b물통의 첫 양은 0,0
		ab[0][0] = true;
		result[cap[2]] = true; // a물통이 0으로 시작하므로 시작할때 maxC값은 결과에 저장
		while (!q.isEmpty()) {
			Pair p = q.remove();
			int sum = cap[2];
			sum -= (p.a + p.b);
			int[] cur = {p.a, p.b, sum};
			
			for (int i = 0; i < 6; i++) { //a -> b, a -> c, b -> a, b -> c, c -> a, c -> b
				int[] next = {cur[0], cur[1], cur[2]};
				next[to[i]] += next[from[i]];
				next[from[i]] = 0; // 일단 0으로 만든 뒤 다음 조건문에서 next[to[i]]가 초과되는 경우 나머지를 다시 구해준다.
				if (next[to[i]] >= cap[to[i]]) { // next[to[i]]가 용량을 초과하는 경우 용량만큼의 결과만 가질 수 있고, 그차이만큼은 next[from[i]]에 돌려주어야 한다.
					next[from[i]] = next[to[i]] - cap[to[i]];
					next[to[i]] = cap[to[i]];
				}
				if (!ab[next[0]][next[1]]) {
					ab[next[0]][next[1]] = true;
					q.add(new Pair(next[0], next[1]));
					if (next[0] == 0) {
						result[next[2]] = true;
					}
				}
			}
		}
		
		for (int i = 0; i <= cap[2]; i++) {
			if (result[i]) {
				System.out.print(i + " ");
			}
		}
		
		sc.close();
	}

}
