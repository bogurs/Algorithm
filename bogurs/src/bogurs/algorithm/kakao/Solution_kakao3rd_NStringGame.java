package bogurs.algorithm.kakao;

public class Solution_kakao3rd_NStringGame {
	public String solution(int n, int t, int m, int p) {
		String answer = "";
		
		//2진수, 4개 숫자출력, 2명 인원수, 1번째 출력순서
		//1. n에 따라 m(인원수)*t(숫자출력) 만큼 숫자를 모두 구해서 String에 저장한다.
		StringBuilder tot = new StringBuilder();
		for (int i = 0; i < m*t; i++) {
			if (tot.length() > m*t) {
				String temp = tot.substring(0, m*t);
				tot.replace(0, tot.length(), temp);
				break;
			}
			
			tot.append(Integer.toString(i, n));
		}
		
		//2. 구한 String의 길이만큼 루프를 돌리면서 인원수만큼 String을 나눈다.
		//나누다가 저장해야 하는 String이면 answer에다 저장한다.
		StringBuilder tempAn = new StringBuilder();
		for (int i = 0; i < tot.length(); i++) {
			if ((i%m)+1 == p) {
				tempAn.append(tot.substring(i, i+1).toUpperCase());
			}
		}
		answer = tempAn.toString();
		
		return answer;
	}

	public static void main(String[] args) {
		Solution_kakao3rd_NStringGame solution = new Solution_kakao3rd_NStringGame();
		int n = 2; // 진수, 2 ≦ n ≦ 16
		int t = 4; // 출력숫자개수, 0 ＜ t ≦ 1000
		int m = 2; // 인원수, 2 ≦ m ≦ 100
		int p = 1; // 출력순서, 1 ≦ p ≦ m
		System.out.println(solution.solution(n, t, m, p));
		
		int n2 = 16; // 진수, 2 ≦ n ≦ 16
		int t2 = 16; // 출력숫자개수, 0 ＜ t ≦ 1000
		int m2 = 2; // 인원수, 2 ≦ m ≦ 100
		int p2 = 1; // 출력순서, 1 ≦ p ≦ m
		System.out.println(solution.solution(n2, t2, m2, p2));
		
		int n3 = 10; // 진수, 2 ≦ n ≦ 16
		int t3 = 10; // 출력숫자개수, 0 ＜ t ≦ 1000
		int m3 = 2; // 인원수, 2 ≦ m ≦ 100
		int p3 = 1; // 출력순서, 1 ≦ p ≦ m
		System.out.println(solution.solution(n3, t3, m3, p3));
	}
}
