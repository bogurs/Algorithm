package bogurs.algorithm.offline.bruteforce.bitmask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BitOperation {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		int maxN = 20;
		
		int res=0;
		StringBuilder sb = new StringBuilder();
		while(n-- > 0){
			String line = bf.readLine();
			if("all".equals(line)){
				res = (1<<(maxN+1))-1;
			} else if("empty".equals(line)){
				res = 0;
			} else {
				String[] lintSpl = line.split(" ");
				int x = Integer.parseInt(lintSpl[1]);
				if("remove".equals(lintSpl[0])){
					res = res&~(1<<x);
				} else if("check".equals(lintSpl[0])){
					if((res&(1<<x))!=0){
						sb.append("1\n");
					} else {
						sb.append("0\n");
					}
				} else if("toggle".equals(lintSpl[0])){
					res = res^(1<<x);
				} else if("add".equals(lintSpl[0])){
					res = res|(1<<x);
				} 
			}
		}
		System.out.println(sb);
		bf.close();
	}
}