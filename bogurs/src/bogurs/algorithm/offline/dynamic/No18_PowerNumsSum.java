package bogurs.algorithm.offline.dynamic;

import java.util.Scanner;

/**
 * 제곱수의 합
 * 
 * 주어진 자연수 N을 제곱수들의 합으로 표현할 때에 그 항의 최소개수를 구하는 프로그램
 * 
 * d[n] = ? + ? + ? + ... + ? (?은 제곱수)
 * 와 같이 나타낼 수 있는 수 중 최소값을 구해야 하는데,
 * n - Math.pow(sqrt(n), 2) 이 0보다 큰경우마다 계속해서 실행해야 한다.
 * 
 * d[1] = 1
 * d[2] = 2
 * d[3] = 3
 * @author thsong
 *
 */
public class No18_PowerNumsSum {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // (1 ≤ N ≤ 100,000)
		int[] d = new int[n+1];
		
		d[1] = 1;
		for (int i = 2; i <= n; i++) {
			d[i] = Integer.MAX_VALUE;
			for (int j = 1, k = 1; j <= (int) Math.sqrt(i); j++, k=(int) Math.pow(j, 2)) {
				int new_now = d[i-k] + 1;
				if (new_now < d[i]) {
					d[i] = new_now;
				}
			}
		}
		
		System.out.println(d[n]);
		sc.close();
	}

}
