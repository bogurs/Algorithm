package bogurs.algorithm.offline.dynamic;

import java.util.Scanner;

/**
 * 가장 긴 바이토닉 부분 수열
 * 
 * 수열 A가 주어졌을 때, 가장 긴 바이토닉 부분 수열을 구하기
 * 
 * 가장 긴 증가하는 부분 수열 + 가장 긴 감소하는 부분 수열 - 1이
 * 가장 큰 값을 출력하면 된다.
 * 
 * @author thsong
 *
 */
public class No14_LIS_Bitonic {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // (1 ≤ N ≤ 1,000)
		int[] a = new int[n+1];
		for (int i = 1; i <= n; i++) {
			a[i] = sc.nextInt(); // (1 ≤ Ai ≤ 1,000)
		}
		
		int[] d_lis = new int[n+1];
		d_lis[1] = 1;
		int answer = 1;
		for (int i = 2, j = 0; i <= n; i++) {
			d_lis[i] = 1;
			int temp = 0;
			for (j = i-1; j > 0; j--) {
				int newTemp = 0;
				if (a[i] > a[j]) {
					newTemp = d_lis[j];
				}
				if (newTemp > temp) {
					temp = newTemp;
				}
			}
			d_lis[i] += temp;
		}
		
		int[] d_lds = new int[n+1];
		d_lds[n] = 1;
		for (int i = n-1, j = 0; i >= 1; i--) {
			d_lds[i] = 1;
			int temp = 0;
			for (j = i+1; j <= n; j++) {
				int newTemp = 0;
				if (a[i] > a[j]) {
					newTemp = d_lds[j];
				}
				if (newTemp > temp) {
					temp = newTemp;
				}
			}
			d_lds[i] += temp;
		}
		
		for (int i = 1; i <= n; i++) {
			int newAnswer = d_lis[i] + d_lds[i] - 1;
			if (newAnswer > answer) {
				answer = newAnswer;
			}
		}
		System.out.println(answer);
		
		sc.close();
	}
}
