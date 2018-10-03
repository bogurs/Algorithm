package bogurs.algorithm.offline.bruteforce.permutation;

/**
 * 이전 순열 구하기
 * @author thsong
 *
 */
import java.util.Scanner;

public class Prev_Permutation {
	
	public static boolean prev_permutation(int[] values) {
		int i = 0;
		int j = 0;
		// 1. values[i-1]>values[i] 를 만족하는 가장 큰 i을 찾는다.
		i = values.length - 1;
		while (i > 0 && values[i - 1] <= values[i]) {
			i--;
		}

		// 첫번째순열인 경우 -1을 출력한다.
		if (i <= 0) {
			return false;
		}

		// 2. j<=i 이면서 values[j]<values[i-1] 를 만족하는 가장 큰 j를 찾는다.
		j = values.length - 1;
		while (values[j] >= values[i - 1]) {
			j--;
		}

		// 3. values[i-1]과 values[j]를 swap 한다.
		int temp = values[i - 1];
		values[i - 1] = values[j];
		values[j] = temp;
		temp = 0;

		// 4. values[i] 부터 순열을 뒤집는다.
		for (int z = i, m = values.length - 1; z < m; z++, m--) {
			temp = values[z];
			values[z] = values[m];
			values[m] = temp;
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] values = new int[n];

		for (int k = 0; k < values.length; k++) {
			values[k] = sc.nextInt();
		}

		if (prev_permutation(values)) {
			for (int y = 0; y < values.length; y++) {
				System.out.print(values[y] + " ");
			}
		} else {
			System.out.println(-1);
		}

		sc.close();
	}

}