package baekjoon.Q11725;

/*
1. 아이디어
- map에 트리 정보 저장
- DFS 탐색 하며 하위 노드를 주소로 하는 배열에 상위 노드 정보 저장
2. 변수
- int N
- ArrayList<ArrayList<Integer>> map
- boolean[] visited
- int res
3. 시간복잡도
- O(N)
 */

import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    static int[] res;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        res = new int[N+1];

        for(int i=0; i<=N; i++){
            map.add(new ArrayList<>());
        }

        for(int n=0; n<N-1; n++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            map.get(start).add(end);
            map.get(end).add(start);
        }

        dfs(1);
        for(int pNode = 2; pNode<res.length; pNode++){
            System.out.println(res[pNode]);
        }
    }

    static void dfs(int start){
        for(int cNode : map.get(start)){
            if(!visited[cNode]){
                visited[cNode] = true;
                res[cNode] = start;
                dfs(cNode);
            }
        }
    }
}
