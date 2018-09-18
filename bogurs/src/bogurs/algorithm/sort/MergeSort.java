package bogurs.algorithm.sort;

import java.util.Arrays;

/**
 * 합병 정렬: n개만큼 배열을 나눈뒤 나눈 각 배열을 다시 합치면서 정렬하는 알고리즘
 * 시간복잡도: O(nlogn)
 * 공간복잡도: O(n^2)
 * 
 * (1) 리스트의 길이가 0 또는 1이면 이미 정렬된 것으로 본다. 그렇지 않은 경우에는
 * (2) 정렬되지 않은 리스트를 절반으로 잘라 비슷한 크기의 두 부분 리스트로 나눈다.
 * (3) 각 부분 리스트를 재귀적으로 합병 정렬을 이용해 정렬한다.
 * (4) 두 부분 리스트를 다시 하나의 정렬된 리스트로 합병한다.
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
