package baekjoon.Q11047;
/*
    1. 아이디어
    - 두 번째 줄부터 동전 금액이므로 배열에 저장
    - 역순으로 while문 돌려서 (타겟 / 금액) -> 개수에 합산, (타겟 % 금액) -> 타겟으로 변경
    - while 종료조건은 타겟이 0일 때
    2. 시간 복잡도
    - O(N)
    - 1억 < 2억 -> 가능
    3. 자료구조
    - int[] coins
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
        int[] coins = new int[N];
        int num = N - 1;
        int cnt = 0;

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            coins[i] = Integer.parseInt(st.nextToken());
        }
        while(K > 0){
            cnt += K / coins[num];
            K %= coins[num];
            num--;
        }

        bw.write("" + cnt);
        bw.flush();
        bw.close();
    }
    public static void main(String[] args) throws IOException{
        new Main().solution();
    }
}
