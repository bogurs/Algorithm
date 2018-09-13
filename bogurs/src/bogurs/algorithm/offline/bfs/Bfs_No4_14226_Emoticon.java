package bogurs.algorithm.offline.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 이모티콘
 * 
 * 모든 연산은 1초, 3가지 연산만 존재
 * 최소한의 시간을 구하는 문제이므로 bfs문제이다.
 * 
 * 일반적인 bfs문제는 정점을 변수하나로 확실하게 만들수 있는경우이다.
 * 하지만 이모티콘 문제는 알지못하는 미지수가 하나 더 발생하게 된다.
 * 
 * x는 현재 이모티콘의 길이
 * 1. 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장 [x, c] -> [x, x]
 * 2. 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 [x, c] -> [x+c, c]
 * 3. 화면에 있는 이모티콘 중 하나를 삭제 [x, c] -> [x-1, c]
 * 
 * @author thsong
 *
 */
public class Bfs_No4_14226_Emoticon {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int s = sc.nextInt(); // S (2 ≤ S ≤ 1000)
		
		int[][] d = new int[s+1][s+1];
		for (int i = 0; i <= s; i++) { // d배열을 방문 목적으로도 사용하기 위함
			Arrays.fill(d[i], -1);
		}
		
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		q.add(0);
		d[1][0] = 0;
		while (!q.isEmpty()) {
			int newS = q.remove();
			int newC = q.remove();
			
			if (d[newS][newS] == -1) {
				d[newS][newS] = d[newS][newC] + 1;
				q.add(newS);
				q.add(newS);
			}
			if (newS+newC <= s) {
				if (d[newS+newC][newC] == -1) {
					d[newS+newC][newC] = d[newS][newC] + 1;
					q.add(newS+newC);
					q.add(newC);
				}
			}
			if (newS-1 >= 0) {
				if (d[newS-1][newC] == -1) {
					d[newS-1][newC] = d[newS][newC] + 1;
					q.add(newS-1);
					q.add(newC);
				}
			}
		}
		int answer = -1;
		for (int i = 0; i <= s; i++) {
			int newAnswer = d[s][i];
			if (newAnswer != -1) {
				if (answer == -1 || newAnswer < answer) {
					answer = newAnswer;
				}
			}
		}
		System.out.println(answer);
		
		sc.close();
	}

}
