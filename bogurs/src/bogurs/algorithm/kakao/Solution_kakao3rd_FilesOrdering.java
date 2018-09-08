package bogurs.algorithm.kakao;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution_kakao3rd_FilesOrdering {
	public String[] solution(String[] files) {
		int fileSize = files.length;
		String[] answer = new String[fileSize];
		final String DELE = "!!";
		final String ZERO = "0";
		
		/*
		 * 1.파일명은 우선 HEAD 부분을 기준으로 사전 순으로 정렬한다.
		 *   이때, 문자열 비교 시 대소문자 구분을 하지 않는다.
		 *   MUZI와 muzi, MuZi는 정렬 시에 같은 순서로 취급된다.
		 * 2. 파일명의 HEAD 부분이 대소문자 차이 외에는 같을 경우, 
		 *   NUMBER의 숫자 순으로 정렬한다. 9 < 10 < 0011 < 012 < 13 < 014 순으로 정렬된다. 
		 *   숫자 앞의 0은 무시되며, 012와 12는 정렬 시에 같은 같은 값으로 처리된다.
		 * 3. 두 파일의 HEAD 부분과, NUMBER의 숫자도 같을 경우, 원래 입력에 주어진 순서를 유지한다.
		 *   MUZI01.zip과 muzi1.png가 입력으로 들어오면,
		 *   정렬 후에도 입력 시 주어진 두 파일의 순서가 바뀌어서는 안 된다.
		 */
		
		//TOTAL = img12.png
		//HEAD = img //사전순으로 하되 문자열 비교 시 대소문자 구분을 하지 않는다.
		//NUMBER = 12 // 숫자 앞의 0은 무시하고 숫자만 비교할 것
		
		//1. HEAD와 NUMBER, 그리고 TAIL을 자르기
		String[] heads = new String[fileSize];
		String[] numbers = new String[fileSize];
		for (int i = 0; i < fileSize; i++) {
			String file = files[i];
			String head = "";
			String number = "";
			Matcher numMc = Pattern.compile("[0-9]").matcher(file);
			int startIdx = 0;
			int endIdx = 0;
			if (numMc.find()) {
				startIdx = numMc.start();
				endIdx++;
			}
			while (numMc.find()) {
				endIdx++;
			}
			endIdx += startIdx;
			head = file.substring(0, startIdx);
			number = Integer.parseInt(file.substring(startIdx, endIdx)) + "";
			
			heads[i] = head.toLowerCase() + DELE + i;
			numbers[i] = number;
		}
		
		//2. 순서를 정하기
		Arrays.sort(heads);
		//3. HEAD 부터 순서를 정하기
		int[] order = new int[fileSize];
		for (int i = 0; i < fileSize; i++) {
			String[] tempSA = heads[i].split(DELE);
			heads[i] = tempSA[0];
			order[i] = Integer.parseInt(tempSA[1]);
		}
		int[] sameorder = new int[fileSize]; // 인근한 Head요소가 같은지를 판별해서 배열에 초기화
		for (int i = 0, k = 0; i < fileSize-1; i++) {
			if (heads[i].equals(heads[i+1])) {
				sameorder[i] = sameorder[i+1] = k;
			} else {
				sameorder[i] = i;
				k++;
			}
		}
		
		//4. number에 order에 대한 순서 붙이기
		String[] newNums = new String[fileSize];
		int maxSizeOfNum = 0;
		//5. number 앞에 0을 최대 자리수 만큼 패딩
		for (int i = 0; i < fileSize; i++) {
			int maxSize = numbers[order[i]].length();
			if (maxSize > maxSizeOfNum) {
				maxSizeOfNum = maxSize;
			}
		}
		for (int i = 0; i < fileSize; i++) {
			int diff = maxSizeOfNum - numbers[order[i]].length();
			if (diff > 0) {
				for (int j = 0; j < diff; j++) {
					numbers[order[i]] = ZERO + numbers[order[i]];
				}
			}
			newNums[i] = numbers[order[i]] + DELE + order[i];
		}
		
		for (int i = 0; i < fileSize-1; i++) {
			int startIdx = i;
			int endIdx = i+1;
			while (i < fileSize-1) {
				if (sameorder[i] == sameorder[i+1]) {
					endIdx++;
					i++;
				} else {
					break;
				}
			}
			Arrays.sort(newNums, startIdx, endIdx);
		}
		
		//numbers를 통해 순서를 출력
		for (int i = 0; i < fileSize; i++) {
			order[i] = Integer.parseInt(newNums[i].split(DELE)[1]);
		}
		for (int i = 0; i < fileSize; i++) {
			answer[i] = files[order[i]];
		}
		
		return answer;
	}

	public static void main(String[] args) {
		Solution_kakao3rd_FilesOrdering solution = new Solution_kakao3rd_FilesOrdering();
//		String[] files = {"img12.png", "img10.png", "img02.png"
//				, "img1.png", "IMG01.GIF", "img2.JPG"};
//		//1000 개 이하의 파일명을 포함하는 문자열 배열
//		System.out.println(Arrays.toString(solution.solution(files)));
		
//		String[] files2 = {"F-5 Freedom Fighter", "B-50 Superfortress"
//				, "A-10 Thunderbolt II", "F-14 Tomcat"};
//		//1000 개 이하의 파일명을 포함하는 문자열 배열
//		System.out.println(Arrays.toString(solution.solution(files2)));
		
		String[] files3 = {"F-5 Freedom Fighter", "B-50 Superfortress"
				, "A-10 Thunderbolt II", "F-14 Tomcat"};
		//1000 개 이하의 파일명을 포함하는 문자열 배열
		System.out.println(Arrays.toString(solution.solution(files3)));
	}
}
