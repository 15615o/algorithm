package baekjoon.Q1715;

/*
    1. 아이디어
    - heap에 자료 삽입
    - N이 1보다 크면 heap에서 값 2개 빼서 sum에 저장, 뺀 2개는 더해서 다시 heap에 저장
    - heap의 크기가 1이 될 때까지 while문 반복
    2. 시간복잡도
    - O(N) => 가능
    3. 변수
    - int N
    - PriorityQueue pq
    - int sum
 */

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int sum = 0;

        for(int i=0; i<N; i++){
            pq.add(Integer.parseInt(br.readLine()));
        }

        while(pq.size() > 1){
            int num1 = pq.remove();
            int num2 = pq.remove();
            sum += num1 + num2;
            pq.add(num1 + num2);
        }

        bw.write(Integer.toString(sum));
        bw.flush();
        bw.close();
    }
}
