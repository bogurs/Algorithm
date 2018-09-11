package bogurs.algorithm.prime;

import java.util.Scanner;

/**
 * 골드바흐의 추측
 * 
 * @author thsong
 *
 */
public class Prime_6588 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int m = 1000000;
		
		boolean[] check = new boolean[m + 1];
		check[0] = check[1] = check[2] = true;
		for (int i = 3; i * i <= m; i++) {
			if (check[i] == true) {
				continue;
			}
			for (int j = i + i; j <= m; j += i) {
				check[j] = true;
			}
		}
		
		while (sc.hasNextInt()) {
			int num = sc.nextInt();
			if (num == 0) {
				break;
			}
			
			for (int i = 3; i < num; i+=2) {
				if (!check[i]) { // 홀수 중 소수인 수
					if (!check[num-i]) { // N - [홀수소수] 값이 홀수소수이면 됨.
						System.out.println(num + " = " + i + " + " + (num-i));
						break;
					}
				}
			}
		}

		sc.close();
	}

}
