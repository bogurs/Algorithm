package bogurs.algorithm.offline.bruteforce.permutation;

import java.util.Scanner;

public class Permutation_6603 {
	
	public static int getFactRecur(int num) {
		if(num == 1) {
			return 1;
		}
		return num * getFactRecur(num - 1);
	}
	
	public static boolean nextPermutation(int[] a){
		//1. A[i-1]<A[i] 를 만족하는 최대 i 값을 찾는다.
		int i = a.length-1;
		while(i > 0 && a[i - 1] >= a[i]) {
			i--;
		}
		
		if(i <= 0) {
			return false;
		}
		
		//2. A[j]>A[i-1] 를 만족하는 최대 j 값을 찾는다.
		int j = a.length-1;
		while(a[j] <= a[i-1]) {
			j--;
		}
		
		//3. A[j]와 A[i-1] 를 swap
		int temp = a[j];
		a[j] = a[i-1];
		a[i-1] = temp;
		
		//4. A[i] 부터 끝까지 수를 뒤집는다.
		for(int m=i, n=a.length-1 ; m < n ; m++, n--){
			temp = a[m];
			a[m] = a[n];
			a[n] = temp;
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		StringBuilder res = new StringBuilder();
		while(sc.hasNextLine()) {
			String nextLine = sc.nextLine();
			if("0".equals(nextLine)) {
				break;
			}
			
			// 테스트 숫자 초기화
			String[] tempLineSplit = nextLine.split(" ");
			int k = Integer.parseInt(tempLineSplit[0]); // k (6 < k < 13)
			int[] inputArr = new int[k];
			int[] outputMask = new int[k];
			for(int i = 0; i < k; i++) { // 다음 k개 수는 집합 S에 포함되는 수
				inputArr[i] = Integer.parseInt(tempLineSplit[i+1]);
			}
			for(int i = 0; i < k; i++) { // 출력 마스크 만들기
				if((k-i) > 6) {
					outputMask[i] = 0;
				} else {
					outputMask[i] = 1;
				}
			}
			
			int j = 1;
			int tempSize = getFactRecur(k) / (getFactRecur(6) * getFactRecur(k-6));
			String[] tempStrArr = new String[tempSize];
			do{
				String tempStr = "";
				for(int i = 0; i < k; i++) {
					if(outputMask[i] == 1) {
						tempStr = tempStr.concat(inputArr[i] + " ");
					}
				}
				tempStrArr[tempSize-j] = tempStr;
				j++;
			}while(nextPermutation(outputMask));
			
			for(int i = 0; i < tempSize; i++) {
				res.append(tempStrArr[i] + "\n");
			}
			
			res.append("\n"); // 각 테스트 케이스 사이에는 빈 줄을 하나 출력
		}
		
		System.out.println(res);
		
		sc.close();
	}
}