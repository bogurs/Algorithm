package bogurs.algorithm.offline.bruteforce.permutation;

import java.util.Scanner;

/**
 * 연산자 끼워 넣기
 * 
 * 2~11개 까지의 숫자가 주어지고,
 * 숫자들 사이에 (숫자갯수 -1) 만큼의 연산자를 끼워넣는다고 했을 때
 * 첫째줄에는 최대값을,
 * 둘째줄에는 최소값을 출력하는 문제
 * 
 * 연산자 조건에 따라서 계산하는 것이 아닌 앞에서부터 순서대로 연산한다.
 * 음수를 나눌때에는 양수로 전환한 후 나눈값에 정수부분을 음수로 변환하여 계산한다.
 * 
 * @author thsong
 *
 */
public class Brute_Permu_14888 {
	
	private static boolean next_permutation(int[] arr) {
		int i = arr.length-1;
		while (i > 0 && arr[i-1] >= arr[i]) i--;
		if (i <= 0) return false;
		
		int j = arr.length-1;
		while (arr[i-1] >= arr[j]) j--;
		
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
		
		int n = sc.nextInt(); // N(2 ≤ N ≤ 11)
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		
		int[] oper = new int[4];
		int operSize = 0;
		for (int i = 0; i < 4; i++) {
			oper[i] = sc.nextInt(); // 덧,뺄,곱,나 연산 횟수
			operSize += oper[i];
		}
		
		int[] operMask = new int[operSize];
		for (int i = 0, j = 0; i < 4; i++) {
			for (int k = 0; k < oper[i]; k++) {
				operMask[j] = i;
				j++;
			}
		}
		
		int max_res = Integer.MIN_VALUE;
		int min_res = Integer.MAX_VALUE;
		do {
			int now_res = a[0];
			for (int i = 0; i < operSize; i++) {
				if (operMask[i] == 0) { // 더하기
					now_res += a[i+1];
				} else if (operMask[i] == 1) { // 빼기
					now_res -= a[i+1];
				} else if (operMask[i] == 2) { // 곱하기
					now_res *= a[i+1];
				} else if (operMask[i] == 3) { // 나누기
					now_res /= a[i+1];
				}
			}
			if (now_res > max_res) max_res = now_res;
			if (now_res < min_res) min_res = now_res;
		} while (next_permutation(operMask));
		
		System.out.println(max_res);
		System.out.println(min_res);
		sc.close();
	}

}
