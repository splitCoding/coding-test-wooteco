package boj;

import java.util.Scanner;
import java.util.Stack;

public class 스택수열 {

    /**
     * 스택 (stack)은 기본적인 자료구조 중 하나로, 컴퓨터 프로그램을 작성할 때 자주 이용되는 개념이다.
     * 스택은 자료를 넣는 (push) 입구와 자료를 뽑는 (pop) 입구가 같아 제일 나중에 들어간 자료가 제일 먼저 나오는 (LIFO, Last in First out) 특성을 가지고 있다.
     *
     * 1부터 n까지의 수를 스택에 넣었다가 뽑아 늘어놓음으로써, 하나의 수열을 만들 수 있다.
     * 이때, 스택에 push하는 순서는 반드시 오름차순을 지키도록 한다고 하자.
     * 임의의 수열이 주어졌을 때 스택을 이용해 그 수열을 만들 수 있는지 없는지, 있다면 어떤 순서로 push와 pop 연산을 수행해야 하는지를 알아낼 수 있다.
     * 이를 계산하는 프로그램을 작성하라.
     *
     *
     * 첫 줄에 n (1 ≤ n ≤ 100,000)이 주어진다. 둘째 줄부터 n개의 줄에는 수열을 이루는 1이상 n이하의 정수가 하나씩 순서대로 주어진다. 물론 같은 정수가 두 번 나오는 일은 없다.
     *
     * 입력된 수열을 만들기 위해 필요한 연산을 한 줄에 한 개씩 출력한다. push연산은 +로, pop 연산은 -로 표현하도록 한다. 불가능한 경우 NO를 출력한다.
     *
     *         4 3 6 8 7 5 2 1
     *         처음의 경우 현재 숫자보다 작은 모든 숫자를 넣는다.
     *
     *
     *         1 2 3 4
     *         1 2 3
     *         1 2
     *         1 2 5 6
     *         1 2 5
     *         1 2 5 7 8
     *         1 2 5 7
     *         1 2 5
     *         1 2
     *         1
     *
     *         1 2 3 4 5 6 7 8
     */

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int N = scanner.nextInt();
        final int[] ints = new int[N];
        for (int i = 0; i < N; i++) {
            ints[i] = scanner.nextInt();
        }
        System.out.println(betterSolution(ints));
    }

    private static String mySolution(final int[] ints) {
        //1부터 n까지의 수를 스택
        //push 는 오름차순으로 한다.
        //스택을 이용해 ints를 만들 수 있는가?
        Stack<Integer> stack = new Stack<>();
        final boolean[] isUsed = new boolean[ints.length + 1];
        /*
        1. 현재 숫자보다 작고 사용되지 않은 모든 숫자를 push 한다.
            a. peek 에 있는 값이 현재 숫자보다 큰 경우 진행이 불가능하다.
        2. 현재 숫자를 pop 한다.
        3. pop 한 숫자를 사용 처리한다.
        */
        final StringBuilder stringBuilder = new StringBuilder();
        int biggest = 0;
        for (int i = 0; i < ints.length; i++) {
            int current = ints[i];
            if (biggest <= current) {
                for (int j = stack.isEmpty() ? 1 : stack.peek(); j <= current; j++) {
                    // 이미 Stack 에 있거나 사용된 숫자는 제외한다.
                    if (isUsed[j]) {
                        continue;
                    }
                    //stack 이 비어있지 않고 stack 의 peek 값이 current 보다 크다면 불가하다.
                    if (!stack.isEmpty() && stack.peek() > current) {
                        return "NO";
                    }
                    isUsed[stack.push(j)] = true;
                    stringBuilder.append("+").append(System.lineSeparator());
                }
                biggest = Math.max(biggest, current);
            }
            if (stack.isEmpty()) {
                return "NO";
            }
            //stack 이 비어있지 않고 스텍의 peek 값이 현재값이 아닌 경우 불가하다.
            if (stack.peek() != current) {
                return "NO";
            }
            stack.pop();
            stringBuilder.append("-").append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    private static String betterSolution(final int[] ints) {

        Stack<Integer> stack = new Stack<>();

        final StringBuilder stringBuilder = new StringBuilder();
        int usedNumber = 1;

        for (int i = 0; i < ints.length; i++) {
            while (usedNumber <= ints[i]) {
                stack.push(usedNumber++);
                stringBuilder.append("+").append(System.lineSeparator());
            }
            if (stack.pop() != ints[i]) {
                return "NO";
            } else {
                stringBuilder.append("-").append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }
}
