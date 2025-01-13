package baekjoon.Q1697;

/*
1. 아이디어
- BFS
- deque에 -1, +1, *2 계산했을 때와 그 시간을 저장
- deque가 빌 때까지 반복
- 목표 위치에 도달했다면 시간 출력
2. 변수
- int N
- int K
- int sec
3. 시간복잡도
- O(N)
 */

import java.util.*;
import java.io.*;

public class Main {
    private static int K;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.print(bfs(N));
    }
    private static int bfs(int n){
        Deque<int[]> deq = new ArrayDeque<>();
        boolean[] visited = new boolean[100001];

        deq.add(new int[] {n, 0});
        while(!deq.isEmpty()){
            int[] cur = deq.poll();
            int val = cur[0];
            int sec = cur[1];

            if(val == K) return sec;

            if(0 <= val - 1 && !visited[val - 1]){
                visited[val - 1] = true;
                deq.add(new int[] {val - 1, sec + 1});
            }
            if(val + 1 <= 100000 && !visited[val + 1]){
                visited[val + 1] = true;
                deq.add(new int[] {val + 1, sec + 1});
            }
            if(val * 2 <= 100000 && !visited[val * 2]){
                visited[val * 2] = true;
                deq.add(new int[] {val * 2, sec + 1});
            }
        }

        return 0;
    }
}
