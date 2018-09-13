package bogurs.algorithm.offline.dynamic;

import java.util.Scanner;

/**
 * d[i] = 붕어빵 i개를 팔아서 얻을 수 있는 최대 수익
 * p[i] = 붕어빵 i개씩 팔때 최대 이익
 * 
 * 가능한 경우 생각하기 d[i]의 모든 경우 (i개 나온다)
 * d[i] = 붕어빵 1개를 p[1]에 팔고 남은 붕어빵의 개수 => p[1] + d[i-1]
 * d[2] = 붕어빵 2개를 p[2]에 팔고 남은 붕어빵의 개수 => p[2] + d[i-2]
 * ...
 * d[i-1] = 붕어빵 i-1개를 p[i-1]에 팔고 남은 붕어빵의 개수 => p[i-1] + d[1]
 * d[i] = 붕어빵 i개를 p[i]에 팔고 남은 붕어빵의 개수 => p[i] + d[0]
 * 
 * 즉,
 * d[i] = max(p[j] + d[i-j]) (1<= j <=i)
 * @author thsong
 *
 */
public class No5_FishBread {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); //붕어빵 개수(1 ≤ N ≤ 1,000)
		int[] p = new int[n+1]; //팔때 가격(1 ≤ Pi ≤ 10,000)
		for (int i = 1; i <= n; i++) {
			p[i] = sc.nextInt();
		}
		
		int[] d = new int[n+1]; // 붕어빵을 팔아서 얻을 수 있는 최대 이익
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= i; j++) {
				if (d[i] < d[i-j] + p[j]) {
					d[i] = d[i-j] + p[j];
				}
			}
		}
		
		System.out.println(d[n]);
		
		sc.close();
	}
}
