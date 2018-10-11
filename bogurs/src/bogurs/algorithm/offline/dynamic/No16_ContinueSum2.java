package bogurs.algorithm.offline.dynamic;

import java.util.Scanner;

/**
 * 연속합2
 * 
 * 연속합1에서 구했던 문제에서 수를 최대 하나 없앨수 있는 경우
 * 연속합의 최대값
 * 
 * 연속합1에서의
 * d[n] = max(a[n], n까지 최대 연속합)
 * 
 * 연속합2에서의
 * 인덱스를 하나씩 지정해서 인덱스를 기준으로 좌측에서부터의 최대합 및
 * 우측에서부터의 최대합을 구해서 인덱스가 만날 때 그 합들을 더해서 새로운 최대합으로 구할 수 있다.
 * 
 * @author thsong
 *
 */
public class No16_ContinueSum2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // n(1≤n≤100,000)
		int[] a = new int[n+1];
		for (int i = 1; i <= n; i++) {
			a[i] = sc.nextInt(); // 수는 -1,000보다 크거나 같고, 1,000보다 작거나 같은 정수
		}
		
		int[] dl = new int[n+1];
		dl[1] = a[1];
		int sum = a[1];
		for (int i = 2; i <= n; i++) {
			sum += a[i];
			if (sum >= a[i]) { // 현재 숫자보다 연속합이 크거나 같은 경우 (연속합으로 계속 가도 된다)
				dl[i] = sum;
			} else { // 현재 숫자보다 연속합이 작은 경우 (새 숫자로 시작하는 연속합을 다시 구해야 한다)
				dl[i] = a[i];
				sum = a[i];
			}
		}
		int[] dr = new int[n+1];
		dr[n] = a[n];
		sum = a[n];
		for (int i = n-1; i > 0; i--) {
			sum += a[i];
			if (sum >= a[i]) { // 현재 숫자보다 연속합이 크거나 같은 경우 (연속합으로 계속 가도 된다)
				dr[i] = sum;
			} else { // 현재 숫자보다 연속합이 작은 경우 (새 숫자로 시작하는 연속합을 다시 구해야 한다)
				dr[i] = a[i];
				sum = a[i];
			}
		}
		
		int res = Integer.MIN_VALUE;
		for (int i = 1; i <= n; i++) { // 요소를 하나도 제거하지 않았을 때 연속합의 최대값
			if (dl[i] > res) {
				res = dl[i];
			}
		}
		for (int i = 2; i < n; i++) { // i번째 요소를 없앴을 때 최대합 (무조건 없애는 경우를 포함한다)
			if (dl[i-1] + dr[i+1] > res) {
				res = dl[i-1] + dr[i+1];
			}
		}
		System.out.println(res);
		
		sc.close();
	}

}
