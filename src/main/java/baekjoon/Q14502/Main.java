package baekjoon.Q14502;

/*
1. 아이디어
- map에 연구소 정보 저장
- 2의 위치 ArrayList에 저장
- 0의 위치 ArrayList에 저장
- 벽 세울 수 있는 조합 확인
    - 3개의 벽을 세웠으면 바이러스 시뮬레이션(BFS)
- 바이러스 시뮬레이션
    - 큐에 바이러스 정보 저장 후 while문으로 반복
    - 가능한 범위 탐색 후 큐에 저장
    - tempMap에 탐색한 곳 2 처리
    - while문 종료 후 map2 돌며 0의 개수 카운트
    - max값으로 res 적용
2. 변수
- int N, M, maxSafeArea
- int[][] map
- ArrayList<int> emptySpaces
- ArrayList<int> viruses
- Queue<int[]> que
- int cnt
3. 시간복잡도
- O((N*M)^4)
 */

import java.util.*;
import java.io.*;

public class Main {
    static int N, M, maxSafeArea = 0;
    static int[][] map;
    static ArrayList<int[]> emptySpaces = new ArrayList<>();
    static ArrayList<int[]> viruses = new ArrayList<>();
    static final int[] dy = new int[]{1, 0, -1, 0};
    static final int[] dx = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m=0; m<M; m++){
                map[n][m] = Integer.parseInt(st.nextToken());
                if(map[n][m] == 0) emptySpaces.add(new int[]{n, m});
                if(map[n][m] == 2) viruses.add(new int[]{n, m});
            }
        }

        buildWalls(0, 0);
        System.out.print(maxSafeArea);
    }

    static void buildWalls(int start, int count){
        if(count == 3){
            maxSafeArea = Math.max(maxSafeArea, simulateViruses());
            return;
        }

        for(int i=start; i<emptySpaces.size(); i++){
            int[] addr = emptySpaces.get(i);
            map[addr[0]][addr[1]] = 1;
            buildWalls(i + 1, count + 1);
            map[addr[0]][addr[1]] = 0;
        }
    }

    static int simulateViruses(){
        int[][] tempMap = new int[N][M];
        for(int n=0; n<N; n++){
            tempMap[n] = map[n].clone();
        }

        Queue<int[]> que = new ArrayDeque<>(viruses);
        while(!que.isEmpty()){
            int[] addr = que.poll();
            int n = addr[0];
            int m = addr[1];

            for(int i=0; i<4; i++){
                int ey = dy[i] + n;
                int ex = dx[i] + m;
                if(0 <= ey && ey < N && 0 <= ex && ex < M && tempMap[ey][ex] == 0){
                    tempMap[ey][ex] = 2;
                    que.add(new int[]{ey, ex});
                }
            }
        }

        int cnt = 0;
        for(int[] arr : tempMap){
            for(int num : arr){
                if(num == 0) cnt++;
            }
        }

        return cnt;
    }
}