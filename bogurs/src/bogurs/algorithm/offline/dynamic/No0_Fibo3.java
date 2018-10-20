package bogurs.algorithm.offline.dynamic;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 피보나치 3
 * 
 * n은 1000000000000000000보다 작거나 같은 자연수
 * n번째 피보나치 수%1000000 구하기
 * 
 * fibo[n]%1000000 = (fibo[n-1]%1000000 + fibo[n-2]%1000000) % 1000000
 * 
 * 주기 = 15*10^(k-1) = 1500000 (k=6)
 * @author thsong
 *
 */
public class No0_Fibo3 {
	private final static int mod = 1000000; // 10^6
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long n = sc.nextLong() % 1500000L;
		ArrayList<Long> li = new ArrayList<>();
		li.add(0L);
		li.add(1L);
		if (n >= 2) {
			li.add(1L);
		}
		for (int i = 3; i <= n; i++) {
			long temp = (li.get((i-1)%3)%mod + li.get((i-2)%3)%mod)%mod;
			li.remove(i%3);
			li.add(i%3, temp);
		}
		
		System.out.println(li.get((int) (n%3)));
		
		sc.close();
	}

}
