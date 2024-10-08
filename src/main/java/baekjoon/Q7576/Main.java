package baekjoon.Q7576;

/*
    1. 아이디어
    - 큐가 빌 때까지 while문 반복하면서 상하좌우 중 0이고, 방문하지 않은 주소값 1로 변경, 방문체크
    - while문 안에서 포문 돌기 전에 현재 큐의 길이 저장 후 반복
    - 변경이 생기면 boolean 값에 true 체크 하고 res++
    2. 시간복잡도
    - O(N*M)
    3. 변수
    - Queue q
    - int[][] map
    - boolean[][] chk
    - int res
    - int isChk
 */

import java.util.*;
import java.io.*;
import java.awt.*;

public class Main {
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] map = new int[M][N];
        boolean[][] chk = new boolean[M][N];
        Queue<Point> q = new LinkedList<>();
        int res = 0;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                int tomato = Integer.parseInt(st.nextToken());
                map[j][i] = tomato;
                if(tomato == 1){
                    q.add(new Point(i, j));
                    chk[j][i] = true;
                }
            }
        }

        while(!q.isEmpty()){
            int length = q.size();
            boolean isChk = false;

            for(int k=0; k<length; k++){
                Point now = q.poll();

                for(int i=0; i<4; i++){
                    int ex = now.x + dx[i];
                    int ey = now.y + dy[i];

                    if(ex >= 0 && ex < N && ey >= 0 && ey < M && map[ey][ex] == 0 && !chk[ey][ex]){ // 익지 않은 새로운 토마토이면
                        chk[ey][ex] = true;
                        map[ey][ex] = 1;
                        q.add(new Point(ex, ey));
                        isChk = true;
                    }
                }
            }
            if(isChk) res++;
        }

        for(int l=0; l<N; l++){
            for(int m=0; m<M; m++){
                if(map[m][l] == 0) res = -1;
            }
        }

        bw.write(Integer.toString(res));
        bw.flush();
        bw.close();
    }
}
