package boj;

import java.util.Scanner;

public class TwoXn타일링 {

    /*

    문제

    2×n 크기의 직사각형을 1×2, 2×1 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.
    아래 그림은 2×5 크기의 직사각형을 채운 한 가지 방법의 예이다.

    입력

    첫째 줄에 n이 주어진다. (1 ≤ n ≤ 1,000)

    출력

    첫째 줄에 2×n 크기의 직사각형을 채우는 방법의 수를 10,007로 나눈 나머지를 출력한다.


    풀이

    사용할 수 있는 사각형 : 2 x 1  , 1 x 2

    dp[n] = 2 x n 크기의 사각형을 사용할 수 있는 사각형으로 채운 모든 방법의 수

    dp[n] = dp[n - 2] + ( 2 x 1 사각형 2개 사용 )
    dp[n] = dp[n - 1] + ( 1 x 2 사각형 1개 사용 )
    dp[n] = dp[n - 2] + dp[n - 1]

    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 1 + 2;
    dp[4] = 2 + 3;
    ...

     */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        System.out.println(solution(n));
    }

    private static int solution(final int n) {
        if (n < 3) {
            return n;
        }
        final int[] counts = new int[n + 1];
        counts[1] = 1;
        counts[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            counts[i] = (counts[i - 2] + counts[i - 1]) % 10007;
        }
        return counts[n];
    }
}
