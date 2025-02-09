package baekjoon.Q11403;

/*
1. 아이디어
- 인접노드 정보 저장
- N번 반복하면서 인접노드가 있으면 해당 위치 1로 수정
2. 변수
- ArrayList<ArrayList<Integer>> map
- int[][] res
3. 시간복잡도
- O(N^2)
 */

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> map = new ArrayList<>();
        int[][] res = new int[N][N];
        Queue<Integer> que = new ArrayDeque<>();

        for(int n=0; n<N; n++){
            map.add(new ArrayList<>());
        }

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                if("1".equals(st.nextToken())){
                    map.get(i).add(j);
                }
            }
        }

        for(int n=0; n<N; n++){
            que.addAll(map.get(n));
            while(!que.isEmpty()){
                int addr = que.poll();
                if(res[n][addr] == 0){
                    res[n][addr] = 1;
                    que.addAll(map.get(addr));
                }
            }
        }

        for(int[] arr : res){
            for(int n : arr){
                bw.write(n + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }
}
