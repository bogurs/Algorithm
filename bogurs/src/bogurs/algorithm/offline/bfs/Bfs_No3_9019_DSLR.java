package bogurs.algorithm.offline.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * DSLR
 * 
 * 1. D: Double. 결과 값이 9999 이상인 경우 10000으로 나눈 나머지를 취한다. (2n mod 10000)
 * 2. S: Subtract. 1을 뺀다. 0에서 빼는 경우 9999를 저장한다.
 * 3. L: 각 자릿수를 왼쪽으로 회전한 결과를 저장한다.
 * 4. R: 각 자릿수를 오른쪽으로 회전한 결과를 저장한다.
 * 
 * 4가지 정점이 정해졌음.
 * 간선의 거리는 1
 * 최소값을 구해야 하는 경우이므로 bfs를 적용한다.
 * 
 * @author thsong
 *
 */
public class Bfs_No3_9019_DSLR {

	public static void main(String[] args) {
		final int MAX = 10000;
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		int[][] ab = new int[t][2]; //A 와 B는 모두 0 이상 10,000 미만
		for (int i = 0; i < t; i++) {
			ab[i][0] = sc.nextInt();
			ab[i][1] = sc.nextInt();
		}
		
		//각 정점의 계산을 구현한다.
		//1. D, 2. S, 3. L, 4. R
		for (int i = 0; i < t; i++) {
			boolean[] c = new boolean[MAX];
			String[] d = new String[MAX];
			int[] f = new int[MAX];
			Queue<Integer> q = new LinkedList<>();
			
			int a = ab[i][0];
			q.add(a);
			d[a] = "";
			while (!q.isEmpty()) {
				int p = q.remove();
				int doub = (2*p) % MAX;
				int subt = p-1;
				if (subt == -1) {
					subt = MAX - 1;
				}
				int left = (int) (((p%1000) * 10) + (p/1000));
				int righ = (int) (((p%10) * 1000) + (p/10));
				
				if (!c[doub]) { // D
					c[doub] = true;
					d[doub] = "D";
					f[doub] = p;
					q.add(doub);
				}
				if (!c[subt]) { // S
					c[subt] = true;
					d[subt] = "S";
					f[subt] = p;
					q.add(subt);
				}
				if (!c[left]) { // L
					c[left] = true;
					d[left] = "L";
					f[left] = p;
					q.add(left);
				}
				if (!c[righ]) { // R
					c[righ] = true;
					d[righ] = "R";
					f[righ] = p;
					q.add(righ);
				}
			}
			int newB = ab[i][1];
			StringBuilder sb = new StringBuilder();
			while (a != newB) {
				sb.append(d[newB]);
				newB = f[newB];
			}
			System.out.println(sb.reverse().toString());
		}
		
		sc.close();
	}

}
