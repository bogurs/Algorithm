package bogurs.algorithm.offline.dynamic;

import java.util.Scanner;

/**
 * 오르막 수
 * 
 * 점화식: d[n] = 자릿수가 n인 오르막 수의 전체 갯수
 * d[n-1] = 자릿수가 n-1인 오르막 수의 전체 갯수
 * d[n-1]을 통해 d[n]의 식을 나타낼 수 없다.
 * 
 * d[n] = d[n-1] + (가장 마지막 자리가 n-1번째 수와 같거나 큰 수들의 전체 갯수)
 * 마지막 자리를 나타내는 변수인 i를 통해 식을 재정의한다.
 * d[n][i] = 자릿수가 n이고 가장 마지막 수가 i인 오르막 수의 전체 갯수
 * d[n-1][i] = 자릿수가 n-1이고 가장 마지막 수가 i인 오르막 수의 전체 갯수
 * 
 * d[n][i]에서 끝자리 i는 정해져 있다.
 * 따라서 d[n-1][0]~d[n-1][i] 까지 더하면 d[n][i]가 완성된다.
 * d[n][i] += d[n-1][k] (0 <= k <= i)
 * 
 * d[1][0] ~ d[1][9] = 1
 * d[2][0] = 00
 * d[2][1] = 01, 11
 * d[2][2] = 02, 12, 22
 * ...
 * d[2][9] = 09, 19, 29, ..., 99
 * 
 * d[3][0] = 000
 * d[3][1] = 001, 011, 111
 * d[3][2] = 002, 012, 022, 112, 122, 222
 * ...
 * d[3][9] = 009, 019, 029, ..., 999
 * @author thsong
 *
 */
public class No7_IncrementNumber {

	public static void main(String[] args) {
		final int MOD = 10007;
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // (1 ≤ N ≤ 1,000)
		
		//초기값 초기화
		int[][] d = new int[n+1][10];
		for (int i = 0; i < 10; i++) {
			d[1][i] = 1;
		}
		
		//점화식에 따라 d값을 계산
		for (int i = 2; i <= n; i++) { // 자릿수
			for (int j = 0; j < 10; j++) { // 가장 마지막 자리의 수
				for (int k = 0; k <= j; k++) { // 가장 마지막 자리 전체 합구하기(0<=k<=j)
					d[i][j] += (d[i-1][k] % MOD);
				}
				d[i][j] %= MOD;
			}
		}
		
		//d[n][0]~d[n][9] 까지 합을 구한 값을 출력
		int answer = 0;
		for (int i = 0; i < 10; i++) {
			answer += d[n][i];
		}
		System.out.println(answer);
		
		sc.close();
	}

}
