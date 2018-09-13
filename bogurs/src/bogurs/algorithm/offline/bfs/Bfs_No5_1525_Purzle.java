package bogurs.algorithm.offline.bfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 퍼즐
 * 
 * 정점: 퍼즐의 모양 (3*3 행렬)
 * 간선: 현재 퍼즐의 모양에서 변형될 수 있는 퍼즐의 모양의 종류 (위/아래/왼/오 4가지)
 * 주어진 행렬이 정렬[[1,2,3][4,5,6][7,8,0]]된 모양이 되는 최소한의 변경 횟수
 * 즉, bfs
 * @author thsong
 *
 */
public class Bfs_No5_1525_Purzle {
	//(dx, dy) -> 오/왼/위/아래
	private static final int[] dx = {0, 0, 1, -1};
	private static final int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int input = 0;
		for (int i = 0; i < 9; i++) {
			int t = sc.nextInt();
			if (t == 0) {
				t = 9;
			}
			input = input * 10 + t;
		}
		
		Queue<Integer> q = new LinkedList<>();
		HashMap<Integer, Integer> d = new HashMap<>();
		d.put(input, 0);
		q.add(input);
		while (!q.isEmpty()) {
			int p = q.remove();
			String pS = p + "";
			int z = pS.indexOf('9'); // 일렬로 된 3*3배열에서 9의 index를 찾는다.
			int x = z/3; // 9의 위치를 행렬로 나타냈을 때의 x좌표를 찾는다.
			int y = z%3; // 9의 위치를 행렬로 나타냈을 때의 y좌표를 찾는다.
			for (int k = 0; k < 4; k++) { // 4방향으로 이동한 위치를 찾고 map에서 조회해서 거리를 저장한다.
				int nx = x + dx[k];
				int ny = y + dy[k];
				if (nx >= 0 && nx < 3 && ny >= 0 && ny < 3) {
					StringBuilder sb = new StringBuilder(pS);
					char temp = sb.charAt(x*3 + y);
					sb.setCharAt(x*3 + y, sb.charAt(nx*3 + ny));
					sb.setCharAt(nx * 3 + ny, temp);
					int num = Integer.parseInt(sb.toString());
					if (!d.containsKey(num)) {
						d.put(num, d.get(p) + 1);
						q.add(num);
					}
				}
			}
		}
		if (d.containsKey(123456789)) {
			System.out.println(d.get(123456789));
		} else {
			System.out.println("-1");
		}
		
		sc.close();
	}

}
