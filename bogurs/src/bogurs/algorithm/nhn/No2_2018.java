package bogurs.algorithm.nhn;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 2. 전광판 광고
  문제의 입력값은 각 언어의 표준입력(stdin) 함수를, 출력값은 표준출력(stdout) 함수를 사용해주세요.
전광판 개발자인 ‘엔터’는 [기획안]에 따라 이름들이 이동하는 정사각형 전광판을 개발하기로 했습니다.
전광판에 사용되는 데이터는 정사각 행렬로 주어지고 그 크기와 회전수를 입력받아, 회전한 결과를 출력하는 모듈을 개발해야 합니다.



[기획안]

테두리는 아래 그림에서 회전 방향이 같은 색깔을 가지고 같은 방향으로 회전하는 일련의 원소들을 의미합니다
홀수 행렬 기획안: 크기 3의 전광판, 가장 바깥 테두리는 반시계 방향으로 3칸 회전 (아래 그림 참고)

짝수 행렬 기획안: 크기 4의 전광판, 가장 바깥 테두리는 시계 방향으로, 그 안쪽 테두리는 반시계 방향으로 7칸 회전 (아래 그림 참고)



[조건]

회전수의 부호는 가장 바깥 테두리의 회전 방향을 결정합니다.
회전수가 양수일 때 시계 방향, 음수일 때 반시계 방향으로 회전합니다.
인접한 두 테두리의 회전 방향은 서로 반대입니다.
회전수의 절댓값만큼 원소들이 이동합니다.


[입력]

첫 번째 줄은 정사각 행렬의 크기 N과 회전수 W가 입력됩니다.
1 < N <= 100
-1,000,000,000 <= W <= 1,000,000,000
두 번째 줄부터는 행렬의 원소값이 입력됩니다.
각 원소는 공백을 포함하지 않는 20바이트 이하의 문자열입니다.
각 줄은 개행문자(newline, \n)로 구분되어 있습니다.


[출력]

테두리부터 중심 사이의 정사각형들을 회전 ± 방향에 맞춰 W만큼 이동한 결과를 출력합니다.
한 줄의 끝은 불필요한 공백 없이 개행문자(newline, \n)로 끝나야 합니다.


[예시]

입력
5 x 5 행렬은 테두리부터 시작으로 반시계방향으로 2만큼 회전
5 -2
강미나 김도연 김세정 김소혜 김청하
유연정 임나영 전소미 정채연 주결경
최유정 강시라 기희현 김나영 김소희
박소연 윤채경 이해인 전소연 정은우
한혜리 강예빈 권은빈 김다니 김서경
출력
김세정 김소혜 김청하 주결경 김소희 
김도연 윤채경 강시라 임나영 정은우 
강미나 이해인 기희현 전소미 김서경 
유연정 전소연 김나영 정채연 김다니 
최유정 박소연 한혜리 강예빈 권은빈 

입/출력 예시
⋇ 입출력 형식을 잘 지켜주세요.
␣ : 공백
↵ : 줄바꿈
−⇥ : 탭
보기 입력 1
2 333
강미나 김도연
김세정 김소혜
출력 1
김세정 강미나
김소혜 김도연

보기 입력 2
3 -777
강미나 김도연 김세정
김소혜 김청하 유연정
임나영 전소미 정채연
출력 2
김도연 김세정 유연정
강미나 김청하 정채연
김소혜 임나영 전소미

 * 
 * @author thsong
 *
 */
class No2_2018 {
	static final int[] sigye_dx = {0, 1, 0, -1}; // 우, 하, 좌, 상
	static final int[] sigye_dy = {1, 0, -1, 0};
	static final int[] ban_sigye_dx = {1, 0, -1, 0}; // 하, 우, 상, 좌
	static final int[] ban_sigye_dy = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // 1 < N <= 100
		int w = sc.nextInt(); // -1,000,000,000 <= W <= 1,000,000,000
		//회전수가 양수일 때 시계 방향 -> , 음수일 때 반시계 방향 <-
		String[][] a = new String[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = sc.next();
			}
		}
		
		/**
		 * 2 333
			강미나 김도연
			김세정 김소혜
			시계방향으로 333이동.
		 */
		int[] ws = new int[n/2];
		for (int i = 0, opt = 1; i < n/2; i++) {
			ws[i] = (w % (4*(n-1))) * opt;
			opt *= (-1);
		}
		
		int nn = 4*(n-1);
		int size = n;
		int nx = 0;
		int ny = 0;
		String[][] na = new String[n][n];
		for (int i = 0; i < ws.length; i++) {
			Deque<String> dq = new LinkedList<>();
			String[] temp = new String[nn];
			for (int j = 0, dir = 0; j < nn; j++) {
				temp[j] = a[nx][ny];
				nx += sigye_dx[dir];
				ny += sigye_dy[dir];
				if (nx < n-size || ny < n-size || nx >= size || ny >= size) {
					nx -= sigye_dx[dir];
					ny -= sigye_dy[dir];
					dir++;
					j--;
				}
			}
			for (int j = 0; j < nn; j++) {
				dq.add(temp[j]);
			}
			for (int j = 0; j < Math.abs(ws[i]); j++) {
				if (ws[i] > 0) {
					String str = dq.removeLast();
					dq.addFirst(str);
				} else if (ws[i] < 0) {
					String str = dq.removeFirst();
					dq.addLast(str);
				}
			}
			
			for (int j = 0, dir = 0; j < nn; j++) {
				na[nx][ny] = dq.removeFirst();
				nx += sigye_dx[dir];
				ny += sigye_dy[dir];
				if (nx < 0 || ny < 0 || nx >= size || ny >= size) {
					nx -= sigye_dx[dir];
					ny -= sigye_dy[dir];
					dq.addFirst(na[nx][ny]);
					dir++;
					j--;
				}
			}
			size--;
			nn = 4*(size-2);
			nx++;
			ny++;
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (na[i][j] == null) {
					na[i][j] = a[i][j];
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(na[i][j]);
				if (j < n-1) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		sc.close();
	}
}