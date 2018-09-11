package bogurs.algorithm.sort;

import java.util.Arrays;

/**
 * 버블 정렬: 첫번째 요소부터 인접한 다음 요소와 비교해서 정렬을 하는 방식이다.
 * 시간복잡도: O(n^2)
 * 공간복잡도: O(n)
 * 
 * 1. 인접한 다음 요소와 비교해서 이전의 요소가 더 큰 경우 자리를 바꾼다.
 * 2. 배열의 길이만큼 인덱스를 이동하면서 1을 수행한다.
 * 3. 배열의 길이만큼 1~2을 반복한다.
 * @author thsong
 *
 */
public class BubbleSort {
	
	private static void bubbleSort(int[] arr, int option) {
		int arrLen = arr.length;
		
		if (option == 0) {
			for (int i = arrLen-1; i >= 0; i--) {
				for (int j = 0; j < i; j++) {
					if (arr[j] > arr[j+1]) {
						int temp = arr[j];
						arr[j] = arr[j+1];
						arr[j+1] = temp;
					}
				}
			}
		} else if (option == 1) {
			for (int i = arrLen-1; i >= 0; i--) {
				for (int j = 0; j < i; j++) {
					if (arr[j] < arr[j+1]) {
						int temp = arr[j];
						arr[j] = arr[j+1];
						arr[j+1] = temp;
					}
				}
			}
		}
		
		System.out.println(Arrays.toString(arr));
	}

	public static void main(String[] args) {
		int[] arr = {8, 5, 4, 9, 1, 3, 2, 6, 7};
		bubbleSort(arr, 0); // 오름차순 (작은 수 버블정렬)
		bubbleSort(arr, 1); // 내림차순 (큰 수 버블정렬)
	}

}
