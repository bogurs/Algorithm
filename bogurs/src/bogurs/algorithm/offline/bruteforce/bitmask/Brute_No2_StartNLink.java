package bogurs.algorithm.offline.bruteforce.bitmask;

import java.util.Scanner;

/**
 * 스타트와 링크
 * 
 * n*n 행렬의 능력치 종합표가 주어졌을 때, n/2씩 팀을 나누었을 때 두 팀간의
 * 종합 능력치 차이가 최소가 되는 경우. 그때의 최솟값 출력.
 * 
 * n의 크기는 최대 20.
 * 10명씩 두 팀으로 짝을 짓는 경우의 수 = 20명 중 10명을 고르는 경우의 수
 * 2^10 = 1024
 * 이므로 모든 경우의 수를 다 해봐도 된다.
 * 
 * 브루트포스-비트마스크
 * 
 * @author thsong
 *
 */
public class Brute_No2_StartNLink {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // N(4 ≤ N ≤ 20, N은 짝수)
		int[][] t = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				t[i][j] = sc.nextInt();
			}
		}
		
		int res = Integer.MAX_VALUE;
		for (int i = ((1<<n/2)-1); i <= ((1<<n)-1)-((1<<n/2)-1); i++) {
			//if n=4, i=0011 ~ i=1100까지 선택하게 된다.
			//if 선택했을 때 절반까지 선택하지 못하는 경우 continue
			int cnt = 0;
			int sum_one = 0;
			int sum_two = 0;
			boolean[] c = new boolean[n];
			for (int j = 0; j < n; j++) {
				if ((i&(1<<j)) == (1<<j)) {
					cnt++;
					c[j] = true;
				}
			}
			if (cnt == n/2) {
				for (int x = 0; x < n; x++) {
					for (int y = 0; y < n; y++) {
						if (c[x] && c[y]) {
							sum_one += t[x][y];
						} else if (!c[x] && !c[y]){
							sum_two += t[x][y];
						}
					}
				}
				if (Math.abs(sum_one-sum_two) < res) {
					res = Math.abs(sum_one-sum_two);
				}
			}
		}
		System.out.println(res);
		
		sc.close();
	}

}
