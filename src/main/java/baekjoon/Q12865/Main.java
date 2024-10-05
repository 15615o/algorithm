package baekjoon.Q12865;

/*
    1. 아이디어
    - 이중포문 사용
    - 2차원 배열의 행에 물품, 열에 넣을 수 있는 무게로 가능한 가치를 값으로 저장
    - 현재 위치에 값을 넣을 때 현재 무게의 바로 이전에 있는 배열의 값과 (현재 무게 - 확인할 무게의 값) + 현재 아이템의 값을 비교하여 큰 값으로 교환
    2. 시간복잡도
    - O(N * K)
    3. 변수
    - int N
    - int K
    - int[] weights
    - int[] values
    - int[][] dp
 */

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] weights = new int[N];
        int[] values = new int[N];
        int[][] dp = new int[K+1][N+1];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            weights[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
        }

        for(int k=1; k<=K; k++){ // 7
            for(int n=1; n<=N; n++){ // 4
                if(k < weights[n-1]) {
                    dp[k][n] = dp[k][n-1];
                } else if(k >= weights[n-1]){
                    dp[k][n] = Math.max(dp[k][n-1], dp[k - weights[n-1]][n-1] + values[n-1]);
                }
            }
        }

        bw.write(Integer.toString(dp[K][N]));
        bw.flush();
        bw.close();
    }
}
