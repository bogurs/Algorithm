package bogurs.algorithm.offline.bruteforce;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 일곱 난쟁이
 * 
 * 9개의 난쟁이 키가 주어지는데 이 중 7개를 골라서 합이 100이 되면
 * 그 때의 난쟁이들의 키를 오름차순으로 출력하는 것.
 * 
 * 9개중 7개를 선택하는 경우의 수 = 9C7 = 9C2 = 9 * 8 / 2 = 36가지
 * 즉, 모든 경우를 해봐도 1초가 넘지 않는다.
 * 브루트 포스 다해보기 문제
 * 
 * @author thsong
 *
 */
public class Brute_No1_SevenMinions {
	
	private static boolean next_permutation(int[] a) {
		int i = a.length-1;
		while (i > 0 && a[i-1] >= a[i]) i--;
		
		if (i == 0) {
			return false;
		}
		
		int j = a.length-1;
		while (a[i-1] >= a[j]) j--;
		
		int temp = a[i-1];
		a[i-1] = a[j];
		a[j] = temp;
		
		for (int x = i, y = a.length-1; x < y; x++, y--) {
			temp = a[x];
			a[x] = a[y];
			a[y] = temp;
		}
		
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int MAX = 9;
		
		int[] n = new int[MAX];
		for (int i = 0; i < MAX; i++) {
			n[i] = sc.nextInt();
		}
		
		//1. 9개 중 7개만 고르는 경우
		int[] a = {0, 0, 1, 1, 1, 1, 1, 1, 1};
		do {
			int sum = 0;
			for (int i = 0; i < MAX; i++) {
				if (a[i] == 0) {
					continue;
				}
				sum += n[i];
			}
			if (sum == 100) {
				break;
			}
		} while (next_permutation(a));
		
		int[] result = new int[MAX-2];
		for (int i = 0, j = 0; i < MAX; i++) {
			if (a[i] == 0) {
				continue;
			}
			result[j] = n[i];
			j++;
		}
		Arrays.sort(result);
		
		for (int i = 0; i < MAX-2; i++) {
			System.out.println(result[i]);
		}
		sc.close();
	}

}
