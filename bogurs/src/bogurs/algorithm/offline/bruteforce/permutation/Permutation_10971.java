package bogurs.algorithm.offline.bruteforce.permutation;

import java.util.Scanner;

public class Permutation_10971 {
	
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
		int n = sc.nextInt(); // 도시의 수
		int[][] m = new int[n][n]; // 도시 격자
		for(int i = 0 ; i < n ; i++){
			for(int j = 0; j < n; j++) {
				m[i][j] = sc.nextInt();
			}
		}
		
		//m[0][1](10) > m[1][2](9)  > m[2][3](12) > m[3][0](8)
		//m[0][1](10) > m[1][3](10) > m[3][2](9)  > m[2][0](6)
		//m[0][2](15) > m[2][3](12) > m[3][1](8)  > m[1][0](5)
		//m[0][2](15) > m[2][1](13) > m[1][3](10) > m[3][0](8)
		//...
		
//		10
//		0 23 5 3 2 1 5 4 6 4
//		2 0 5 6 4 3 2 1 4 3
//		3 12 0 34 65 54 23 2 4 3
//		4 39 2 0 23 5 3 2 1 5
//		5 23 54 4 0 23 1 23 5 4
//		6 23 12 25 54 0 24 2 5 3
//		7 34 6 3 2 1 45 0 32 5 20
//		8 23 134 5 56 34 32 0 3 9
//		9 34 76 43 2 1 23 43 6 0 9
//		10 34 5 23 2 65 4 3 2 3 0
		
//		10
//		0 1000000 1000000 1000000 1000000 1000000 1000000 1000000 1000000 1000000
//		1000000 0 1000000 1000000 1000000 1000000 1000000 1000000 1000000 1000000
//		1000000 1000000 0 1000000 1000000 1000000 1000000 1000000 1000000 1000000
//		1000000 1000000 1000000 0 1000000 1000000 1000000 1000000 1000000 1000000
//		1000000 1000000 1000000 1000000 0 1000000 1000000 1000000 1000000 1000000
//		1000000 1000000 1000000 1000000 1000000 0 1000000 1000000 1000000 1000000
//		1000000 1000000 1000000 1000000 1000000 1000000 0 1000000 1000000 1000000
//		1000000 1000000 1000000 1000000 1000000 1000000 1000000 0 1000000 1000000
//		1000000 1000000 1000000 1000000 1000000 1000000 1000000 1000000 0 1000000
//		1000000 1000000 1000000 1000000 1000000 1000000 1000000 1000000 1000000 0
		
		int[] order = new int[n];
		for(int i = 0; i < n; i++) {
			order[i] = i;
		}
		
		int res = Integer.MAX_VALUE;
		int newRes;
		do{
			newRes = 0;
			boolean canGo = true;
			for(int i = 0; i < n-1; i++) { // order[0] -> order[1] ... order[n-2] -> order[n-1] 에 대한 이동 값을 구한다.
				if(m[order[i]][order[i+1]] == 0) { // 0이면 이동할 수 없는 경우이다.
					canGo = false; // 이경우는 결과를 계산할 수 없으므로 skip
					break;
				}
				newRes += m[order[i]][order[i+1]]; // 이동 경로 누적
			}
			if(canGo && m[order[n-1]][order[0]] != 0) { // 모두 갈 수 있고 마지막으로 원점으로 돌아오는 경로가 올 수 있다면,
				newRes += m[order[n-1]][order[0]]; // 원점으로 돌아오는 경로의 거리를 결과에 누적한다.
				if(newRes < res) { // 결과를 비교해서 작은 값을 택한다.
					res = newRes;
				}
			}
		}while(nextPermutation(order));
		
		System.out.println(res);
		
		sc.close();
	}
}