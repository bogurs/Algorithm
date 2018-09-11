package bogurs.algorithm.nandm;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 * 
 * * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열 (N개의 숫자가 주어진다) (오름차순)
 * 
 * 브루트포스는 문제의 조건에 따라 재귀함수 점화식을 세운다.
 * 
 * @author thsong
 *
 */
public class NandM_15655 {
	static int[] myArr;
	static int[] arr = new int[10];
	static boolean[] c = new boolean[10];
	
	private static void go(int index, int start, int n, int m) {
		//빠져나가는 조건
//		System.out.print("go(" + index + ", " + n + ", " + m + ")=> ");
		if (index == m) {
			for (int i = 0; i < m; i++) {
				System.out.print(myArr[arr[i]-1]);
				if (i < m-1) {
					System.out.print(" ");
				}
			}
			System.out.println();
			return;
		}
		
		//출력하는 조건에 따라 출력 및 함수 재호출
		for (int i = start; i <= n; i++) {
			if (c[i]) continue;
			c[i] = true;
			arr[index] = i;
			go(index+1, i+1, n, m);
			c[i] = false;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		myArr = new int[n];
		for (int i = 0; i < n; i++) {
			myArr[i] = sc.nextInt();
		}
		Arrays.sort(myArr);
		go(0, 1, n, m);
		
		sc.close();
	}

}
