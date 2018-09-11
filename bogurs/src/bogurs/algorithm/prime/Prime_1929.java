package bogurs.algorithm.prime;

import java.util.Scanner;

/**
 * "에라토스테네스의 체" 방법을 이용해서 모든 소수를 구한다.
 * 
 * 1978문제와 같은 로직을 사용하여 문제를 제출하면 시간초과가 나온다.
 * 
 * @author thsong
 *
 */
public class Prime_1929 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		boolean[] check = new boolean[m + 1];
		check[0] = check[1] = true;
		for (int i = 2; i * i <= m; i++) {
			if (check[i] == true) {
				continue;
			}
			for (int j = i + i; j <= m; j += i) {
				check[j] = true;
			}
		}
		for (int i = n; i <= m; i++) {
			if (check[i] == false) {
				System.out.println(i);
			}
		}

		sc.close();
	}

}
