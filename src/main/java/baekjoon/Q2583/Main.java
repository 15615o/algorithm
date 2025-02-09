package baekjoon.Q2583;

/*
1. 아이디어
- map에 직사각형 정보 저장(O(N*M*K))
- map에 false인 부분만 BFS 탐색
 - map을 반복하며 false인 부분의 좌표를 que에 저장
 - que가 빌 때까지 반복하며 주변 좌표 탐색
 - 주변에 0이 있으면 que에 추가
- 개수와 칸 수(넓이) 출력
 - 칸 수는 오름차순으로 정렬하여 출력
2. 변수
- int M, N, K
- boolean[][] map
- Queue<int[]> que
- ArrayList<Integer> res
3. 시간복잡도
- O(N*M*K)
 */

import java.util.*;
import java.io.*;

public class Main {
    private static int[] dy = new int[] {-1, 0, 1, 0};
    private static int[] dx = new int[] {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[][] map = new boolean[M][N];
        Queue<int[]> que = new LinkedList<>();
        ArrayList<Integer> res = new ArrayList<>();

        for(int k=0; k<K; k++){
            st = new StringTokenizer(br.readLine());
            int ld = Integer.parseInt(st.nextToken());
            int rd = Integer.parseInt(st.nextToken());
            int lu = Integer.parseInt(st.nextToken());
            int ru = Integer.parseInt(st.nextToken());
            for(int i=rd; i<ru; i++){
                for(int j=ld; j<lu; j++){
                    map[i][j] = true;
                }
            }
        }

        int cnt = 0;
        for(int m=0; m<M; m++){
            for(int n=0; n<N; n++){
                if(!map[m][n]){
                    cnt++;
                    que.add(new int[] {m, n});
                    res.add(0);
                    while(!que.isEmpty()){
                        int[] target = que.poll();
                        if(!map[target[0]][target[1]]){
                            map[target[0]][target[1]] = true;
                            res.set(cnt - 1, res.get(cnt - 1) + 1);

                            for(int i=0; i<4; i++){
                                int ey = dy[i] + target[0];
                                int ex = dx[i] + target[1];
                                if(0 <= ey && ey < M && 0 <= ex &&  ex < N && !map[ey][ex]){
                                    que.add(new int[] {ey, ex});
                                }
                            }
                        }
                    }
                }
            }
        }

        Collections.sort(res);
        System.out.println(res.size());
        for(int area : res){
            System.out.print(area + " ");
        }
    }
}
