package bogurs.algorithm.offline.bruteforce.bitmask;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 종이조각
 * 
 * n*m 크기의 종이를 일자로 적당히 잘라서 만들수 있는 수들의 최대합
 * max n=4, m=4이므로 최대 종이를 자르는 경우의 수는
 * 2^16 = 65536 이므로, 전체를 해봐도 된다.
 * 
 * 브루트 포스-비트마스크
 * 
 * n과 m중 가장 큰 값에 해당하는 행이나 열에서 n자리 수 또는 m자리 수 숫자를
 * 선택했을 때 가장 큰 값을 찾아야 한다.
 * 그 다음은 n-1자리 또는 m-1자리 숫자 중 가장 큰 값을 선택해야 한다.
 * ...
 * 1자리까지 왔을 때는 남은 숫자를 모두 더한다.
 * 
 * @author thsong
 *
 */
public class Brute_No3_Paper {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // (1 ≤ N, M ≤ 4)
		int m = sc.nextInt(); // (1 ≤ N, M ≤ 4)
		int[][] a = new int[n][m];
		for (int i = 0; i < n; i++) {
			String temp = sc.next();
			for (int j = 0; j < m; j++) {
				a[i][j] = Integer.parseInt(temp.substring(j, j+1)); // 0부터 9까지 중 하나
			}
		}
		
		int[][] mask = new int[n][m]; // 세로로 읽을 것인지 가로로 읽을 것인지 결정하기 위한 mask, 0은 가로, 1은 세로
		ArrayList<Integer> li;
		int res = 0;
		for (int i = 0; i <= ((1<<n*m)-1); i++) {
			li = new ArrayList<>();
			// if n=2, m=3, 000000 ~ 111111까지 경우의 수를 진행한다.
			for (int k = 0; k < n*m; k++) {
				if ((i&(1<<k)) == (1<<k)) {
					mask[k/m][k%m] = 1;
				}
			}
			
			//로직 수행
			//가로 먼저 합한다.
			int newRes = 0;
			for (int x = 0; x < n; x++) {
				StringBuilder temp = new StringBuilder();
				for (int y = 0; y < m; y++) {
					if (mask[x][y] == 0) {
						temp.append(a[x][y]);
					} else {
						if (!temp.toString().equals("")) {
							li.add(Integer.parseInt(temp.toString()));
							temp = new StringBuilder();
						}
					}
				}
				if (!temp.toString().equals("")) {
					li.add(Integer.parseInt(temp.toString()));
				}
			}
			for (int y = 0; y < m; y++) {
				StringBuilder temp = new StringBuilder();
				for (int x = 0; x < n; x++) {
					if (mask[x][y] == 1) {
						temp.append(a[x][y]);
					} else {
						if (!temp.toString().equals("")) {
							li.add(Integer.parseInt(temp.toString()));
							temp = new StringBuilder();
						}
					}
				}
				if (!temp.toString().equals("")) {
					li.add(Integer.parseInt(temp.toString()));
				}
			}
			
			//ArrayList의 숫자를 모두 합하여 최댓값인지 확인하기
			for (int iter : li) {
				newRes += iter;
			}
			if (newRes > res) {
				res = newRes;
			}
			
			//mask 0 및 c를 false으로 초기화 (재사용을 위함)
			for (int x = 0; x < n; x++) {
				for (int y = 0; y < m; y++) {
					mask[x][y] = 0;
				}
			}
		}
		System.out.println(res);
		
		sc.close();
	}

}
