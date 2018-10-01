package bogurs.algorithm.sort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 기수 정렬
 * 
 * 시간복잡도 O(dn), d는 자릿수
 * MSB(Most Significant Bit) -> LSB(Least Significant Bit) 또는
 * LSB -> MSB으로 정렬해 나가는 방식이 있는데
 * 후자의 방법이 더 많이 사용된다.
 * 
 * 정렬을 수행하는 방법은 둘로 나뉜다.
 *  1. 각 자릿수마다 계수배열을 사용해서 구현하는 방법
 *  2. 계수큐를 사용해서 구하는 방법
 * @author thsong
 *
 */
public class RadixSort {
	
	/**
	 * 계수배열을 이용한 기수정렬
	 * 
	 * 1. 숫자의 빈도수를 저장하는 c배열을 k+1 크기로 생성한다.
	 * 2. 빈도수를 하나씩 누적하여 계수 배열을 완성한다.
	 * 3. 계수 배열을 이용해서 주어진 배열의 마지막 요소부터 첫 요소까지 거꾸로 새 배열에 저장한다.
	 *    하나의 요소를 저장한 후 c[마지막 요소]를 1 감소시킨다. (동일요소가 있다면 바로 앞에 오게 된다)
	 * @param arr
	 * @param d
	 */
	private static void radixSort_Counting(int[] arr, int d) {
		for (int j = 0; j < d; j++) { // d(자릿수)만큼 반복해서 진행 LSB -> MSB
			int[] c = new int[10]; // 계수배열을 생성
			int[] newArr = new int[arr.length]; // 새 배열을 저장할 임시 공간 생성
			int trig = (int) Math.pow(10, j); // LSB->MSB로 한칸씩 이동하게 하기 위한 장치
			for (int i = 0; i < arr.length; i++) {
				c[arr[i]/trig%10]++;
			}
			
			for (int i = 1; i < 10; i++) {
				c[i] += c[i-1];
			}
			
			for (int i = arr.length-1; i >= 0; i--) {
				newArr[c[arr[i]/trig%10]-1] = arr[i];
				c[arr[i]/trig%10]--;
			}
			arr = newArr;
			System.out.println(Arrays.toString(arr));
		}
	}
	
	/**
	 * 계수큐를 이용한 기수정렬
	 * 
	 * 1. 0~9까지 구분할 계수큐를 생성한다 (10개)
	 * 2. LSB -> MSB까지 자리수를 이동하면서 정렬을 진행한다.
	 * 
	 * 계수정렬과 다르게 중복되는 자릿수의 숫자가 다수 있더라도 자릿수를 이동하면서
	 * 정렬할 필요가 없이 큐의 사이즈만큼 출력하면 된다.
	 * @param arr
	 * @param d
	 */
	private static void radixSort_Queue(int[] arr, int d) {
		@SuppressWarnings("unchecked")
		Queue<Integer>[] cq = new Queue[10];
		for (int i = 0; i < 10; i++) { // 모든 계수큐를 초기화
			cq[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < d; i++) { // d(자릿수) 만큼 반복
			int[] newArr = new int[arr.length]; // 새 배열을 저장할 임시 공간 생성
			int trig = (int) Math.pow(10, i); // LSB->MSB로 한칸씩 이동하게 하기 위한 장치
			for (int j = 0; j < arr.length; j++) { // 자릿수에 맞는 계수큐에 원래배열을 이동
				cq[arr[j]/trig%10].add(arr[j]);
			}
			
			for (int j = 0, idx = 0; j < 10; j++) { // 0자리의 계수큐부터 새 배열에 저장
				while (!cq[j].isEmpty()) {
					newArr[idx] = cq[j].remove();
					idx++;
				}
			}
			arr = newArr;
			System.out.println(Arrays.toString(arr));
		}
	}

	public static void main(String[] args) {
		int[] arr3 = {86, 54, 41, 92, 15, 33, 29, 67, 78};
		int[] arr4 = {1, 54, 4165, 92, 15, 33, 29, 67, 782, 0};
		int[] arr2 = {86134334, 5234, 41323, 4392, 1555, 373, 2429, 85667, 444278};
		int[] arr = {984938192, 54598274, 41436625, 92684, 1524574, 3345337, 2467389, 6200227, 782542, 24642};
		int d = 9; // 자릿수
		System.out.println(Arrays.toString(arr));
		long start_c = System.currentTimeMillis();
		radixSort_Counting(arr, d);
		System.out.println("performance(radix use counting): " + (System.currentTimeMillis() - start_c) + " ms");
		long start_q = System.currentTimeMillis();
		radixSort_Queue(arr, d);
		System.out.println("performance(radix use queue): " + (System.currentTimeMillis() - start_q) + " ms");
	}

}
