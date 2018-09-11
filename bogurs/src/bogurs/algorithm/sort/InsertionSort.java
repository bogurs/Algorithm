package bogurs.algorithm.sort;

import java.util.Arrays;

/**
 * 삽입 정렬: 두번째 요소부터 시작해 앞 인덱스와 하나씩 비교하면서 들어갈 위치에 삽입하는 정렬
 * 시간복잡도: O(n^2)
 * 공간복잡도: O(n)
 * 
 * 1. n+1 배열의 요소를 지정한다.
 * 2. n+1 배열의 인덱스를 지정한다.
 * 3. n 배열의 요소부터 첫번째 요소까지 1에서 지정한 요소와 비교해서 큰 값이 있다면 2에서 지정한 인덱스의 값으로 지정한다.
 * @author thsong
 *
 */
public class InsertionSort {
	
	private static void insertionSort(int[] arr, int option) {
		int arrLen = arr.length;
		
		if (option == 0) {
			for (int i = 1; i < arrLen; i++) {
				int insertTemp = arr[i];
				int idx = i;
				for (int j = i-1; j >= 0; j--) {
					if (insertTemp < arr[j]) {
						arr[idx] = arr[j];
						idx = j;
					}
				}
				arr[idx] = insertTemp;
			}
		} else if (option == 1) {
			for (int i = 1; i < arrLen; i++) {
				int insertTemp = arr[i];
				int idx = i;
				for (int j = i-1; j >= 0; j--) {
					if (insertTemp > arr[j]) {
						arr[idx] = arr[j];
						idx = j;
					}
				}
				arr[idx] = insertTemp;
			}
		}
		
		System.out.println(Arrays.toString(arr));
	}

	public static void main(String[] args) {
		int[] arr = {8, 5, 4, 9, 1, 3, 2, 6, 7};
		insertionSort(arr, 0); // 오름차순 (작은 수 삽입정렬)
		insertionSort(arr, 1); // 내림차순 (큰 수 삽입정렬)
	}

}
