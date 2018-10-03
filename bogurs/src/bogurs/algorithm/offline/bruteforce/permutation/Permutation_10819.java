package bogurs.algorithm.offline.bruteforce.permutation;

import java.util.Arrays;
import java.util.Scanner;

public class Permutation_10819 {
	
	public static int calSubstring(int[] m) {
		int res = 0;
		for(int i = 0 ; i < m.length-1 ; i++){
			res += Math.abs(m[i]-m[i+1]);
		}
		return res;
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
		int n = sc.nextInt();
		int[] m = new int[n];
		for(int i = 0 ; i < n ; i++){
			m[i] = sc.nextInt();
		}
		
		Arrays.sort(m);
		
		int res = 0;
		int newRes = 0;
		do{
			newRes = calSubstring(m);
			if(res < newRes) res = newRes;
		}while(nextPermutation(m));
		
		System.out.println(res);
		
		sc.close();
	}
}