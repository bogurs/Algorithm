package bogurs.algorithm.offline.dynamic;

import java.util.Scanner;

public class No2_TwoSquare_Tiling {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] d = new int[n+1];
		
		d[1] = 1;
		
		if (n > 1) {
			d[2] = 2;
			for (int i = 3; i < n+1; i++) {
				d[i] = d[i-1] + d[i-2];
				d[i] %= 10007;
			}
		}
		System.out.println(d[n]);
		
		sc.close();
	}
}
