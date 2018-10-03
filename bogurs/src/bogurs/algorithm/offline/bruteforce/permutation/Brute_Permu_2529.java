package bogurs.algorithm.offline.bruteforce.permutation;

import java.util.Scanner;

/**
 * 부등호
 * 
 * 2~9개까지 부등호의 종류가 주어지면,
 * 0~9까지 10개의 숫자를 중복없이 부등호 사이에 배치해서
 * 부등호 식을 만족하는 숫자 배열을 하나의 정수로 만들었을 때
 * 가장 작은값과 큰 값을 고르는 문제
 * 
 * 맨 앞자리가 0이되는 정수도 출력(e.g. 021)
 * 
 * 최대 주어지는 부등호의 개수 9개.
 * 부등호 앞뒤로 배치할 수 있는 숫자 10개 즉 최대 배열할 수 있는 경우의 수는
 * 10!이다.
 * 10! = 3628800
 * 이므로 모든 경우의 수를 다 해봐도 되는 부르트 포스 문제이다.
 * 
 * 또한 순열에 대한 결과이므로 부르트포스-순열 문제이다.
 * 
 * @author thsong
 *
 */
public class Brute_Permu_2529 {
	static final int MAX = 10;
	
	private static boolean next_permutation(int[] arr) {
		int i = arr.length-1;
		while (i > 0 && arr[i-1] > arr[i]) i--;
		
		if (i <= 0) return false;
		
		int j = arr.length-1;
		while (arr[i-1] > arr[j]) j--;
		
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		
		for (int x = i, y = arr.length-1; x < y; x++, y--) {
			temp = arr[x];
			arr[x] = arr[y];
			arr[y] = temp;
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int k = sc.nextInt(); //2<=k<=9
		String[] a = new String[k];
		for (int i = 0; i < k; i++) {
			a[i] = sc.next();
		}
		
		int[] nums = new int[MAX]; // 부등호 식에 사용될 순열 숫자
		for (int i = 0; i < MAX; i++) {
			nums[i] = i;
		}
		
		Long max_res = 0L;
		String max_res_str = null;
		Long min_res = Long.MAX_VALUE;
		String min_res_str = null;
		do {
			StringBuilder sb = new StringBuilder();
			int i;
			for (i = 0; i < k; i++) {
				if (a[i].equals("<")) { // '<' 부등호의 경우
					if (nums[i] > nums[i+1]) { // 부등호의 결과와 일치하지 않음
						break;
					}
					sb.append(nums[i]);
				} else if (a[i].equals(">")) { // '>' 부등호의 경우
					if (nums[i] < nums[i+1]) { // 부등호의 결과와 일치하지 않음
						break;
					}
					sb.append(nums[i]);
				}
			}
			sb.append(nums[i]);
			if (sb.length() == (k+1) && Long.parseLong(sb.toString()) < min_res) {
				min_res = Long.parseLong(sb.toString());
				min_res_str = sb.toString();
			}
			if (sb.length() == (k+1) && Long.parseLong(sb.toString()) > max_res) {
				max_res = Long.parseLong(sb.toString());
				max_res_str = sb.toString();
			}
		} while (next_permutation(nums));
		
		System.out.println(max_res_str);
		System.out.println(min_res_str);
		
		sc.close();
	}

}
