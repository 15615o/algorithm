package baekjoon.Q1012;

/*
1. 아이디어
- 테스트케이스 수를 반복(for)
- M, N, K를 저장
- visited를 생성
- map을 생성하고 해당 좌표에 1을 표시
- M과 N으로 이중포문 돌며 탐색
- 현재 좌표에 방문하지 않았고 1이면 큐에 삽입, cnt + 1
 - while문으로 BFS 진행
 - 현재 좌표 방문 처리
 - 경계 밖으로 나가지 않는 선에서 주변에 1이 있는지 탐색
 - 1이 있으면 큐에 삽입
- 테스트 케이스 반복문 마지막에 cnt값 출력
2. 변수
- int T
- int M
- int N
- int K
- int X
- int Y
- int[][] map
- boolean[][] visited
- Queue que
- int cnt
3. 시간복잡도
- O(T*N*N)
 */

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        for(int i=0; i<T; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[][] map = new int[M][N];
            boolean[][] visited = new boolean[M][N];
            Queue<int[]> que = new LinkedList<>();
            int cnt = 0;

            for(int k=0; k<K; k++){
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());

                map[X][Y] = 1;
            }

            for(int n=0; n<N; n++){
                for(int m=0; m<M; m++){
                    if(!visited[m][n] && map[m][n] == 1){
                        cnt += 1;
                        que.add(new int[] {m, n});
                        visited[m][n] = true;
                        while(!que.isEmpty()){
                            int[] addr = que.poll();
                            int x = addr[0];
                            int y = addr[1];

                            for(int j=0; j<4; j++){
                                int ex = x + dx[j];
                                int ey = y + dy[j];
                                if(0 <= ex && ex < M && 0 <= ey && ey < N && !visited[ex][ey] && map[ex][ey] == 1){
                                    que.add(new int[] {ex, ey});
                                    visited[ex][ey] = true;
                                }
                            }
                        }
                    }
                }
            }
            bw.write(cnt + "\n");
        }
        bw.flush();
        bw.close();
    }
}
