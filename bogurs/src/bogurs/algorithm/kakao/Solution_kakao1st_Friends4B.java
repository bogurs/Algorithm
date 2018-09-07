package bogurs.algorithm.kakao;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_kakao1st_Friends4B {
	private int m;
	private int n;
	private final String NUL_STRING = "NUL_STR";
	private String[][] newBoard;
	private boolean[][] isDeleteOk;
	private int answer = 0;
	
	public int solution(int m, int n, String[] board) {
		this.m = m;
		this.n = n;
		this.answer = 0;
		
		//1. 삭제해도 되는지 여부를 저장하는 boolean 배열 초기화
		isDeleteOk = new boolean[m][n];
		
		//2. 1차원 board 배열을 2차원으로 만들기
		newBoard = new String[m][n];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < n; j++) {
				newBoard[i][j] = board[i].substring(j, j+1);
			}
		}
		
		go();
		
		return answer;
	}

	private void go() {
		int answer = 0 ;
		
		//3. 2차원 배열의 첫번째요소부터 1개씩 모두 인접한 2*2 행렬 조사하기
		for (int i = 0; i < m-1; i++) {
			for (int j = 0; j < n-1; j++) {
				if (!newBoard[i][j].equals(NUL_STRING) &&
						newBoard[i][j].equals(newBoard[i+1][j]) &&
						newBoard[i][j].equals(newBoard[i][j+1]) &&
						newBoard[i][j].equals(newBoard[i+1][j+1])) { //인접한 4개 행렬이 모두 같은 값인 경우(삭제가능)
					isDeleteOk[i][j] = isDeleteOk[i+1][j] = isDeleteOk[i][j+1] = isDeleteOk[i+1][j+1] = true;
				}
			}
		}
		
		//4. isDeleteOk값에 따라 true인 경우 newBoard행렬에 삭제하여 배열 재생성
		// 세로 한줄씩 조사해서 내릴 수 있는지 여부에 따라 재정렬
		@SuppressWarnings("unchecked")
		Queue<String>[] queues = new Queue[n];
		for (int j = 0; j < n; j++) {
			queues[j] = new LinkedList<>();
			for (int i = 0; i < m; i++) {
				if (!isDeleteOk[i][j]) {
					queues[j].add(newBoard[i][j]);
				}
			}
		}
		
		//5. queues 정보에 따라 newBoard에 새 배열 정보 입력
		for (int j = 0; j < n; j++) {
			int queueSize = queues[j].size();
			if (queueSize == m) {
				continue;
			}
			for (int i = 0; i < m; i++) {
				if (i < m-queueSize) {
					newBoard[i][j] = NUL_STRING;
					answer++;
				} else {
					int k = 0;
					while (!queues[j].isEmpty()) {
						String tempStr = queues[j].remove();
						newBoard[i+k][j] = tempStr;
						k++;
					}
					break;
				}
			}
		}
		
		//3~5를 반복해서 수행
		if (answer > 0) {
			this.answer += answer;
			// isDeleteOk 배열 초기화
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (isDeleteOk[i][j]) {
						isDeleteOk[i][j] = false;
					}
				}
			}
			go();
		}
	}

	public static void main(String[] args) {
		Solution_kakao1st_Friends4B solution = new Solution_kakao1st_Friends4B();
		int m = 4; // 2 ≦ n ≦ 30
		int n = 5; // 2 ≦ m ≦ 30
		String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"}; // 대문자 A에서 Z가 사용 (26개)
		System.out.println(solution.solution(m, n, board));
		
		int m2 = 6; // 2 ≦ n ≦ 30
		int n2 = 6; // 2 ≦ m ≦ 30
		String[] board2 = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"}; // 대문자 A에서 Z가 사용 (26개)
		System.out.println(solution.solution(m2, n2, board2));
		
	}
}
