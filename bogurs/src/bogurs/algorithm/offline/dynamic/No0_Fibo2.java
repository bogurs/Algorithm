package bogurs.algorithm.offline.dynamic;

import java.util.Scanner;

/**
 * 피보나치2
 * 
 * n은 90보다 작거나 같은 자연수
 * n번째 피보나치 수 출력하기
 *
 * 피보나치 1문제에서 long형으로 변경하면 92번째까지 출력이 가능하다.
 * @author thsong
 *
 */
public class No0_Fibo2 {
	private static long[] memo;
	
	private static long fibo(int n) {
		
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
		
		int n = sc.nextInt(); // n은 90보다 작거나 같은 자연수
		memo = new long[n+1];
		System.out.println(fibo(n));
		
		sc.close();
	}

}
