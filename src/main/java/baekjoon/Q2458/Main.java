package baekjoon.Q2458;

/*
    1. 아이디어
    - 플로이드
    - 2차원 배열 초기값 1억으로 초기화. 자기 자신은 0으로.
    - 2차원 배열에 노드 정보 넣기. 값은 1로 넣기
    - 3중포문 돌면서 비교
    - 연결됨만 확인하면 되기 때문에 위치 바꿔서 둘다 비교. map[a][b] > 1억 || map[b][a] > 1억
    2. 시간복잡도
    - O(M^3) => 1억2500 => 가능
    3. 변수
    - int[][] map
    - int cnt
 */

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args0) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N+1][N+1];
        int cnt = 0;

        // map 배열 초기화(1억)
        for(int i=0; i<N+1; i++){
            Arrays.fill(map[i], 100000000);
            map[i][i] = 0;
        }

        // map 배열에 입력 정보 삽입
        for(int j=0; j<M; j++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            map[start][end] = 1;
        }

        for(int k=1; k<N+1; k++){ // 경유 노드
            for(int l=1;l<N+1; l++){ // 시작 노드
                for(int m=1; m<N+1; m++) { // 종료 노드
                    if(map[l][m] > map[l][k] + map[k][m]) map[l][m] = map[l][k] + map[k][m];
                }
            }
        }

        for(int n=1; n<N+1; n++){
            int sum = 0;
            for(int o=1;o<N+1;o++){
                if(map[n][o] < 100000000 || map[o][n] < 100000000) sum++;
            }
            if(sum == N) cnt++;
        }

        bw.write(Integer.toString(cnt));
        bw.flush();
        bw.close();
    }
}
