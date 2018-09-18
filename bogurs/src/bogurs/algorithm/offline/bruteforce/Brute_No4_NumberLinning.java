package bogurs.algorithm.offline.bruteforce;

import java.util.Scanner;

/**
 * 수 이어 쓰기 1
 * 
 * n이 주어졌을 때 1~n까지 자연수를 이어 써서 새로 만들어지는 수의 자릿수 출력
 * 
 * n의 최대값은 10억이므로 전부 붙였을 때 1*9 + 2*90 + 3*900 + ... + 10억*9000000000 자리가 나온다.
 * 
 * @author thsong
 *
 */
public class Brute_No4_NumberLinning {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long n = sc.nextInt(); // 1≤N≤100,000,000 (10억)

		long result = 0;
		
		for (long i = 9, idx = 1; i < 9000000000L; i*=10, idx++) {
			if (n - i > 0) {
				n -= i;
				result += (idx * i);
			} else {
				result += (idx * n);
				break;
			}
		}
		System.out.println(result);
		
		sc.close();
	}

}
