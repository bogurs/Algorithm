package bogurs.algorithm.offline.bfs;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 숨바꼭질 3
 * 숨바꼭질 1에서 순간이동하는 경우는 가중치를 0으로 처리하는 경우
 * 
 * x -> 2*x 는 가중치가 0
 * x -> x-1 or x+1 은 가중치가 1
 * 
 * 가중치가 0인 큐,
 * 가중치가 1인 큐로 별도로 구성하여 0인 큐 부터 처리한 후 1인 큐를 처리하게 해서 구현할 수도 있고,
 * 
 * 선입선출/후입후출이 모두 가능한 덱을 구성해서 가중치가 0인 값은 front에, 1인 값은 back에 넣어서 처리해도 된다.
 * @author thsong
 *
 */
public class Bfs_No7_Sumba3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // 수빈이 위치 N(0 ≤ N ≤ 100,000)
		int k = sc.nextInt(); // 동생 위치 K(0 ≤ K ≤ 100,000)
		int max = 1000000;
		
		boolean[] c = new boolean[max]; // 방문 여부
		int[] d = new int[max]; // 시간 누적을 위함
		Deque<Integer> dq = new LinkedList<>();
		dq.addLast(n);
		c[n] = true;
		d[n] = 0;
		while (!dq.isEmpty()) {
			int p = dq.removeFirst();
			System.out.println(p);
			if (p-1 >= 0) { // -1
				if (!c[p-1]) {
					c[p-1] = true;
					d[p-1] = d[p] + 1;
					dq.addLast(p-1);
				}
			}
			if (p+1 < max) { // +1
				if (!c[p+1]) {
					c[p+1] = true;
					d[p+1] = d[p] + 1;
					dq.addLast(p+1);
				}
			}
			if (p*2 < max) { // *2
				if (!c[p*2]) {
					c[p*2] = true;
					d[p*2] = d[p]; // 순간이동은 가중치가 0임
					dq.addFirst(p*2); // 순간이동하는 경우 덱의 가장 앞에 넣는다.
				}
			}
		}
		System.out.println(d[k]);
		
		sc.close();
	}

}
