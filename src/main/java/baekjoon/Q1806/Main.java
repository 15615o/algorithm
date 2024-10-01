package baekjoon.Q1806;

/*
    1. 아이디어
    - while문
    - 합이 S 이상이면 min과 cnt 비교하여 작은 값으로 갱신, 처음 더했던 배열의 값 빼고 cnt 감소, 처음 더했던 배열의 주소값 증가
    - 합이 S 미만이면 마지막에 더했던 주소값+1 만큼 S에 더하기
    -
    2. 시간복잡도
    - O(N) = 가능
    3. 변수
    - int[] arr
    - int st
    - int ed
    - int cnt
    - int sum
    - int min
 */

import java.util.*;
import java.io.*;

public class Main {
    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer infoSt = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(infoSt.nextToken());
        int S = Integer.parseInt(infoSt.nextToken());
        int[] arr = new int[N];
        int start = 0;
        int end = 0;
        int cnt = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        while(end < N || sum >= S){
            if(sum < S){
                sum += arr[end];
                end++;
                cnt++;
            } else {
                min = Math.min(cnt, min);
                sum -= arr[start];
                start++;
                cnt--;
            }
        }

        min = min == Integer.MAX_VALUE ? 0 : min;

        bw.write(Integer.toString(min));
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException{
        new Main().solution();
    }
}
