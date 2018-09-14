package bogurs.algorithm.offline.dynamic;

import java.util.Scanner;

/**
 * 가장 큰 증가 부분 수열
 * 
 * 수열 A의 부분 수열 중 합이 가장 큰 부분 수열
 * 
 * 가장 긴 증가 부분 수열 문제에서 d[i] = 1 대신 d[i] = a[i] 를 사용해서
 * 해결할 수 있다.
 * 
 * d[n] = 수열 n의 배열에서 마지막 자리가 a[n]인 가장 큰 증가하는 부분 수열
 * d[1] = a[1]
 * d[2] = a[2] + d[1](when a[2] > a[j]) (j = 1)
 * d[3] = a[n] + d[2](when a[3] > a[j]) (1 <= j <= 2)
 * ...
 * d[n] = a[n] + d[n-1](when a[n] > a[j]) (1 <= j <= n-1)
 * 
 * max(d[1]~d[n])
 * @author thsong
 *
 */
public class No12_LIS_Big {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // N (1 ≤ N ≤ 1,000)
		int[] a = new int[n+1];
		for (int i = 1; i <= n; i++) {
			a[i] = sc.nextInt(); // (1 ≤ Ai ≤ 1,000)
		}
		
		int[] d = new int[n+1];
		d[1] = a[1];
		int answer = d[1];
		for (int i = 2, j = 0; i <= n; i++) {
			d[i] = a[i];
			int temp = 0;
			for (j = i-1; j > 0; j--) {
				int newTemp = 0;
				if (a[i] > a[j]) {
					newTemp = d[j];
				}
				if (newTemp > temp) {
					temp = newTemp;
				}
			}
			d[i] += temp;
			if (d[i] > answer) {
				answer = d[i];
			}
		}
		System.out.println(answer);
		
		sc.close();
	}

}
