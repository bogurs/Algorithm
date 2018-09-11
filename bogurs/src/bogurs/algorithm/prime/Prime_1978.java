package bogurs.algorithm.prime;

import java.util.Scanner;

/**
 * 소수: 약수가 1과 자기 자신 밖에 없는 수
 *  N이 소수가 되려면, 2보다 크거나 같고 N-1보다 작거나 같은 자연수로 나누어 떨어지면 안된다.
 *  
 *  시간복잡도: O(sqrtN)
 *  1978 문제의 경우는 주어지는 숫자의 수가 최대 100
 *  이므로 시간복잡도에서 100개의 수는 빠르게 처리할 수 있다.
 *  
 *  하지만, 1~백만까지의 모든 숫자가 소수인지 아닌지를 판단하는 것의
 *  시간복잡도는 O(N*sqrtN) = 10억 = 10초 이므로,
 *  "에라토스테네스의 체" 방법을 이용해서 소수를 구한다. (1929문제)
 * @author thsong
 *
 */
public class Prime_1978 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = sc.nextInt();
		}
		
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			boolean isPrime = true;
			if (nums[i] == 1) {
				continue;
			}
			for (int j = 2; j < nums[i]; j++) {
				if (nums[i] % j == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				cnt++;
			}
		}
		System.out.println(cnt);
		
		sc.close();
	}

}
