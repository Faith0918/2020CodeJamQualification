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
				if((S[i][2] == 0 && S[i][3] == 0)) {
					impossible = true;
					answer = "IMPOSSIBLE";
					break;
				}
				else {
					if(S[i][2] == 0 && S[i][3] == 1) {
						System.out.println("S[i][4] : "+S[i][4]);
						System.out.println(S[i][2]+" "+S[i][3]);
						answer =  answer + 'C';
					}
					else if(S[i][2] == 1 && S[i][3] == 0) {
						System.out.println("S[i][4] : "+S[i][4]);
						System.out.println(S[i][2]+" "+S[i][3]);
						answer= answer+'J';
					}
					else {
						System.out.println("else");
					}
				}
			}
			System.out.println("Case #"+ test_case +": "+ answer);
			
			
		}
		sc.close();
	}
	private static void bfs(int[][] S, int N, int count) {
		if(count == N) {
			return;
		}
		else {
			System.out.println("bfs");
			for(int i = 0; i<N; i++) {
				if(S[i][2] == 0 && S[i][3] == 0) {
					System.out.println("1");
					return;
				}
				else if(S[i][2] == 0) {
					System.out.println("2");
					CheckOverlap(S, N, i);
				}
				else if(S[i][3] == 0) {
					System.out.println("3");
					CheckOverlap(S, N, i);
				}
				else {
					System.out.println("4");
					S[i][2] = 0;
					CheckOverlap(S, N, i);
				}
			}
			bfs(S, N, count+1);
		}
		
	}
	private static void CheckOverlap(int[][] S, int N, int count) {
		int[][] newS = S;
		for(int i = 0; i<N; i++ ) {
			if(i != count) {
				if( (newS[i][1]>newS[count][0] && newS[i][1]<=newS[count][1])||(newS[i][0]>=newS[count][0] && S[i][0]<S[count][1])) {
					if(S[count][2] ==  0 && S[count][3] == 1) {
						S[i][2] = 0;
						CheckOverlap(S, N, i);
						System.out.println("checkoverlap");
					}
					else if(S[count][2] == 1 && S[count][3] == 0) {
						S[i][3] = 0;
						CheckOverlap(S, N, i);
					}
				}
			}
			
		}

		
	}
}
