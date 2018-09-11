package bogurs.algorithm.sort;

import java.util.Arrays;

/**
 * 선택 정렬: 첫번째 요소부터 가장 작거나 큰 수를 선택하여 서로 자리를 변경하는 방식으로 정렬하는 방법
 * 시간복잡도: O(n^2)
 * 공간복잡도: O(n)
 * 
 * 1. n 요소를 인덱스로 지정한다.
 * 2. n+1 요소부터 1에서 지정한 인덱스의 요소와 비교해서 가장 작은 인덱스를 찾는다.
 * 3. 2에서 찾은 인덱스와 현재 비교 대상을 서로 변경한다.
 * 4. 배열의 길이만큼 1~3을 반복한다.
 * @author thsong
 *
 */
public class SelectionSort {
	
	private static void selectionSort(int[] arr, int option) {
		int arrLen = arr.length;
		
		if (option == 0) {
			for (int i = 0; i < arrLen; i++) {
				int idx = i;
				for (int j = i+1; j < arrLen; j++) {
					if (arr[j] < arr[idx]) {
						idx = j;
					}
				}
				int temp = arr[i];
				arr[i] = arr[idx];
				arr[idx] = temp;
			}
		} else if (option == 1) {
			for (int i = 0; i < arrLen; i++) {
				int idx = i;
				for (int j = i+1; j < arrLen; j++) {
					if (arr[j] > arr[idx]) {
						idx = j;
					}
				}
				int temp = arr[i];
				arr[i] = arr[idx];
				arr[idx] = temp;
			}
		}
		
		System.out.println(Arrays.toString(arr));
	}

	public static void main(String[] args) {
		int[] arr = {8, 5, 4, 9, 1, 3, 2, 6, 7};
		selectionSort(arr, 0); // 오름차순 (작은 수 선택정렬)
		selectionSort(arr, 1); // 내림차순 (큰 수 선택정렬)
	}

}
