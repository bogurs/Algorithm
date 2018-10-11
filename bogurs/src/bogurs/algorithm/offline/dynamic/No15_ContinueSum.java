package bogurs.algorithm.offline.dynamic;

import java.util.Scanner;

/**
 * 연속합
 * 
 * n개의 숫자 중 연속된 1~n개의 숫자를 선택해서 합을 구했을 때 최대값을 출력
 * 최대 n=100000
 * 최대 연속된 숫자를 고를 수 있는 경우의 수는...
 * 연속된 수라고 가정하지 않고 n개의 숫자중 고를지 말지 선택해서 계산했을 때
 * 2^100000 이므로 매우 큰 경우의 수가 나온다.
 * 
 * 연속된 수만 고르는 경우의 수도 많을 것이므로,
 * 브루트 포스 문제는 아니다.
 * 
 * d[n] = max(a[n-1]+a[n-2]...+a[1]까지 합을 구하다 감소하는 구간에서의 연속합, a[n])
 * 
 * d[1] = a[1]
 * 
 * 
 * @author thsong
 *
 */
public class No15_ContinueSum {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); //n(1 ≤ n ≤ 100,000)
		int[] a = new int[n+1]; // -1,000보다 크거나 같고, 1,000보다 작거나 같은 정수
		for (int i = 1; i <= n; i++) {
			a[i] = sc.nextInt();
		}
		
		int[] d = new int[n+1];
		d[1] = a[1];
		int sum = a[1];
		for (int i = 2; i <= n; i++) {
			sum += a[i];
			if (sum >= a[i]) { // 현재 숫자보다 연속합이 크거나 같은 경우 (연속합으로 계속 가도 된다)
				d[i] = sum;
			} else { // 현재 숫자보다 연속합이 작은 경우 (새 숫자로 시작하는 연속합을 다시 구해야 한다)
				d[i] = a[i];
				sum = a[i];
			}
		}
		
		int res = Integer.MIN_VALUE;
		for (int i = 1; i <= n; i++) {
			if (d[i] > res) {
				res = d[i];
			}
		}
		System.out.println(res);
		
		sc.close();
	}

}
