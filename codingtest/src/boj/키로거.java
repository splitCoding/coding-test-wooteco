package boj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

public class 키로거 {

    private static final char BACKSPACE = '-';
    private static final char LEFT = '<';
    private static final char RIGHT = '>';

    /*

    문제

    창영이는 강산이의 비밀번호를 훔치기 위해서 강산이가 사용하는 컴퓨터에 키로거를 설치했다.
    며칠을 기다린 끝에 창영이는 강산이가 비밀번호 창에 입력하는 글자를 얻어냈다.
    키로거는 사용자가 키보드를 누른 명령을 모두 기록한다.
    따라서, 강산이가 비밀번호를 입력할 때, 화살표나 백스페이스를 입력해도 정확한 비밀번호를 알아낼 수 있다.

    강산이가 비밀번호 창에서 입력한 키가 주어졌을 때, 강산이의 비밀번호를 알아내는 프로그램을 작성하시오.
    강산이는 키보드로 입력한 키는 알파벳 대문자, 소문자, 숫자, 백스페이스, 화살표이다.

    ---
    입력

    [테스트 케이스 갯수]
    [입력 문자열 L] 1 ≤ L ≤ 1,000,000

    백스페이스 : -
    화살표 : < , >

    2
    <<BP<A>>Cd-
    ThIsIsS3Cr3t
    ---
    출력

    BAPC
    ThIsIsS3Cr3t
    */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int testCaseCount = scanner.nextInt();
        for (int i = 0; i < testCaseCount; i++) {
            final String input = scanner.next();
            System.out.println(solutionUsingLinkedList(input));
        }
    }

    /*
    [] : 0
    1 : [1] : 1
    2: [12] : 2
    3: [123] : 3
    - : [12] : 2
    < : [12] : 1
    - : [2] : 0
     */

    private static String solutionUsingArrayList(final String input) {
        //시간초과
        //중간에 값을 삭제, 추가하는 일이 많이 생기기에 ArrayList 를 사용하면 많은 성능 저하 발생
        final List<Character> passwordArrayList = new ArrayList<>();
        return solutionExecutor(passwordArrayList, input);
    }

    private static String solutionUsingLinkedList(final String input) {
        final List<Character> passwordLinkedList = new LinkedList<>();
        return solutionExecutor(passwordLinkedList, input);
    }

    private static String solutionExecutor(final List<Character> password, final String input) {
        int cursor = 0;

        for (int i = 0; i < input.length(); i++) {
            final char currentCharacter = input.charAt(i);
            if (currentCharacter == BACKSPACE) {
                if (cursor > 0) {
                    password.remove(--cursor);
                }
            } else if (currentCharacter == RIGHT) {
                if (cursor < password.size()) {
                    cursor++;
                }
            } else if (currentCharacter == LEFT) {
                if (cursor > 0) {
                    cursor--;
                }
            } else {
                password.add(cursor++, currentCharacter);
            }
        }
        return password.stream()
            .map(String::valueOf)
            .collect(Collectors.joining());
    }

    private static String solutionUsingDoubleStack(final String input) {
        final Stack<Character> left = new Stack<>();
        final Stack<Character> right = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            final char currentCharacter = input.charAt(i);
            if (currentCharacter == BACKSPACE) {
                if (!left.isEmpty()) {
                    left.pop();
                }
            } else if (currentCharacter == RIGHT) {
                if (!right.isEmpty()) {
                    left.push(right.pop());
                }
            } else if (currentCharacter == LEFT) {
                if (!left.isEmpty()) {
                    right.push(left.pop());
                }
            } else {
                left.push(currentCharacter);
            }
        }
        final StringBuilder answer = new StringBuilder();
        while (!left.isEmpty()) {
            answer.append(left.pop());
        }
        answer.reverse();
        while (!right.isEmpty()) {
            answer.append(right.pop());
        }

        return answer.toString();
    }
}
