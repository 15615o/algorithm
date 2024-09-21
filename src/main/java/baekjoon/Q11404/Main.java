package baekjoon.Q11404;

/*
    1. 아이디어
    - 모든 점 -> 모든 점 => 플로이드
    - 비용 배열 MAX_VALUE로 초기화
    - 간선의 비용 대입
    - 3중 포문 돌면서 현재 비용보다 작으면 갱신
    2. 시간복잡도
    - 플로이드: O(V^3)
    3. 변수
    - 거리 배열: int[][] => 10^2 * 10^5 = 10^7 => 가능
 */

import java.util.*;
import java.io.*;

public class Main {
    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] res = new int[n+1][n+1]; // 1부터 편하게 사용하기 위함

        // 거리 배열 초기화
        for(int i=1; i<n+1; i++){
            for(int j=0; j<n+1; j++){
                if(i == j){
                    res[i][j] = 0; // 자기 자신에서 자기 자신으로 가는 경우 0으로 초기화
                } else{
                    res[i][j] = 100000000; // 그 외는 MAX값으로 초기화
                }
            }
        }

        for(int k=0; k<m; k++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            res[a][b] = Math.min(res[a][b], c); // 노선이 1개가 아니기 때문에 최소 비용으로 교체
        }

        for(int x=1; x<n+1; x++){ // 경유
            for(int y=1; y<n+1; y++){ // 시작
                for(int z=1; z<n+1; z++){ // 도착지
                    res[y][z] = Math.min(res[y][z], (res[y][x] + res[x][z]));
                }
            }
        }

        // 출력
        for(int l=1; l<n+1; l++){
            for(int o=1; o<n+1; o++){
                if(o > 1) sb.append(" ");
                if(res[l][o] == 100000000) sb.append("0");
                else sb.append(res[l][o]);
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException{
        new Main().solution();
    }
}
