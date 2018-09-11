package bogurs.algorithm.sort;

import java.util.Arrays;

/**
 * 합병 정렬: n개만큼 배열을 나눈뒤 나눈 각 배열을 다시 합치면서 정렬하는 알고리즘
 * 시간복잡도: O(nlogn)
 * 공간복잡도: O(2n)
 * 
 * 1. n개만큼의 배열로 주어진 배열을 쪼갠다.
 * 2. 배열을 2개씩 비교하면서 정렬된 새 배열을 만든다.
 * 3. 원래 배열의 size가 될 때까지 2를 반복한다.
 * @author thsong
 *
 */
public class MergeSort {
	private int option;
	
	public MergeSort(int option) {
		this.option = option;
	}
	
	private void mergeSort(int[] arr) {
		int[] temp = new int[arr.length];
		mergeSort(arr, temp, 0, arr.length-1);
	}
	
	private void mergeSort(int[] arr, int[] temp, int s, int e) {
		if (s < e) {
			int m = (s + e) / 2;
			mergeSort(arr, temp, s, m);
			mergeSort(arr, temp, m+1, e);
			merge(arr, temp, s, m, e);
//			System.out.println(Arrays.toString(arr));
		}
	}

	private void merge(int[] arr, int[] temp, int s, int m, int e) {
		for (int i = s; i <= e; i++) {
			temp[i] = arr[i];
		}
		int sIdx = s;
		int mIdx = m+1;
		int idx = s;
		
		if (option == 0) {
			while (sIdx <= m && mIdx <= e) {
				if (temp[sIdx] < temp[mIdx]) {
					arr[idx] = temp[sIdx];
					sIdx++;
				} else {
					arr[idx] = temp[mIdx];
					mIdx++;
				}
				idx++;
			}
		} else {
			while (sIdx <= m && mIdx <= e) {
				if (temp[sIdx] > temp[mIdx]) {
					arr[idx] = temp[sIdx];
					sIdx++;
				} else {
					arr[idx] = temp[mIdx];
					mIdx++;
				}
				idx++;
			}
		}
		
		for (int i = 0; i <= m - sIdx; i++) {
			arr[idx + i] = temp[sIdx + i];
		}
	}

	public static void main(String[] args) {
		int[] arr = {8, 5, 4, 1, 6, 2, 7, 3};
		System.out.println(Arrays.toString(arr));
		MergeSort ms = new MergeSort(0);
		ms.mergeSort(arr); // 오름차순 (작은 수 합병정렬)
		System.out.println(Arrays.toString(arr));
		MergeSort ms2 = new MergeSort(1);
		ms2.mergeSort(arr); // 내림차순 (큰 수 합병정렬)
		System.out.println(Arrays.toString(arr));
	}

}
