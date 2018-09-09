package bogurs.algorithm.kakao;

import java.util.Arrays;

public class Solution_kakao3rd_AutoComplete {
	public int solution(String[] words) {
		int answer = 0;
		int wordsLen = words.length;
		
		// 문자가 자동완성이 될때까지 입력해야 하는 총 문자열의 개수
		//1. 문자열을 오름차순으로 정렬(Arrays)
		Arrays.sort(words);
		
		//2. 모든 문자열에 대해서 첫번째 문자부터 겹치는 횟수를 체크함
		int[] correct = new int[wordsLen];
		int afterCo = -2;
		int beforeCo = -2;
		int lastAfterCo = -2;
		for (int i = 0; i < wordsLen; i++) {
			int cnt = 0;
			beforeCo = lastAfterCo;
			for (int j = 1; j <= words[i].length(); j++) {
				String temp = words[i].substring(0, j);
				if (i < wordsLen-1) {
					afterCo = words[i+1].indexOf(temp);
				}
				if (afterCo >= 0) {
					afterCo += j;
					lastAfterCo = afterCo;
				}
				if (afterCo > beforeCo) {
					cnt = afterCo;
				} else {
					cnt = beforeCo;
				}
				if (cnt > correct[i]) {
					correct[i] = cnt;
				}
				if (afterCo < 0) {
					break;
				}
			}
		}
		
		//3. 겹치는 횟수와 문자열의 길이가 같으면 answer에 겹치는 횟수를 더해준다.
		//3-1. 겹치는 횟수가 문자열의 길이보다 작으면 (겹치는 횟수+1)만큼 answer에 더해준다.
		for (int i = 0; i < wordsLen; i++) {
			answer += correct[i];
			if (correct[i] < words[i].length()) {
				answer++;
			}
		}
		
		return answer;
	}

	public static void main(String[] args) {
		Solution_kakao3rd_AutoComplete solution = new Solution_kakao3rd_AutoComplete();
		String[] words = { "go", "gone", "guild" }; //중복없는단어 2 <= N <= 100,000 ,단어들의 길이의 총합 2 <= L <= 1,000,000
		System.out.println(solution.solution(words)); // 7
		
		String[] words2 = { "word", "war", "warrior", "world" }; //중복없는단어 2 <= N <= 100,000 ,단어들의 길이의 총합 2 <= L <= 1,000,000
		System.out.println(solution.solution(words2)); // 7
		
		String[] words3 = { "word", "war", "warrior", "world" }; //중복없는단어 2 <= N <= 100,000 ,단어들의 길이의 총합 2 <= L <= 1,000,000
		System.out.println(solution.solution(words3)); // 7
	}
}
