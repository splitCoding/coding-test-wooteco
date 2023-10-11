package boj;

import java.util.Arrays;
import java.util.Scanner;

public class 수찾기 {

    /*

     */
    /*
    문제

    N개의 정수 A[1], A[2], …, A[N]이 주어져 있을 때, 이 안에 X라는 정수가 존재하는지 알아내는 프로그램을 작성하시오.

    입력

    첫째 줄에 자연수 N(1 ≤ N ≤ 100,000)이 주어진다.
    다음 줄에는 N개의 정수 A[1], A[2], …, A[N]이 주어진다.
    다음 줄에는 M(1 ≤ M ≤ 100,000)이 주어진다.
    다음 줄에는 M개의 수들이 주어지는데, 이 수들이 A안에 존재하는지 알아내면 된다. 모든 정수의 범위는 -2^31 보다 크거나 같고 2^31보다 작다.

    출력

    M개의 줄에 답을 출력한다. 존재하면 1을, 존재하지 않으면 0을 출력한다.

    입력 예시

5
4 1 5 2 3
5
1 3 7 9 5

    출력 예시

    1
    1
    0
    0
    1
     */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int N = scanner.nextInt();
        final int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = scanner.nextInt();
        }
        final int M = scanner.nextInt();
        final int[] target = new int[M];
        for (int i = 0; i < M; i++) {
            target[i] = scanner.nextInt();
        }
        System.out.println(solution(numbers, target));
    }

    private static String solution(final int[] numbers, final int[] targets) {
        Arrays.sort(numbers);
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < targets.length; i++) {
            final int current = targets[i];
            stringBuilder.append(binarySearch(numbers, current)).append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    private static int binarySearch(final int[] numbers, final int target) {
        long left = 0;
        long right = numbers.length - 1;

        while (left <= right) {
            int middle = (int) ((left + right) / 2);
            final int current = numbers[middle];
            if (current == target) {
                return 1;
            }
            if (current < target) {
                left = middle + 1;
            }
            if (current > target) {
                right = middle - 1;
            }
        }
        return 0;
    }
}
