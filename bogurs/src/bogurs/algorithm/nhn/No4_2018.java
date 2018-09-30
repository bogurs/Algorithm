package bogurs.algorithm.nhn;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair{
	int x;
	int y;
	String a;
	public Pair(int x, int y, String a) {
		this.x = x;
		this.y = y;
		this.a = a;
	}
}

/**
 * 4. 점령지 확장
  문제의 입력값은 각 언어의 표준입력(stdin) 함수를, 출력값은 표준출력(stdout) 함수를 사용해주세요.
게임개발자인 ‘엔터’는 여러 국가가 땅을 점령하는 보드게임을 만들려고 합니다.
N * N 크기의 땅에 여러 국가가 있고, 각 국가는 매 턴(turn)마다 아래의 규칙에 따라 비어 있는 땅을 점령하여 영토를 확장합니다.
더 이상 점령 가능한 땅이 없을 때까지 진행이 되며, 그 때까지의 턴 수와 최종적인 땅의 형태를 출력합니다.



[정의]

국가는 알파벳 대문자 한 글자로 표현합니다.
빈 땅은 숫자로 표현하고, 장애물이 없을 경우 0으로 표현합니다.
장애물이 존재하는 땅은 1 이상의 숫자로 표현합니다.
장애물이 위치하는 방향에 따라 상(1), 하(2), 좌(4), 우(8)로 표현합니다
두 개 이상의 방향에 장애물이 있는 경우, 조합하여 표현합니다. (예를 들어 하측, 좌측에 장애물이 동시에 있을 경우 2+4 => 6 으로 표현)
각 국가가 최초에 위치한 영토에는 장애물이 존재하지 않습니다.
장애물이 있는 땅을 국가가 점령하여도 장애물은 사라지지 않습니다.


[확장 규칙]

매 턴마다 각 국가는 모든 영토에서 상하좌우로 1칸씩 동시에 확장합니다.
모든 땅에는 상하좌우에 장애물이 존재할 수 있고, 장애물이 존재하는 방향으로는 확장이 불가능합니다.
어떤 땅을 여러 나라가 동시에 점령하고자 한다면, 그 땅에 인접한 영토가 많은 국가가 점령합니다.
어떤 땅에 인접한 국가들의 영토 수가 동일하다면 어느 국가도 점령할 수 없습니다.

위와 같은 상황일 경우 빨간색 테두리 칸에 인접한 A와 B 국가의 영토의 수가 2개씩 동일하므로 점령할 수 없습니다.
파란색 테두리 칸에 인접한 A와 B 국가의 영토의 수가 A는 1개, B는 2개이므로 다음 턴에 B가 점령합니다.




위와 같은 상황에서 노란색 테두리 칸의 좌측에 장애물이 존재하고, 초록색 테두리 칸의 상측에 장애물이 존재하여 A국가는 영토를 확장할 수 없습니다.



[입력]

첫 번째 줄은 정사각형으로 표현된 땅의 크기 N이 입력됩니다.
N * N의 N값, 1 <= N <= 50
두 번째 줄부터는 공백문자로 구분된 땅의 최초 형태가 입력됩니다.
각 줄은 개행문자(newline, \n)로 구분되어 있습니다.


[출력]

더 이상 점령 가능한 땅이 없을 때까지 진행이 되며, 그 때까지의 턴 수와 최종적인 땅의 형태를 출력합니다
각 국가가 모든 영토를 확장했을 때, 진행된 턴 수와 영토의 구조를 출력합니다.
한 줄의 끝은 불필요한 공백 없이 개행문자(newline, \n)로 끝나야 합니다.


[예시]

입력
5
A 0 0 0 0
0 0 6 0 D
0 0 B 0 0
0 C 8 0 0
0 0 0 0 0
확장 과정



출력

3
A A A D D
A A D D D
0 0 B B D
C C C B D
C C C 0 D

입/출력 예시
⋇ 입출력 형식을 잘 지켜주세요.
␣ : 공백
↵ : 줄바꿈
−⇥ : 탭
보기 입력 1
5
A 0 0 0 0
0 0 6 0 D
0 0 B 0 0
0 C 8 0 0
0 0 0 0 0
출력 1
3
A A A D D
A A D D D
0 0 B B D
C C C B D
C C C 0 D

보기 입력 2
5
A 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 B
출력 2
3
A A A A 0
A A A 0 B
A A 0 B B
A 0 B B B
0 B B B B

보기 입력 3
5
A 0 0 0 10
0 0 0 10 0
0 0 10 0 0
0 10 0 0 0
10 0 0 0 0
출력 3
4
A A A A A
A A A A 0
A A A 0 0
A A 0 0 0
A 0 0 0 0

 * @author thsong
 *
 */
