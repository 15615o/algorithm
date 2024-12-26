package baekjoon.Q2805;

/*
1. 아이디어
- 이진탐색
- trees 배열 정렬 후 최대값의 반 만큼 mid 설정
- left=0, right=trees[N-1], mid=Math.round((left+right)/2)
- trees 배열의 원소마다 반복하며 mid을 뺀 값을 sum에 합산(Long)
- M값과 비교하여 sum값이 작으면 left = mid, 크면 right = mid, 그렇지 않으면 return 후 종료
- 반복문이 다 돌 때 까지 답이 나오지 않으면 return sum > M ? mid : mid + 1
2. 변수
- int N
- long M
- int[] trees
- int left, min, right
- long sum
3. 시간복잡도
- O(Nlog(M)) => 가능
 */

import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());
        int[] trees = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(trees);

        System.out.println(binSearch(trees, M));
    }

    private static int binSearch(int[] trees, long M){
        int left = 0, right = trees[trees.length - 1];
        int result = 0;

        while(left <= right){
            int mid = (left + right) / 2;
            long sum = 0;

            for(int tree : trees){
                if(tree > mid) sum += tree - mid;
            }

            if (sum >= M) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }
}
