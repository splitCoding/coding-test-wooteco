package boj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 음계 {

    /**
     * 문제
     *
     * 다장조는 c d e f g a b C, 총 8개 음으로 이루어져있다. 이 문제에서 8개 음은 다음과 같이 숫자로 바꾸어 표현한다. c는 1로, d는 2로, ..., C를 8로 바꾼다.
     *
     * 1부터 8까지 차례대로 연주한다면 ascending, 8부터 1까지 차례대로 연주한다면 descending, 둘 다 아니라면 mixed 이다.
     *
     * 연주한 순서가 주어졌을 때, 이것이 ascending인지, descending인지, 아니면 mixed인지 판별하는 프로그램을 작성하시오.
     */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final List<Integer> input = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            input.add(scanner.nextInt());
        }
        System.out.println(solution(input));
    }

    private static String solution(final List<Integer> input) {
        Order currentOrder = Order.MIXED;
        for (int i = 0; i < input.size() - 1; i++) {
            if (input.get(i) < input.get(i + 1)) {
                if (currentOrder == Order.DESC) {
                    return Order.MIXED.message;
                }
                currentOrder = Order.ASC;
                continue;
            }
            if (input.get(i) > input.get(i + 1)) {
                if (currentOrder == Order.ASC) {
                    return Order.MIXED.message;
                }
                currentOrder = Order.DESC;
                continue;
            }
            break;
        }
        return currentOrder.message;
    }

    enum Order {
        ASC("ascending"),
        DESC("descending"),
        MIXED("mixed");

        protected final String message;

        Order(final String message) {
            this.message = message;
        }
    }
}
