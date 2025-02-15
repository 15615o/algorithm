package baekjoon.Q2644;

/*
1. 아이디어
- map에 촌수 정보 저장(양방향)
- start에서 end까지의 최단 경로 탐색
2. 변수
- int N, start, end, M, chons
- ArrayList<ArrayList<Integer>> map
3. 시간복잡도
- O(N+M)
 */

import java.util.*;
import java.io.*;

public class Main {
    static int N, M, start, end;
    static int chons = Integer.MAX_VALUE;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];

        for(int i=0; i<=N; i++){
            map.add(new ArrayList<>());
        }

        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            map.get(parent).add(child);
            map.get(child).add(parent);
        }

        dfs(start, 0);
        System.out.print(chons == Integer.MAX_VALUE ? -1 : chons);
    }

    static void dfs(int s, int n){
        if(s == end){
            chons = Math.min(chons, n);
            return;
        }
        for(int person : map.get(s)){
            if(!visited[person]){
                visited[person] = true;
                dfs(person, n+1);
                visited[person] = false;
            }
        }
    }
}
