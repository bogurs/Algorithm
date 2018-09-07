package bogurs.algorithm.kakao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution_kakao1st_News {
	public int solution(String str1, String str2) {
		int answer = 0;
		List<String> str1Arr = new ArrayList<>();
		List<String> str2Arr = new ArrayList<>();
		str1 = str1.toUpperCase();
		str2 = str2.toUpperCase();
		
		if (str1.equals(str2)) {
			answer = 65536;
		} else {
			for (int i = 0; i < str1.length() - 1; i++) {
				String tempStr = str1.substring(i, i+2);
				Matcher matcher = Pattern.compile("^([A-Z])*&").matcher(tempStr);
				if (matcher.find()) {
					str1Arr.add(tempStr);
				}
			}
			for (int i = 0; i < str2.length() - 1; i++) {
				String tempStr = str2.substring(i, i+2);
				Matcher matcher = Pattern.compile("^([A-Z])*&").matcher(tempStr);
				if (matcher.find()) {
					str2Arr.add(tempStr);
				}
			}
			
			Collections.sort(str1Arr);
			Collections.sort(str2Arr);
			if (str1Arr.equals(str2Arr)) {
				answer = 65536;
			} else {
				int findCnt = 0;
				for (String toFindStr : str1Arr) {
					if (Collections.binarySearch(str2Arr, toFindStr) > 0) {
						findCnt++;
					}
				}
				double imsiAnswer = (double) findCnt / (str1Arr.size() + str2Arr.size() - findCnt);
				answer = (int) (imsiAnswer * 65536);
			}
		}
		
		return answer;
	}

	public static void main(String[] args) {
		Solution_kakao1st_News solution = new Solution_kakao1st_News();
		String str1 = "FRANCE";
		String str2 = "french";
		System.out.println(solution.solution(str1, str2));
	}
}
