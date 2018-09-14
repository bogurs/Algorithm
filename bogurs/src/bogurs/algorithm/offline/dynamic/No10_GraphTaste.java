package bogurs.algorithm.offline.dynamic;

import java.util.Scanner;

/**
 * 포도주 시식
 * 
 * 최대로 마실수 있는 포도주의 양을 구하기
 * 
 * 연속으로 놓여 있는 3잔을 마실 수 없다.
 * 
 * 마신다/마시지않는다 둘 중 하나를 선택하는데 포도주 잔의 개수가 10000이므로
 * O(2^10000) 으로 브루트포스로는 문제를 풀지못한다.
 * 
 * d[n] = n개 중 포도주를 마실 수 있는 최대양
 * 조건으로 연속으로 3잔까지 마실 수 없다는 것이 주어졌기 때문에 연속해서
 * 마실지 안 마실지에 대한 변수 i를 추가한다 (i=0=0번 연속. i=1=1번 연속. i=2=2번 연속.)
 * d[n][i] = n개 중 연속된 잔을 i(행동)로 결정했을 때 최대양
 * d[n][0] = a[n]을 마시지 않았을 때의 최대양
 * d[n][1] = a[n]은 마시고, a[n-1]을 마시지 않았을 때의 최대양
 * d[n][2] = a[n]과 a[n-1]은 마시고, a[n-2]는 마시지 않았을 때의 최대양
 * 
 * d[1][0] = 0
 * d[1][1] = a[1]
 * d[1][2] = a[1]
 * d[2][0] = a[1]
 * d[2][1] = a[2]
 * d[2][2] = a[1] + a[2]
 * 
 * @author thsong
 *
 */
public class No10_GraphTaste {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // (1≤n≤10,000)
		int[][] d = new int[n+1][3];
		int[] a = new int[n+1];
		for (int i = 1; i <= n; i++) {
			a[i] = sc.nextInt();
		}
		
		d[1][0] = 0;
		d[1][1] = d[1][2] = a[1];
		
		if (n >= 2) {
			d[2][0] = a[1];
			d[2][1] = a[2];
			d[2][2] = a[1] + a[2];
		}
		for (int i = 3; i <= n; i++) {
			d[i][0] = Math.max(Math.max(d[i-1][1], d[i-1][2]), d[i-1][0]);
			d[i][1] = d[i-1][0] + a[i];
			d[i][2] = d[i-2][0] + a[i-1] + a[i];
		}
		
		int answer = Math.max(Math.max(d[n][0], d[n][1]), d[n][2]);
		System.out.println(answer);
		
		sc.close();
	}

}
