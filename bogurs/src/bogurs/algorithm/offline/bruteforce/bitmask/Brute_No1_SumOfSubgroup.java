package bogurs.algorithm.offline.bruteforce.bitmask;

import java.util.Scanner;

/**
 * 부분집합의 합
 * 
 * n과 s그리고 n개 만큼의 정수가 주어졌을 때 n크기의 정수 배열 중 부분집합의 합이 s가 되는 경우의 수
 * 
 * n은 최대 20, 부분집합의 개수는 2^20 - 1(공집합 제외)으로 100만정도이기 때문에
 * 부르트 포스 문제로 해결할 수 있다.
 * @author thsong
 *
 */
public class Brute_No1_SumOfSubgroup {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); //1≤N≤20
		int s = sc.nextInt(); //|S|≤1,000,000
		int res = 0;
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = sc.nextInt(); //|num|≤100,000, 중복 허용
		}
		
		for (int i = 1; i <= (1<<n)-1; i++) { // 전체 부분집합의 개수는 (2^n), 공집합은 제외하므로 1부터 시작한다.
			int sum = 0;
			for (int j = 0; j < n; j++) {
				if ((i&(1<<j)) == (1<<j)) { // 포함되어 있는지 확인
					sum += nums[j];
				}
			}
			if (sum == s) {
				res++;
			}
		}
		
		System.out.println(res);
		sc.close();
	}

}
