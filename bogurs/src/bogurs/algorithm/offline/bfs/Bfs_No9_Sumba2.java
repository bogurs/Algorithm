package bogurs.algorithm.offline.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 숨바꼭질 2
 * 
 * 가장 빨리 동생을 찾는 이동 시간 및 최소 시간이 되는 모든 경우의 수
 * 5 -> 10 -> 9 -> 18 -> 17
 * 5 -> 4 -> 8 -> 16 -> 17
 * 
 * @author thsong
 *
 */
public class Bfs_No9_Sumba2 {
	static final int MAX = 1000000;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // N(0 ≤ N ≤ 100,000)
		int k = sc.nextInt(); // K(0 ≤ K ≤ 100,000)
		
		boolean[] c = new boolean[MAX];
		int[] d = new int[MAX];
		int[] cnt = new int[MAX]; // cnt[k] = k까지 가는 최소 방법의 수
		Queue<Integer> q = new LinkedList<>();
		q.add(n);
		c[n] = true;
		d[n] = 0;
		cnt[n] = 1; // n에 방문한 횟수를 누적
		while (!q.isEmpty()) {
			int p = q.remove();
			if (p-1 >= 0) {
				if (!c[p-1]) {
					c[p-1] = true;
					d[p-1] = d[p] + 1;
					q.add(p-1);
					cnt[p-1] = cnt[p];
				} else if (d[p-1] == d[p] + 1) {
					cnt[p-1] += cnt[p];
				}
			}
			if (p+1 < MAX) {
				if (!c[p+1]) {
					c[p+1] = true;
					d[p+1] = d[p] + 1;
					q.add(p+1);
					cnt[p+1] = cnt[p];
				} else if (d[p+1] == d[p] + 1) {
					cnt[p+1] += cnt[p];
				}
			}
			if (p*2 < MAX) {
				if (!c[p*2]) {
					c[p*2] = true;
					d[p*2] = d[p] + 1;
					q.add(p*2);
					cnt[p*2] = cnt[p];
				} else if (d[p*2] == d[p] + 1) {
					cnt[p*2] += cnt[p];
				}
			}
		}
		System.out.println(d[k]);
		System.out.println(cnt[k]);
		
		sc.close();
	}

}
