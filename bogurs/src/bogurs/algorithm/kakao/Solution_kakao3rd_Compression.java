package bogurs.algorithm.kakao;

import java.util.Arrays;
import java.util.HashMap;

public class Solution_kakao3rd_Compression {
	public int[] solution(String msg) {
		int[] answer;
		HashMap<String, Integer> dict = new HashMap<>();
		char ch = 'A';
		int index = 0;
		
		for (index = 1; index <= 26; index++) {
			dict.put(String.valueOf(ch), index);
			ch++;
		}
		
		//1. 한글자씩 검사 시작
		int cnt;
		int totCnt = 0;
		int[] tempAns = new int[msg.length()];
		for (int i = 0; i < msg.length(); i+=1+cnt) {
			//다음 글자와 결합하여 사전에 검색했을 때 있는지 없는지 부터 확인
			cnt = 0;
			String temp = msg.substring(i, i+1);
			String temp2 = msg.substring(i, i+1);
			for (int j = i+1; j < msg.length(); j++) {
				//다음 글자와 결합했을 때 사전에 있으면 다음 글자와 결합해서
				//다시 검색하는 방식으로 진행
				temp2 = temp2.concat(msg.substring(j, j+1));
				if (dict.containsKey(temp2)) {
					cnt++;
					totCnt++;
					temp = temp2;
				} else {
					break;
				}
			}
			
			tempAns[i] = dict.get(temp);
			dict.put(temp2, index);
			index++;
		}
		
		//결과 축소하기 (0을 제외하고 결과 압축하기)
		answer = new int[msg.length()-totCnt];
		for (int i = 0, j = 0; i < msg.length(); i++) {
			if (tempAns[i] != 0) {
				answer[j] = tempAns[i];
				j++;
			}
		}
		
		return answer;
	}

	public static void main(String[] args) {
		Solution_kakao3rd_Compression solution = new Solution_kakao3rd_Compression();
//		String msg = "KAKAO"; // 1 글자 이상, 1000 글자 이하
//		System.out.println(Arrays.toString(solution.solution(msg)));
		
		String msg2 = "TOBEORNOTTOBEORTOBEORNOT"; // 1 글자 이상, 1000 글자 이하
		System.out.println(Arrays.toString(solution.solution(msg2)));
	}
}
