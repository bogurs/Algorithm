package bogurs.algorithm.sort;

import java.util.Arrays;

/**
 * 계수 정렬
 * 
 * 시간복잡도 O(n+k) (O(n), when k <= n) (O(n^lognk) [n은 밑, k는 지수], when k >>> n)
 * 배열에 숫자만 있을 때만 가능한 정렬 방법이다.
 * 
 * 방법: x보다 작은 원소의 개수가 N개일 때, x는 N+1번째에 위치해야 한다.
 * 
 * 1. 숫자의 빈도수를 저장하는 c배열을 k+1 크기로 생성한다.
 * 2. 주어진 배열을 인덱스로 사용하여 계수배열을 완성하고, 빈도수를 하나씩 누적하여 계수 배열을 완성한다.
 * 3. 계수 배열을 이용해서 주어진 배열의 마지막 요소부터 첫 요소까지 거꾸로 새 배열에 저장한다.
 *    하나의 요소를 저장한 후 c[마지막 요소]를 1 감소시킨다. (동일요소가 있다면 바로 앞에 오게 된다)
 * 
 * @author thsong
 *
 */
public class CountingSort {

	private static void countingSort(int[] arr, int k) {
		int[] c = new int[k+1];
		int[] newArr = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			c[arr[i]]++;
		}
		
		for (int i = 1; i <= k; i++) {
			c[i] += c[i-1];
		}
		
		for (int i = arr.length-1; i >= 0; i--) {
			newArr[c[arr[i]]-1] = arr[i];
			c[arr[i]]--;
		}
		System.out.println(Arrays.toString(newArr));
	}
	
	public static void main(String[] args) {
		int[] arr4 = {8, 8, 4, 9, 1, 3, 0, 0, 7};
		int[] arr2 = {8, 5, 4, 9, 1, 3, 2, 6, 7};
		int[] arr3 = {833, 512, 425, 94, 1234, 35646, 223, 644, 73};
		int[] arr = {833, 512, 425, 94, 1234, 356466, 223, 644, 73};
		int k = 35646666;
		System.out.println(Arrays.toString(arr));
		long start = System.currentTimeMillis();
		countingSort(arr, k);
		System.out.println("performance: " + (System.currentTimeMillis() - start) + " ms");
	}

}
