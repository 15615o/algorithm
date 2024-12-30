package baekjoon.Q9095;

/*
1. 아이디어
- 1: 1, 2: 2, 3: 4, 4: 7, 5: 13, 6: 24 => 점화식 = (arr[n-1] + arr[n-2] + arr[n-3])
- 각 목표 수 까지 반복하며 점화식 결과값 arr에 저장
- 목표 수에 도달하면 bw에 줄바꿔서 문자 저장
2. 변수
- int T
- int n
3. 시간복잡도
- O(N)
 */

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        int[] testCases = new int[T + 1];
        int max = 0;

        for(int i=1; i<=T; i++){
            testCases[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, testCases[i]);
        }

        int[] arr = new int[max + 1];

        for(int j=1; j<=max; j++){
            if(j == 1) arr[j] = 1;
            else if(j == 2) arr[j] = 2;
            else if(j == 3) arr[j] = 4;
            else arr[j] = arr[j - 1] + arr[j - 2] + arr[j - 3];
        }

        for(int k=1; k<=T; k++){
            bw.write(arr[testCases[k]] + "\n");
        }
        bw.flush();
        bw.close();
    }
}
