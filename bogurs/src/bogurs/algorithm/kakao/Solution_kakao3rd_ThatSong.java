package bogurs.algorithm.kakao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Solution_kakao3rd_ThatSong {
	public String solution(String m, String[] musicinfos) throws ParseException {
		String answer = "'(None)'";
		final String PREFIX = "2018-09-08 ";
		final String PATTERN = "yyyy-MM-dd HH:mm";
		final SimpleDateFormat SDF = new SimpleDateFormat(PATTERN);
		final String DELE = ",";
		final char SHARP = '#';
		
		//1. 주어진 musicinfos를 통해 모든 악보를 구한다.
		// 이 때, #이 들어간 경우는 한 음절로 본다.
		//조건이 일치하는 음악이 여러 개일 때에는 라디오에서 재생된
		//시간이 제일 긴 음악 제목을 반환한다. 재생된 시간도 같을 경우 먼저 입력된 음악 제목을 반환한다.
		//조건이 일치하는 음악이 없을 때에는 `(None)`을 반환한다.
		
		//1. musicinfos 전체 악보구하기
		int musicSize = musicinfos.length;
		Date[] startDates = new Date[musicSize];
		Date[] endDates = new Date[musicSize];
		String[] titles = new String[musicSize];
		String[] contents = new String[musicSize];
		for (int i = 0; i < musicSize; i++) {
			String[] splitTemp = musicinfos[i].split(DELE);
			startDates[i] = SDF.parse(PREFIX + splitTemp[0]);
			endDates[i] = SDF.parse(PREFIX + splitTemp[1]);
			titles[i] = splitTemp[2];
			contents[i] = splitTemp[3];
		}
		
		//2. musicinfos 음악의 플레이 시간(분 구하기) 단, 00:00을 넘어가서 재생하는 경우는 없다.
		int[] playtimes = new int[musicSize];
		for (int i = 0; i < musicSize; i++) {
			playtimes[i] = (int) (endDates[i].getTime() - startDates[i].getTime()) / (60*1000);
		}
		
		//3. 결과 구하기
		//3-1. 악보의 전체 구간 구하기
		// 결과1. 일치하는 악보 정보의 음악이 1개이면 그 노래의 제목을 반환한다.
		// 결과2. 일치하는 악보 정보의 음악이 2개이상이면 그 중 가장 긴 플레이 시간의 노래를 반환한다.
		// 결과3. 일치하는 악보 정보의 음악이 없으면 '(None)'이라는 문구를 반환한다.
		for (int i = 0; i < musicSize; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0, k = 0; j < playtimes[i]; j++) {
				char first = contents[i].charAt(k);
				char second = 0;
				if (k < contents[i].length()-1) {
					second = contents[i].charAt(k+1);
				}
				if (second == SHARP) {
					sb.append(first).append(second);
					k+=2;
				} else {
					sb.append(first);
					k++;
				}
				if (k == contents[i].length()) {
					k = 0;
				}
			}
			contents[i] = sb.toString();
		}
		
		int playTime = 0;
		for (int i = 0; i < musicSize; i++) {
			String newAnswer = null;
			int newPlayTime;
			if (contents[i].contains(m)) {
				char right = contents[i].charAt(contents[i].indexOf(m) + m.length());
				if (right != SHARP) {
					newAnswer = titles[i];
					newPlayTime = playtimes[i];
					if (newPlayTime > playTime) {
						answer = newAnswer;
						playTime = newPlayTime;
					}
				}
			}
		}
		
		return answer;
	}

	public static void main(String[] args) throws ParseException {
		Solution_kakao3rd_ThatSong solution = new Solution_kakao3rd_ThatSong();
//		String m = "ABCDEFG"; // 1개 이상 1439개 이하
//		String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
//		// 100개 이하의 곡 정보를 담고 있는 배열(시작시각, 종료시각, 제목, 악보정보)
//		System.out.println(solution.solution(m, musicinfos)); //HELLO
//		
		String m2 = "CC#BCC#BCC#BCC#B"; // 1개 이상 1439개 이하
		String[] musicinfos2 = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};
		// 100개 이하의 곡 정보를 담고 있는 배열(시작시각, 종료시각, 제목, 악보정보)
		System.out.println(solution.solution(m2, musicinfos2)); //FOO
		
//		String m3 = "ABC"; // 1개 이상 1439개 이하
//		String[] musicinfos3 = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
//		// 100개 이하의 곡 정보를 담고 있는 배열(시작시각, 종료시각, 제목, 악보정보)
//		System.out.println(solution.solution(m3, musicinfos3)); //WORLD
	}
}
