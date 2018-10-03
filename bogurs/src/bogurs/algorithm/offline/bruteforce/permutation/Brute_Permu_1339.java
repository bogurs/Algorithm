package bogurs.algorithm.offline.bruteforce.permutation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 단어 수학
 * 
 * 주어진 대문자영문을 숫자(0~9)로 임의로 변경했을 때 최대값
 * 
 * 단어는 최대 10개 문장이 주어진다.
 * 단어를 숫자로 변경하는 것을 다음수열을 통해 구현하고
 * 이 때 최대값을 찾는다.
 * @author thsong
 *
 */
public class Brute_Permu_1339 {
	
	private static boolean next_permutation(int[] arr) {
		int i = arr.length - 1;
		while (i > 0 && arr[i-1] > arr[i]) i--;
		
		if (i <= 0) return false;
		
		int j = arr.length - 1;
		while (arr[i-1] > arr[j]) j--;
		
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		
		for (int x = i, y = arr.length-1; x < y; x++, y--) {
			temp = arr[x];
			arr[x] = arr[y];
			arr[y] = temp;
		}
		
		return true;
	}

	static int[] alpha = new int[256];
    static int calc(String[] a, Character[] letters, int[] d) {
        int m = letters.length;
        int sum = 0;
        for (int i=0; i<m; i++) {
            alpha[letters[i]] = d[i];
        }
        for (String s : a) {
            int now = 0;
            for (char x : s.toCharArray()) {
                now = now * 10 + alpha[x];
            }
            sum += now;
        }
        return sum;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] a = new String[n];
        HashSet<Character> s = new HashSet<>();
        for (int i=0; i<n; i++) {
            a[i] = sc.next();
            for (char x : a[i].toCharArray()) {
                s.add(x);
            }
        }
        Character[] letters = s.toArray(new Character[s.size()]);
        int m = letters.length;
        int[] d = new int[m];
        for (int i=0; i<m; i++) {
            d[i] = 9-i;
        }
        Arrays.sort(d);
        int ans = 0;
        do {
            int now = calc(a, letters, d);
            if (ans < now) {
                ans = now;
            }
        } while(next_permutation(d));
        System.out.println(ans);
        
        sc.close();
    }

}
