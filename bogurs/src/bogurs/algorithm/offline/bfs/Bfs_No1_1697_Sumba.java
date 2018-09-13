package bogurs.algorithm.offline.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 숨바꼭질
 * 
 * 이동경로: x-1, x+1, 2x 1초에 1칸씩 이동하고 모두 연결되어 있으므로 bfs로 적용
 * 
 * @author thsong
 *
 */
public class Bfs_No1_1697_Sumba {
	private static Queue<Integer> q = new LinkedList<>();
	private static boolean[] c = new boolean[1000000];
	private static int[] d = new int[1000000];
	
	private static void bfs(int n, int k) {
        c[n] = true;
		while (!q.isEmpty()) {
			int p = q.remove();
			if (p-1 >= 0) {//방문하지 않았다면
				if (!c[p-1]) {
					q.add(p-1);
					c[p-1] = true;
					d[p-1] = d[p] + 1;
				}
			}
			if (p+1 < 1000000) {
				if (!c[p+1]) {
					q.add(p+1);
					c[p+1] = true;
					d[p+1] = d[p] + 1;
				}
			}
			if (p*2 < 1000000) {
				if (!c[p*2]) {
					q.add(p*2);
					c[p*2] = true;
					d[p*2] = d[p] + 1;
				}
			}
		}
        System.out.println(d[k]);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // N(0 ≤ N ≤ 100,000)
		int k = sc.nextInt(); // K(0 ≤ K ≤ 100,000)
		q.add(n);
		bfs(n, k);
		
		sc.close();
	}

}
