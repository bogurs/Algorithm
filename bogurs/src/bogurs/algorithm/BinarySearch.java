package bogurs.algorithm;

import java.util.Scanner;

public class BinarySearch {
	
	static int count = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[10];
		int goalInt = sc.nextInt();
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i+1;
		}
		
		binarySearch(arr, goalInt);
		
		sc.close();
	}

	private static int binarySearch(int[] arr, int goalInt) {
		int low = 0;
		int high = arr.length - 1;
		
		while (low <= high) {
			int mid = (low + high) / 2;
			int midVal = arr[mid];
			
			if (midVal < goalInt) {
				low = mid + 1;
			} else if (midVal > goalInt) {
				high = mid - 1;
			} else {
				return mid;
			}
		}
		return -(low + 1);
	}

}
