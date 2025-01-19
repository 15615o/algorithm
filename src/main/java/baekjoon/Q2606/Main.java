package baekjoon.Q2606;

/*
1. 아이디어
- BFS
- 큐에 노드 정보 저장
- 연결된 노드의 정보 큐에 저장
- 탐색하는 노드 방문 처리
- 개수 1 증가
- 큐가 빌 때까지 반복
2. 변수
- ArrayDeque deq
- boolean[] visited
- int cnt
3. 시간복잡도
- O(N+E)
 */

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        List<Set<Integer>> network = new ArrayList<>();
        Deque<Integer> deq = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        int cnt = 0;

        for(int i=0; i<=N; i++){
            network.add(new HashSet<>());
        }

        for(int i=0; i<K; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            network.get(a).add(b);
            network.get(b).add(a);
        }

        deq.add(1);
        visited[1] = true;
        while(!deq.isEmpty()){
            int cur = deq.poll();
            for(int num : network.get(cur)){
                if(!visited[num]){
                    visited[num] = true;
                    deq.add(num);
                    cnt++;
                }
            }
        }

        System.out.print(cnt);
    }
}