class No4_2018 {
	static final int[] dx = {1, -1, 0, 0};
	static final int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		/**
		 * 5
A 0 0 0 0
0 0 6 0 D
0 0 B 0 0
0 C 8 0 0
0 0 0 0 0
5
A 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 B

		 */
		int n = sc.nextInt(); // N값, 1 <= N <= 50
		String[][] a = new String[n][n];
		String[][] na = new String[n][n];
		int[][] nation = new int[n][n];
		Queue<Pair> q = new LinkedList<>();
		Queue<Pair> q2 = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = sc.next();
				nation[i][j] = -1;
				if (a[i][j].charAt(0) >='A' && a[i][j].charAt(0) <= 'Z') { 
					q.add(new Pair(i, j, a[i][j]));
					q2.add(new Pair(i, j, a[i][j]));
					na[i][j] = a[i][j];
					nation[i][j] = 0;
				}
			}
		}
		
		//영토의 기준점이 되는 정보가 들어있는 큐사이즈만큼 루프를 돌린다.
		while (!q.isEmpty()) {
			Pair p = q.remove();
			for (int i = 0; i < 4; i++) {
				//i=0~3까지, 0:하, 1:상, 2:우, 3:좌
				//벽정보: 상(1), 하(2), 좌(4), 우(8)
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
					if (nation[nx][ny] != -1) continue;
					if (a[p.x][p.y].charAt(0) >='1' && a[p.x][p.y].charAt(0) <= '9') {
						int num = Integer.parseInt(a[p.x][p.y]);
						if (i == 0 && (num == 2 || num == 3 || 
								num == 6 || num == 10)) continue; // 현재 위치에서 나가는 방향이 벽인경우 점령할 수 없다.
						if (i == 1 && (num == 1 || num == 3 || 
								num == 5 || num == 9)) continue; // 현재 위치에서 나가는 방향이 벽인경우 점령할 수 없다.
						if (i == 2 && (num == 8 || num == 9 || 
								num == 10 || num == 12)) continue; // 현재 위치에서 나가는 방향이 벽인경우 점령할 수 없다.
						if (i == 3 && (num == 4 || num == 5 || 
								num == 6 || num == 12)) continue; // 현재 위치에서 나가는 방향이 벽인경우 점령할 수 없다.
					}
					if (a[nx][ny].charAt(0) >='1' && a[nx][ny].charAt(0) <= '9') {
						if ((Integer.parseInt(a[nx][ny])&(int) Math.pow(2, i)) == (int) Math.pow(2, i)) continue; // 벽에 부딪히는 경우 점령할 수 없다.
					}
					nation[nx][ny] = nation[p.x][p.y] + 1;
					q.add(new Pair(nx, ny, null));
				}
			}
		}
		//국가의 최초 영토에서 시작해서 nation(x,y)의 숫자가 1 증가하는 방향으로 영토를 확장
		//만약 증가하는 방향에 동일한 영토가 있다면 그곳은 0으로 변경
		while (!q2.isEmpty()) {
			Pair p = q2.remove();
			for (int i = 0; i < 4; i++) {
				//i=0~3까지, 0:하, 1:상, 2:우, 3:좌
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				String nat = p.a;
				if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
					if (nation[p.x][p.y] + 1 != nation[nx][ny]) continue;
					if (na[nx][ny] != null) {
						if (na[nx][ny] != nat) {
							na[nx][ny] = "0";
						}
						continue;
					}
					na[nx][ny] = nat;
					q2.add(new Pair(nx, ny, nat));
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (na[i][j] == null) {
					System.out.print("0");
				} else {
					System.out.print(na[i][j]);
				}
				if (j < n-1) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		
		sc.close();
	}
}