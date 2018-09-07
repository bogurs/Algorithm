package bogurs.algorithm.kakao;

public class Solution_kakao1st_SecretMap {
	public String[] solution(int n, int[] arr1, int[] arr2) {
		String[] answer = new String[n];
		
		//1. arr1[0] or arr2[0] 부터 n크기 까지 가면서 or연산 진행
		int[] tempAnswer = new int[n];
		for (int i = 0; i < n; i++) {
			tempAnswer[i] = arr1[i] | arr2[i];
		}
		
		//2. 이진수로 변경
		//3. or 연산 결과 1="#", 0=공백 으로 새 배열 생성(n*n) 1 ≦ n ≦ 16
		for (int i = 0; i < n; i++) {
			String binStr = Integer.toBinaryString(tempAnswer[i]);
			while (binStr.length() < n) {
				binStr = "0" + binStr;
			}
			System.out.println(binStr);
			answer[i] = binStr.replace("1", "#").replace("0", " ");
		}
		
		System.out.println();
		
		return answer;
	}

	public static void main(String[] args) {
		Solution_kakao1st_SecretMap solution = new Solution_kakao1st_SecretMap();
//		int n = 5;
//		int[] arr1 = {9, 20, 28, 18, 11};
//		int[] arr2 = {30, 1, 21, 17, 28};
//		System.out.println(solution.solution(n, arr1, arr2));
		
		int n2 = 6;
		int[] arr3 = {46, 33, 33 ,22, 31, 50};
		int[] arr4 = {27 ,56, 19, 14, 14, 10};
		System.out.println(solution.solution(n2, arr3, arr4));
	}
}
