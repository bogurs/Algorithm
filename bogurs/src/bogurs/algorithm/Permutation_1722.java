package bogurs.algorithm;

import java.util.Scanner;

public class Permutation_1722 {

	public static long getFact(long num) {
		long res = 1;
		for(int i = 1; i <= num; i++) {
			res *= i;
		}
		return res;
	}
	
	public static long getFactRecur(long num) {
		if(num == 1) {
			return 1;
		}
		return num * getFactRecur(num - 1);
	}
	
	public static void printOrderOfPermutation(int[] reqPermu, int n) {
		// 목표 숫자 reqPermu
		// 자리 수 n
		boolean[] checked = new boolean[n + 1];
		long tempSum = 0;
		for(int i = 0; i < n; i++) {
			int tempNum = 1;
			while(true) {
				if(reqPermu[i] == tempNum || (n-1) - i == 0) {
					checked[tempNum] = true;
					break;
				}
				if(!checked[tempNum]) {
					tempSum += getFact((n-1) - i);
				}
				tempNum++;
			}
		}
		System.out.println(++tempSum);
	}

	public static void printPermutation(long permuOrder, int n) {
		// 목표 순서 permuOrder
		// 자리 수 n
		boolean[] checked = new boolean[n + 1];
		long tempFac = 0;
		String resStr = "";
		for(int i = 0; i < n; i++) {
			int tempNum = 1;
			while(true) {
				if((n-1) - i == 0) {
					break;
				}
				
				tempFac = getFact((n-1) - i);
				if(!checked[tempNum]) {
					if(tempFac < permuOrder) {
						permuOrder -= tempFac;
					} else {
						resStr = resStr.concat(tempNum + " ");
						checked[tempNum] = true;
						break;
					}
				}
				
				
				tempNum++;
			}
		}
		
		//마지막 남은 숫자 붙이기
		int tempInt = 0;
		for(int j = 1; j < checked.length; j++) {
			if(checked[j]) {
				continue;
			} else {
				tempInt = j;
				break;
			}
		}
		resStr = resStr.concat(tempInt + " ");
		System.out.println(resStr);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int questNum = sc.nextInt();
		long permuOrder;
		int[] reqPermu = new int[n];
		if(questNum == 1) {
			permuOrder = sc.nextLong();
			printPermutation(permuOrder, n);
		} else if(questNum == 2) {
			for(int i = 0; i < n; i++) {
				reqPermu[i] = sc.nextInt();
			}
			printOrderOfPermutation(reqPermu, n);
		}
		
		sc.close();
	}

}