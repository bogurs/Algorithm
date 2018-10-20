package bogurs.algorithm.offline.dynamic;

import java.util.Scanner;

/**
 * 피보나치
 * 
 * n은 45보다 작거나 같은 자연수
 * n번째 피보나치 수 출력
 * @author thsong
 *
 */
public class No0_Fibo {
	private static int[] memo;
	
	private static int fibo(int n) {
		if (n < 0) {
			return 0;
		} else if (n <= 1) {
			return n;
		} else {
			if (memo[n] > 0) {
				return memo[n];
			}
			memo[n] = fibo(n-1) + fibo(n-2);
			return memo[n];
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // n은 45보다 작거나 같은 자연수
		memo = new int[n+1];
		System.out.println(fibo(n));
		
		sc.close();
	}

}
