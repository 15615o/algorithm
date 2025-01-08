package baekjoon.Q11053;

/*
1. 아이디어
- 전체 배열 반복
- dp 초기값 1로 초기화
- 역순으로 반복하면서 현재 값보다 이전 값이 작으면,
  과거 dp값과 현재 dp값 + 1 중 큰 값을 현재 dp값에 적용
- dp값 중 가장 큰 값 출력
2. 변수
- int N
- int[] arr
- int[] dp
- int result
3. 시간복잡도
- O(N^2)
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int j=0; j<N; j++){
            dp[j] = 1;
            for(int k=j; k>=0; k--){
                if(arr[j] > arr[k]){
                    dp[j] = Math.max(dp[j], dp[k] + 1);
                }
            }
        }

        int result = 0;
        for(int num : dp){
            result = Math.max(num, result);
        }

        System.out.print(result);
    }
}
