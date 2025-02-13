package baekjoon.Q7569;

/*
1. 아이디어
- 3차원 배열에 정보 저장, -1은 방문 배열에 true 체크, 1이면 큐에 저장
- 큐가 빌 때까지 while문 반복
 - cnt 증가, 큐가 빌 때까지 while문 반복
  - 현재 주소 방문처리
  - 상, 하, 전, 후, 좌, 우가 탐색 가능하고 0이면 해당 값 큐2에 저장
  - while문 끝나면 큐 = 큐2
2. 변수
- int M, N, H
- int[][][] map
- Quque<int[]> que
- Quque<int[]> que2
3. 시간복잡도
- O(H*N*M)
 */

import java.util.*;
import java.io.*;

public class Main {
    private static int[] dz = new int[] {1, -1, 0, 0, 0, 0};
    private static int[] dy = new int[] {0, 0, 1, 0, -1, 0};
    private static int[] dx = new int[] {0, 0, 0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[][][] map = new int[H][N][M];
        boolean[][][] visited = new boolean[H][N][M];
        Queue<int[]> que = new ArrayDeque<>();
        Queue<int[]> que2 = new ArrayDeque<>();
        int res = -1;

        for(int h=0; h<H; h++){
            for(int n=0; n<N; n++){
                st = new StringTokenizer(br.readLine());
                for(int m=0; m<M; m++){
                    map[h][n][m] = Integer.parseInt(st.nextToken());
                    if(map[h][n][m] == 1) que.add(new int[] {h, n, m});
                    if(map[h][n][m] != 0) visited[h][n][m] = true;
                }
            }
        }

        while(!que.isEmpty()){
            res++;
            while(!que.isEmpty()){
                int[] addr = que.poll();
                int h = addr[0];
                int n = addr[1];
                int m = addr[2];

                for(int i=0; i<6; i++){
                    int ez = dz[i] + h;
                    int ey = dy[i] + n;
                    int ex = dx[i] + m;
                    if(0<=ez && ez<H && 0<=ey && ey <N && 0<=ex && ex<M && !visited[ez][ey][ex]){
                        visited[ez][ey][ex] = true;
                        que2.add(new int[]{ez, ey, ex});
                    }
                }
            }
            que.addAll(que2);
            que2.clear();
        }

        boolean isAllKill = true;
        for(int h=0; h<H; h++){
            for(int n=0; n<N; n++){
                for(int m=0; m<M; m++){
                    if(!visited[h][n][m]) {
                        isAllKill = false;
                        break;
                    }
                }
            }
        }
        System.out.print(isAllKill ? res : -1);
    }
}
