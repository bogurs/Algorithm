package bogurs.algorithm.offline.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 물통
 * 
 * 세 개의 물통 중 용량이 C인 C물통에 담겨있을 수 있는 물의 양을 모두 구하기
 * 
 * A,B,C 물통의 용량이 계속 바뀔수있으므로 다이나믹으로 풀 수 없다.
 * C 물통에 담길 수 있는 물의 양 종류
 * C -> C-A
 * C -> C-B
 * @author thsong
 *
 */
public class Bfs_No6_WaterBottle {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		
		boolean[] ch = new boolean[c+1];
		int[] result = new int[c+1];
		Queue<Integer> q = new LinkedList<>();
		q.add(c);
		ch[c] = true;
		result[0] = c;
		int i = 1;
		while (!q.isEmpty()) {
			int p = q.remove();
 			if (p-a >= 0) {
				if (!ch[p-a]) {
					ch[p-a] = true;
					result[i] = p-a;
					q.add(p-a);
					i++;
				}
			}
			if (p-b >= 0) {
				if (!ch[p-b]) {
					ch[p-b] = true;
					result[i] = p-b;
					q.add(p-b);
					i++;
				}
			}
			if (p+a <= c) {
				if (!ch[p+a]) {
					ch[p+a] = true;
					result[i] = p+a;
					q.add(p+a);
					i++;
				}
			}
			if (p+b <= c) {
				if (!ch[p+b]) {
					ch[p+b] = true;
					result[i] = p+b;
					q.add(p+b);
					i++;
				}
			}
		}
		
		Arrays.sort(result);
		for (int j = 0; j <= c; j++) {
			int newResult = result[j];
			if (newResult != 0) {
				System.out.print(result[j] + " ");
			}
		}
		
		sc.close();
	}

}
