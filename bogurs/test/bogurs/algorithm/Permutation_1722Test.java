package bogurs.algorithm;

import org.junit.jupiter.api.Test;

class Permutation_1722Test {

	@Test
	void factorial() {
		long beforeT = System.currentTimeMillis();
		System.out.println(Permutation_1722.getFact(20));
		System.out.println(System.currentTimeMillis() - beforeT);
		
		beforeT = System.currentTimeMillis();
		System.out.println(Permutation_1722.getFactRecur(20));
		System.out.println(System.currentTimeMillis() - beforeT);
	}
	
	@Test
	void printOrderOfPermutation() {
		int n = 20;
		int[] reqPermu = {20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
		Permutation_1722.printOrderOfPermutation(reqPermu, n);
	}
	
	@Test
	void printPermutation() {
		int n = 20;
		long permuOrder = Long.parseLong("2432902008176640000");
		Permutation_1722.printPermutation(permuOrder, n);
	}

}
