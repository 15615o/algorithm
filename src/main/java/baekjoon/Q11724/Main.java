package baekjoon.Q11724;

/*
1. 아이디어
- M으로 반복문 돌며 양방향으로 그래프 저장(ArrayList<ArrayList>)
- 방문 배열 기준으로 반복문 돌며 순서대로 큐에 저장
 - 요소가 방문하지 않았으면 큐에 삽입, 방문처리
 - 큐가 빌 때까지 반복(while)
  - 관련 노드 큐에 추가, 방문처리
2. 변수
- int N, M
- ArrayList<ArrayList> map
- boolean[] visited
- Queue<int> que
- int cnt
3. 시간복잡도
- O(N+M)
 */

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> map = new ArrayList<>();
        boolean[] visited = new boolean[N+1];
        Queue<Integer> que = new LinkedList<>();
        int cnt = 0;

        for(int n=0; n<=N; n++){
            map.add(new ArrayList<>());
        }

        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            map.get(u).add(v);
            map.get(v).add(u);
        }

        for(int i=1; i<visited.length; i++){
            if(!visited[i]){
                cnt++;
                visited[i] = true;
                que.addAll(map.get(i));
                while(!que.isEmpty()){
                    int e = que.poll();
                    if(!visited[e]){
                        visited[e] = true;
                        que.addAll(map.get(e));
                    }
                }
            }
        }

        System.out.print(cnt);
    }
}
