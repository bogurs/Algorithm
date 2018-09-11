package bogurs.algorithm.prime;

/**
 * 소수: 약수가 1과 자기 자신 밖에 없는 수
 *  N이 소수가 되려면, 2보다 크거나 같고 N-1보다 작거나 같은 자연수로 나누어 떨어지면 안된다.
 * @author thsong
 *
 */
public class Prime {
	
	private static void printAllPrimeNumbers(int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i < n; i++) {
			boolean isPrime = true;
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				sb.append(i + ", ");
			}
		}
		
		System.out.println(sb.toString());
	}

	public static void main(String[] args) {
		int n = 100;
		printAllPrimeNumbers(n);
	}

}
