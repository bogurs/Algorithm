package bogurs.algorithm.offline.dynamic;

import java.util.Scanner;

/**
 * 이친수
 * 
 * 0과 1로만 이루어진 수를 이친수라고 한다.
 * 이친수는 0으로 시작하지 않는다.
 * 이친수에서는 1이 두 번 연속으로 나타나지 않는다. 즉, 11을 부분 문자열로 갖지 않는다.
 * 
 * 최대 90자리까지 모든 이친수를 만드려면 2^90 > 1억이므로
 * 브루트포스방식으로는 문제를 해결할 수 없다.
 * 
 * d[n] = n자리 이친수의 총 갯수
 * d[n-1] = n-1자리 이친수의 총 갯수
 * n-1다음으로 n번째 자리에 들어갈 수는 0 또는 1이지만 알 수 없다.
 * 따라서 변수를 i로 하나 더 둔다.
 * 
 * d[n][i] = n자리 이친수이면서 가장 마지막 자리가 i인 이친수의 총 개수
 * d[n-1][i] = n-1자리 이친수이면서 가장 마지막 자리가 i인 이친수의 총 개수
 * n-1번째 자리가 0이었다면 n번째는 1 또는 0이 올 수 있으며,
 * n-1번째 자리가 1이었다면 n번째는 0만 올 수 있다. (경우의 수가 나뉨)
 * 
 * 따라서 점화식은,
 * d[n][0] += d[n-1][k] (k = 0,1)
 * d[n][1] = d[n-1][0]
 * 
 * d[2][0] = 1, d[3][0] = 1, d[4][0] = 2, d[5][0] = 3,...
 * d[2][1] = 0, d[3][1] = 1, d[4][1] = 1, d[5][1] = 2,...
 * 
 * @author thsong
 *
 */
public class No8_PinaryNumber {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // N(1 ≤ N ≤ 90)
		long[] d = new long[n+1];
		d[1] = 1;
		d[2] = 1;
		
		for (int i = 3; i <= n; i++) {
			d[i] = d[i-1] + d[i-2];
		}
		System.out.println(d[n]);
		
		sc.close();
	}

}
