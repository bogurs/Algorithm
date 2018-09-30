package bogurs.algorithm.nhn;

import java.util.LinkedList;
import java.util.Scanner;

public class No1_2018 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int c = sc.nextInt(); // 10 <= C <= 1000, C는 정수
		int p = sc.nextInt(); // 0 <= P <= 1000, P는 정수
		int[] n = new int[p]; 
		for (int i = 0; i < p; i++) {
			n[i] = sc.nextInt(); // 1 <= N < ceil(C / 2), N은 정수 (ceil: 주어진 숫자보다 크거나 같은 숫자 중 가장 작은 정수)
		}
		
		LinkedList<Integer> d = new LinkedList<>();
		for (int i = 1; i <= c; i++) {
			//리스트 초기화
			d.add(i);
		}
		
		for (int i = 0; i < p; i++) {
			//2*n보다 남은 카드가 많은 경우 카드 섞기를 진행한다.
			int nc = c;
			while (nc > 2*n[i]) {
				for (int j = 0; j < n[i]; j++) { // 맨 앞부터 n까지 맨 뒤로 보낸다.
					int temp = d.removeFirst();
					d.add(nc-1, temp);
				}
				for (int j = nc - 2*n[i], k = nc - 2*n[i]; j < nc - n[i]; j++) {
					int temp = d.remove(k);
					d.add(nc-1, temp);
				}
				nc -= 2*n[i];
			}
		}
		
		for (int i = 0; i < 5; i++) {
			System.out.println(d.removeFirst());
		}
		
		sc.close();
	}

}
