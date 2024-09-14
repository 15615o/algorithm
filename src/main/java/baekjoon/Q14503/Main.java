package baekjoon.Q14503;

/*
    1. 아이디어
    - 이중포문(초기값은 로봇의 위치) 돌면서 아래 조건 수행
     1) 현재 칸 == 1 -> 2로 수정
     2) 북, 서, 남, 동 순으로 반복문 돌면서 탐색후 0이 있으면 해당 위치 이동 후 continue
     3) 0이 없으면 현재 방향에서 뒤로 후진 후 continue
    2. 시간복잡도
    - O(N*M) -> 2,500 < 2억 -> 가능
    3. 자료구조
    - int[] map
    - int[] dy = {-1, 0, 1, 0}
    - int[] dx = {0, 1, 0, -1}
    - int ey
    - int ex
    - int cnt
 */

import java.util.*;
import java.io.*;

public class Main {
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer infoSt = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(infoSt.nextToken());
        int M = Integer.parseInt(infoSt.nextToken());
        int cnt = 0;

        StringTokenizer drctnSt = new StringTokenizer(br.readLine());
        int ey = Integer.parseInt(drctnSt.nextToken());
        int ex = Integer.parseInt(drctnSt.nextToken());
        int d = Integer.parseInt(drctnSt.nextToken());
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};
        int[][] map = new int[N][M];
        boolean isSearch = true;

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(isSearch){
            boolean isMoved = false;

            if(map[ey][ex] == 0){
                map[ey][ex] = 2; // 청소 수행
                cnt++; // 청소한 칸 개수 증가
            }

            for(int k=0; k<4; k++) { // 반 시계 방향으로 돌면서 청소 안한 곳 확인
                d = (d + 3) % 4;
                int ty = ey + dy[d]; // 봐야할 y좌표
                int tx = ex + dx[d]; // 봐야할 x좌표

                if(map[ty][tx] == 0) { // 바라보는 곳이 청소가 안돼있으면 이동
                    ey = ty;
                    ex = tx;
                    isMoved = true;
                    break;
                }
            }

            if(!isMoved) { // 4 방향이 모두 청소가 된 경우
                // 뒤로 이동(남쪽)
                ey += dy[(d + 2) % 4];
                ex += dx[(d + 2) % 4];
                if(map[ey][ex] == 1) {
                    isSearch = false;
                }
            }
        }

        bw.write(Integer.toString(cnt));
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException{
        new Main().solution();
    }
}