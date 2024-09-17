package Q2559;

/*
    1. 아이디어
    - K만큼 숫자 미리 더하고 N 만큼 반복문 돌면서 앞 숫자는 빼고 뒤 숫자는 더해서 max와 비교하여 값 변경
    2. 시간복잡도
    - O(N*2) -> O(N) -> 100,000 -> 가능
    3. 자료구조
    - int[] arr
    - int current -> 100 * 100,000 = 100,000,000 < 2억 -> 가능
    - int max
 */

import java.util.*;
import java.io.*;

public class Main {
    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer infoSt = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(infoSt.nextToken());
        int K = Integer.parseInt(infoSt.nextToken());
        int max = 0;
        int current = 0;
        int[] arr = new int[N];

        // arr initial
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // max 초기값 세팅
        for(int j=0; j<K; j++){
            max = current += arr[j]; // max와 current 값 동시에 세팅
        }

        // 투포인터
        for(int k=0; k<N-K; k++){
            current -= arr[k];// 앞날 하나 빼고
            current += arr[k+K]; // 뒤날 하나 더하기
            if(max < current) max = current;
        }

        bw.write(Integer.toString(max));
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException{
        new Main().solution();
    }
}