package bogurs.algorithm.offline.bruteforce;

import java.util.Scanner;

/**
 * 테트로미노
 * 
 * 테트로미노의 종류 5가지
 * 기억자 모형은 8가지 형태를 가질 수 있다.
 * 지그재그 모형과 반 십자 모형은 4가지 형태를 가질 수 있다.
 * 일자모형은 2가지 형태를 가질 수 있다.
 * 전체 테트로미노의 형태 8*1 + 4*2 + 2*1 + 1 = 19가지 모형
 * 19가지 모형을 종이 1*1에 모두 놓을 수 있다고 가정했을 때
 * 최대 경우의 수는 19*N*M = 19*500*500 = 4750000 이다.
 * 모든 경우를 다 해봐도 1억을 넘지 않으므로 다 해봐도 된다.
 * 
 * 하나만 사용해서 종이에 테트로미노를 놓았을 때 겹쳐지는 숫자의 최대 합구하기 브루트 포스 다해보기 문제
 * 
 * @author thsong
 *
 */
public class Brute_No2_Tetromino {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		final int MAX = 19;
		final int[] tet_x = { 
				0, 0, 0, 0, // ㅡ
				0, 1, 2, 3, // |
				0, 0, 1, 1, // ㅁ
				0, 1, 2, 2, // 세로로 긴 ㄴ
				0, 1, 1, 1, // 가로로 긴 ㄴ
				0, 0, 1, 2, // 세로로 긴 ㄱ
				0, 0, 0, 1, // 가로로 긴 ㄱ
				0, 1, 2, 2, // 세로로 긴 반대ㄴ
				0, 1, 1, -1, // 가로로 긴 반대ㄴ
				0, 1, 0, 2, // 세로로 긴 반대ㄱ
				0, 0, 0, 1, // 가로로 긴 반대ㄱ
				0, 1, 1, 2, // 아래/오 지그재그
				0, 0, 1, 1, // 오/아래 지그재그
				0, 1, 1, 2, // 아래/왼 지그재그
				0, 0, 1, 1, // 왼/아래 지그재그
				0, 0, 0, 1, // 아래튀 반십자
				0, 0, 0, -1, // 위튀 반십자
				0, 1, 1, 2, // 오른튀 반십자
				0, 1, 1, 2 };// 왼튀 반십자
		
		final int[] tet_y = { 
				0, 1, 2, 3, // ㅡ
				0, 0, 0, 0, // |
				0, 1, 0, 1, // ㅁ
				0, 0, 0, 1, // 세로로 긴 ㄴ
				0, 0, 1, 2, // 가로로 긴 ㄴ
				0, 1, 1, 1, // 세로로 긴 ㄱ
				0, 1, 2, 2, // 가로로 긴 ㄱ
				0, 0, 0, -1, // 세로로 긴 반대ㄴ
				0, 0, 1, 2, // 가로로 긴 반대ㄴ
				0, 0, 1, 0, // 세로로 긴 반대ㄱ
				0, 1, 2, 0, // 가로로 긴 반대ㄱ
				0, 0, 1, 1, // 아래/오 지그재그
				0, 1, 1, 2, // 오/아래 지그재그
				0, 0, -1, -1, // 아래/왼 지그재그
				0, -1, -1, -2, // 왼/아래 지그재그
				0, 1, 2, 1, // 아래튀 반십자
				0, 1, 2, 1, // 위튀 반십자
				0, 0, 1, 0, // 오른튀 반십자
				0, 0, -1, 0 };// 왼튀 반십자

		int n = sc.nextInt(); // (4 ≤ N, M ≤ 500)
		int m = sc.nextInt();
		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		int sum = 0;
		for (int k = 0; k < MAX * 4; k += 4) {
			for (int x = 0; x < n; x++) {
				for (int y = 0; y < m; y++) {
					int newSum = 0;
					int nx1 = x + tet_x[k];
					int nx2 = x + tet_x[k + 1];
					int nx3 = x + tet_x[k + 2];
					int nx4 = x + tet_x[k + 3];
					int ny1 = y + tet_y[k];
					int ny2 = y + tet_y[k + 1];
					int ny3 = y + tet_y[k + 2];
					int ny4 = y + tet_y[k + 3];
					if (nx4 >= 0 && nx4 < n && ny4 >= 0 && ny4 < m
							&& nx3 >= 0 && nx3 < n && ny3 >= 0 && ny3 < m) {
						newSum += map[nx1][ny1] + map[nx2][ny2] + map[nx3][ny3] + map[nx4][ny4];
					}
					if (newSum > sum) {
						sum = newSum;
					}
				}
			}
		}

		System.out.println(sum);
		sc.close();
	}

}
