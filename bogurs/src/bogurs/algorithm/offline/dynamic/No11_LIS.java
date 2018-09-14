package bogurs.algorithm.offline.dynamic;

import java.util.Scanner;

/**
 * 가장 긴 증가하는 부분 수열
 * 
 * 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하기
 * 
 * 수열인 배열의 최대 크기는 1000이고 배열의 원소를 하나씩 선택하거나
 * 선택하지 않거나 2가지 방법이 있으므로 시간복잡도는 O(2^1000)으로
 * 1억이 넘어가므로, 브루트 포스로 풀지 못한다.
 * 
 * d[n] = 수열 n의 배열에서 마지막 자리가 a[n]인 가장 긴 증가하는 부분 수열
 * d[1] = 1
 * d[2] = 1 + d[1](when a[2] > a[j]) (j = 1)
 * d[3] = 1 + d[2](when a[3] > a[j]) (1 <= j <= 2)
 * ...
 * d[n] = 1 + d[n-1](when a[n] > a[j]) (1 <= j <= n-1)
 * 
 * max(d[1]~d[n])
 * 
 * @author thsong
 *
 */
public class No11_LIS {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // (1 ≤ N ≤ 1,000)
		int[] a = new int[n+1];
		for (int i = 1; i <= n; i++) {
			a[i] = sc.nextInt(); // (1 ≤ Ai ≤ 1,000)
		}
		
		int[] d = new int[n+1];
		d[1] = 1;
		int answer = 1;
		for (int i = 2, j = 0; i <= n; i++) {
			d[i] = 1;
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
