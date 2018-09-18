package bogurs.algorithm.sort;

import java.util.Arrays;

/**
 * 퀵 정렬: pivot 값을 기준으로 지정하여 작은 값은 왼쪽, 큰 값은 오른쪽으로 옮기는 방식의 정렬
 * 시간복잡도: O(nlogn, 이미 정렬된 경우 n^2)
 * 공간복잡도: O(n^2)
 * 
 * 1. 비교할 기준이 되는 pivot 값을 지정한다.
 * 2. 왼쪽 끝을 가리키는 s포인터와 오른쪽 끝을 가리키는 e포인터를 지정한다.
 * 3. 왼쪽의 s포인터의 배열 값부터 pivot값과 비교하여 더 작으면 다음 인덱스로 포인터 위치를 옮기고,
 *    더 크면 변경 대상으로 지정하고 포인터의 움직임을 멈춘다.
 * 4. 오른쪽의 e포인터의 배열 값과 pivot값을 비교하여 더 크면 다음 인덱스로 포인터 위치를 옮기고,
 *    더 작으면 변경 대상으로 지정하고 포인터의 움직임을 멈춘다.
 * 5. 3에서 정해진 인덱스의 값과 4에서 정해진 포인터의 값을 서로 변경한다.
 * 6. s < e 동안, 3~5를 반복한다.
 * 7. 1~6을 반복한다.
 * @author thsong
 *
 */
public class QuickSort {
	
	private static void quickSort(int[] arr, int start, int end) {
		int pivot = end;
		int bs = start;
		int be = end;
		
		if (start >= end) {
			return;
		}
		
		while (start < end) {
			while (arr[pivot] >= arr[start]) {
				start++;
				if (start == be) break;
			}
			while (arr[pivot] <= arr[end]) {
				end--;
				if (end == bs) break;
			}
			if (start >= end) break;
			int temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			System.out.println(Arrays.toString(arr));
		}
		
		int temp = arr[start];
		arr[start] = arr[pivot];
		arr[pivot] = temp;
		pivot = start;
		System.out.println(Arrays.toString(arr));
		
		quickSort(arr, bs, pivot-1);
		quickSort(arr, pivot+1, be);
	}

	public static void main(String[] args) {
		int[] arr = {8, 5, 4, 1, 6, 2, 7, 3};
		System.out.println(Arrays.toString(arr)); // 퀵 정렬 전
		quickSort(arr, 0, arr.length-1);
	}

}
