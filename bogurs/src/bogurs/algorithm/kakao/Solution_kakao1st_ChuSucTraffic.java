package bogurs.algorithm.kakao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Solution_kakao1st_ChuSucTraffic {
	public int solution(String[] lines) throws ParseException {
		int answer = 1;
		int linesLen = lines.length;
		Date[] startTimeArr = new Date[lines.length];
		Date[] endTimeArr = new Date[lines.length];
		String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

		// 시점 구하기
		for (int i = 0; i < linesLen; i++) {
			String[] splitLine = lines[i].split(" ");
			double sec = Double.parseDouble(splitLine[2].replace("s", "")) * 1000;
			endTimeArr[i] = sdf.parse((splitLine[0] + " " + splitLine[1]));
			startTimeArr[i] = new Date(endTimeArr[i].getTime() - (long) sec + (long) 1);
		}

		// 하나씩 구간을 옮겨가면서 현재 종점과 다음 구간의 시점의 시간 차이가 1초 미만인 대상을 모두 찾는다.
		// 찾은 경우 임시 total에 1씩 증가시키고, 1회 loop가 종료되면 answer와 비교하여 최대값을 answer에 할당한다.
		for (int i = 1; i < linesLen; i++) {
			int newAnswer = 1;
			for (int j = i; j < linesLen; j++) {
				if (startTimeArr[j].getTime() - endTimeArr[i - 1].getTime() < (long) 1000) {
					newAnswer++;
				}
			}
			if (newAnswer > answer) {
				answer = newAnswer;
			}
		}

		return answer;
	}
    
    public static void main(String[] args) throws ParseException {
		Solution_kakao1st_ChuSucTraffic solution = new Solution_kakao1st_ChuSucTraffic();
		String[] arr = {"2016-09-15 01:00:04.001 2.0s",
				"2016-09-15 01:00:07.000 2s"};
		System.out.println(solution.solution(arr));
		
//		String[] arr2 = {"2016-09-15 01:00:04.002 2.0s", 
//				"2016-09-15 01:00:07.000 2s"};
//		System.out.println(solution.solution(arr2));
//		
//		String[] arr3 = {"2016-09-15 20:59:57.421 0.351s",
//				"2016-09-15 20:59:58.233 1.181s",
//				"2016-09-15 20:59:58.299 0.8s",
//				"2016-09-15 20:59:58.688 1.041s",
//				"2016-09-15 20:59:59.591 1.412s",
//				"2016-09-15 21:00:00.464 1.466s",
//				"2016-09-15 21:00:00.741 1.581s",
//				"2016-09-15 21:00:00.748 2.31s",
//				"2016-09-15 21:00:00.966 0.381s",
//				"2016-09-15 21:00:02.066 2.62s"};
//		System.out.println(solution.solution(arr3));
	}
}