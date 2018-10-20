package bogurs.algorithm.offline.dynamic;

import java.util.Scanner;

/**
 * 피보나치 5
 * 
 * n은 20보다 작거나 같은 자연수 또는 0
 * n번째 피보나치 수 출력
 * @author thsong
 *
 */
public class No0_Fibo5 {
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
		
		int n = sc.nextInt(); // n은 20보다 작거나 같은 자연수 또는 0
		memo = new int[n+1];
		System.out.println(fibo(n));
		
		sc.close();
	}

}
