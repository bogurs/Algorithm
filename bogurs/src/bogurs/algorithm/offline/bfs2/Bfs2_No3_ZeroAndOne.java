package bogurs.algorithm.offline.bfs2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 0과 1
 * 
 * 각각의 테스트 케이스마다 N의 배수이면서, 구사과가 좋아하는 수를 아무거나 출력한다. 만약, 그러한 수가 없다면 BRAK을 출력
 * 
 * 구사과가 좋아하는 수:1이 최소 1개 있으면서, 1과 0으로만 이루어진 수 중에서 가장 작은 값
 * 가능한 수의 경우,
 * 1,
 * 10, 11
 * 100, 101, 110, 111
 * 1000, 1001, 1010, 1011, 1100, 1101, 1110, 1111
 * ...
 * 앞 숫자는 고정하고 나머지 자리는 2^(n-1) 만큼 가능함
 * @author thsong
 *
 */
public class Bfs2_No3_ZeroAndOne {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt(); // T(T < 1,000)
		for (int i = 0; i < t; i++) {
			int a = sc.nextInt(); // N은 20,000보다 작거나 같은 자연수
			int[] d = new int[a];
			int[] from = new int[a];
			int[] how = new int[a];
			for (int j = 0; j < a; j++) {
				d[j] = -1;
				from[j] = -1;
				how[j] = -1;
			}
			Queue<Integer> q = new LinkedList<>();
			q.add(1%a);
			d[1%a] = 0;
			how[1%a] = 1;
			while (!q.isEmpty()) {
				int p = q.remove();
				for (int j = 0; j <= 1; j++) {
					int np = (p*10+j)%a;
					if (d[np] == -1) {
						d[np] = d[p] + 1;
						from[np] = p;
						how[np] = j;
						q.add(np);
					}
				}
			}
			if (d[0] == -1) {
				System.out.println("BRAK");
			} else {
				StringBuilder sb = new StringBuilder();
				for (int j = 0; from[j] != -1; j=from[j]) {
					sb.append(how[j]);
				}
				sb.append("1");
				System.out.println(sb.reverse());
			}
		}
		
		sc.close();
	}

}
