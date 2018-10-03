package bogurs.algorithm.offline.bruteforce.permutation;

import java.util.Scanner;

public class All_Next_Permutation {
	
	public static boolean next_permutation(int[] values) {
		int i = 0;
		int j = 0;
		// 1. values[i-1]<values[i] 를 만족하는 가장 큰 i을 찾는다.
		i = values.length - 1;
		while (i > 0 && values[i - 1] >= values[i]) {
			i--;
		}

		// 마지막순열인 경우 -1을 출력한다.
		if (i <= 0) {
			return false;
		}

		// 2. j>=i 이면서 values[j]>values[i-1] 를 만족하는 가장 큰 j를 찾는다.
		j = values.length - 1;
		while (values[j] <= values[i - 1]) {
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
		StringBuilder sb = new StringBuilder();
		boolean flag= true;
		
		for (int k = 0; k < values.length; k++) {
			values[k] = k+1; // 가장 처음의 순열 담기
		}
		
		do{
			if (flag) {
				for (int y = 0; y < values.length; y++) {
					sb.append(values[y]);
					if (y == values.length - 1){
						sb.append("\n");
						break;
					}
					sb.append(" ");
				}
			} else {
				System.out.println(-1);
			}
		}while(flag=next_permutation(values));
		
		System.out.println(sb);
		sc.close();
	}

}