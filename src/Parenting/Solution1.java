package Parenting;

import java.util.LinkedList;
import java.util.Scanner;


public class Solution1 {
	public boolean overlap() {
		return true;
	}
	public static void main(String[] args) {
		Solution1 sol = new Solution1();
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 1; test_case<=T; test_case++) {
			int N = sc.nextInt();
			String answer = "";
			boolean impossible = false;
			int[][] S = new int[N][5];
			for(int i = 0; i < N; i++) {
				S[i][0] = sc.nextInt();
				S[i][1] = sc.nextInt();
				S[i][2] = 1;//C_available
				S[i][3] = 1;//J_available
				S[i][4] = 0;//0 1 as C, 2 as J, 0 as non
				
			}
			bfs(S, N, 0);
			for(int i = 0; i<N; i++) {
				if(S[i][4] == 0||(S[i][2] == 0 && S[i][3] == 0)) {
					impossible = true;
					answer = "IMPOSSIBLE";
					break;
				}
				else {
					if(S[i][4] == 1) {
						System.out.println("S[i][4] : "+S[i][4]);
						System.out.println(S[i][2]+" "+S[i][3]);
						answer =  answer + 'C';
					}
					else {
						System.out.println("S[i][4] : "+S[i][4]);
						System.out.println(S[i][2]+" "+S[i][3]);
						answer= answer+'J';
					}
				}
			}
			System.out.println("Case #"+ test_case +": "+ answer);
			
			
		}
	}
	private static void bfs(int[][] S, int N, int count) {
		if(count == N) {
			return;
		}
		for(int i = 0; i<N; i++) {
			if(S[i][2] == 0 && S[i][3] == 0) {
				return;
			}
		}
		
		for(int i = count; i<N; i++) {
			
			if(S[i][2]==1 && S[i][3] == 1) {
				S[i][2] = 0;//C allocate
				S[i][4] = 1;
				
				int[][] newS = CheckOverlap(S, N, count);
				bfs(newS, N, count+1);
				S[i][2] = 1;//C allocate
				
			}
			else if(S[i][2] == 1) {
				S[i][4] = 1;
				int[][] newS = CheckOverlap(S,  N, count);
				bfs(newS, N, count+1);
			}
			else {
				S[i][4] = 2;
				int[][] newS = CheckOverlap(S, N, count);
				bfs(newS, N, count+1);
//				S[i][4] = 0;
			}
		}
		
	}
	private static int[][] CheckOverlap(int[][] S, int N, int count) {
		for(int i = 0; i<N; i++ ) {
			if(i != count||!(S[i][2] == 0 &&S[i][4] ==1)||!(S[i][3] ==0 && S[i][4] == 2)) {
				if( (S[i][1]>S[count][0] && S[i][1]<=S[count][1])||(S[i][0]>=S[count][0] && S[i][0]<S[count][1])) {
					if(S[count][4] == 1) {
						S[i][2] = 0;
						CheckOverlap(S, N, i);
					}
					else if(S[count][4] == 2) {
						S[i][3] = 0;
						CheckOverlap(S, N, i);
					}
				}
			}
			
		}
		return S;
		
		
	}
}
