package bogurs.algorithm.offline.dynamic;

import java.util.Scanner;

/**
 * 계단 오르기
 * 
 * 규칙
 * 1. 계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 
 *    즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
 * 2. 연속된 세 개의 계단을 모두 밟아서는 안 된다. 단, 시작점은 계단에 포함되지 않는다.
 * 3. 마지막 도착 계단은 반드시 밟아야 한다.
 * 
 * d[n][0] = 연속으로 0번 계단을 밟았을 때 최대 계단수
 * d[n][1] = 연속으로 1번 계단을 밟았을 때 최대 계단수
 * 
 * d[1][0] = a[1]
 * d[1][1] = a[1]
 * d[2][0] = a[2]
 * d[2][1] = d[1][0] + a[2]
 * ...
 * 
 * 
 * @author thsong
 *
 */
public class No17_StairsClimb {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); //계단의 개수는 300이하의 자연수
		int[] a = new int[n+1]; // 10,000이하의 자연수
		for (int i = 1; i <= n; i++) {
			a[i] = sc.nextInt();
		}
		
		int[][] d = new int[n+1][2];
		d[1][0] = a[1];
		d[1][1] = a[1];
		if (n >= 2) {
			d[2][0] = a[2];
			d[2][1] = d[1][0] + a[2];
		}
		
		for (int i = 3; i <= n; i++) {
			d[i][0] = a[i] + Math.max(d[i-2][0], d[i-2][1]);
			d[i][1] = a[i] + d[i-1][0];
		}
		System.out.println(Math.max(d[n][0], d[n][1]));
		
		sc.close();
	}

}
