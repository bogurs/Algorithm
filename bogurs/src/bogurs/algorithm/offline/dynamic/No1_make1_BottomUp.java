package bogurs.algorithm.offline.dynamic;

import java.util.Scanner;

public class No1_make1_BottomUp {
	
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] d = new int[n+1];
        
        d[1] = 0;
        for (int i = 2; i < n+1; i++) {
        	d[i] = d[i-1] + 1; // -1 연산으로 구한 최소연산횟수
        	if (i%2 == 0 && (d[i/2] + 1) < d[i]) { // /2 연산으로 구한 최소연산횟수(-1 연산보다 더 작으면 교체한다)
        		d[i] = d[i/2] + 1;
        	}
        	if (i%3 == 0 && (d[i/3] + 1) < d[i]) { // /3 연산으로 구한 최소연산횟수(-1 연산보다 더 작으면 교체한다)
        		d[i] = d[i/3] + 1;
        	}
        }
        System.out.println(d[n]);
        
        sc.close();
    }

}
