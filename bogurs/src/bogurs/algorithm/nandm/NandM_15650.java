package bogurs.algorithm.nandm;

import java.util.Scanner;

/**
 * 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 * 
 * * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열(오름차순만)
 * 
 * 브루트포스는 문제의 조건에 따라 재귀함수 점화식을 세운다.
 * 
 * @author thsong
 *
 */
public class NandM_15650 {
	static int[] arr = new int[10];
	static boolean[] c = new boolean[10];
	
	private static void go(int index, int start, int n, int m) {
		//빠져나가는 조건
//		System.out.print("go(" + index + ", " + n + ", " + m + ")=> ");
		if (index == m) {
			for (int i = 0; i < m; i++) {
				System.out.print(arr[i]);
				if (i < m-1) {
					System.out.print(" ");
				}
			}
			System.out.println();
			return;
		}
		
		//출력하는 조건에 따라 출력 및 함수 재호출
		for (int i = start; i <= n; i++) {
			if (c[i]) continue; // 방문했는지 여부: 중복제거
			c[i] = true; // 방문 true
			arr[index] = i; // 방문 안한 요소 배열에 저장
			go(index+1, i+1, n, m); // 다음 인덱스를 찾기위해 재귀호출
			c[i] = false; // 출력이 모두 끝난 후 방문 false
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		go(0, 1, n, m);
		
		sc.close();
	}

}
