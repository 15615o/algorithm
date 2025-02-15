package baekjoon.Q1260;
/*
1. 아이디어
- DFS 수행
- BFS 수행
2. 변수
- int N, M, V
- ArrayList<Set<Integer>> map
- Queue<Integer> que
- boolean[] visited
- String dfs, bfs
3. 시간복잡도
- O(MlogN)
 */

import java.util.*;
import java.io.*;

public class Main {
    static int N, M, V;
    static ArrayList<Set<Integer>> map;
    static Queue<Integer> que = new ArrayDeque<>();
    static boolean[] dfsVisited, bfsVisited;
    static String dfs, bfs;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        dfs = (V + " ");
        bfs = (V + " ");
        map = new ArrayList<>();
        dfsVisited = new boolean[N+1];
        bfsVisited = new boolean[N+1];

        for(int n=0; n<=N; n++){
            map.add(new TreeSet<>());
        }

        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            map.get(start).add(end);
            map.get(end).add(start);
        }

        dfsVisited[V] = true;
        DFS(V);
        BFS();
        System.out.print(dfs + "\n" + bfs);
    }

    static void DFS(int start){
        for(int node : map.get(start)){
            if(!dfsVisited[node]){
                dfsVisited[node] = true;
                dfs += node + " ";
                DFS(node);
            }
        }
    }

    static void BFS(){
        que.addAll(map.get(V));
        bfsVisited[V] = true;
        while(!que.isEmpty()){
            int node = que.poll();
            if(!bfsVisited[node]){
                bfsVisited[node] = true;
                bfs += node + " ";
                que.addAll(map.get(node));
            }
        }
    }
}
