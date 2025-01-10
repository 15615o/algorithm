package baekjoon.Q11055;

/*
1. 아이디어
- 이중포문 돌면서 이전의 값과 각각 더한 다음, 최대값을 저장.
- 두 번째 반복문을 빠져나올 때 dp값을 최대값으로 저장.(각 단계에서 취할 수 있는 최대 값)
- dp 배열을 반복하며 최대값 추출
2. 변수
- int N
- int[] arr
- int[] dp
- int result
3. 시간복잡도
- O(N^2)
 */

import java.util.*;
import java.io.*;

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

        dp[0] = arr[0];
        for(int i=1; i<N; i++){
            int max = arr[i];
            for(int j=i-1; j>=0; j--){
                if(arr[j] < arr[i]){
                    max = Math.max(max, arr[i] + dp[j]);
                }
            }
            dp[i] = max;
        }

        int result = 0;
        for(int i=0; i<N; i++){
            result = Math.max(result, dp[i]);
        }

        System.out.print(result);
    }
}
