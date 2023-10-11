package boj;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class 프린터큐 {

    /*

    1. 중요도를 기준으로 정렬한다.
    2. 같은 중요도라면 인덱스로 정렬한다.


     */
    /*
    문제

    여러분도 알다시피 여러분의 프린터 기기는 여러분이 인쇄하고자 하는 문서를 인쇄 명령을 받은 ‘순서대로’, 즉 먼저 요청된 것을 먼저 인쇄한다.
    여러 개의 문서가 쌓인다면 Queue 자료구조에 쌓여서 FIFO - First In First Out - 에 따라 인쇄가 되게 된다.
    하지만 상근이는 새로운 프린터기 내부 소프트웨어를 개발하였는데, 이 프린터기는 다음과 같은 조건에 따라 인쇄를 하게 된다.
    현재 Queue의 가장 앞에 있는 문서의 ‘중요도’를 확인한다.
    나머지 문서들 중 현재 문서보다 중요도가 높은 문서가 하나라도 있다면, 이 문서를 인쇄하지 않고 Queue의 가장 뒤에 재배치 한다.
    그렇지 않다면 바로 인쇄를 한다.
    예를 들어 Queue에 4개의 문서(A B C D)가 있고, 중요도가 2 1 4 3 라면 C를 인쇄하고, 다음으로 D를 인쇄하고 A, B를 인쇄하게 된다.

    여러분이 할 일은, 현재 Queue에 있는 문서의 수와 중요도가 주어졌을 때, 어떤 한 문서가 몇 번째로 인쇄되는지 알아내는 것이다.
    예를 들어 위의 예에서 C문서는 1번째로, A문서는 3번째로 인쇄되게 된다.

    입력

    첫 줄에 테스트케이스의 수가 주어진다. 각 테스트케이스는 두 줄로 이루어져 있다.

    테스트케이스의 첫 번째 줄에는 문서의 개수 N(1 ≤ N ≤ 100)과,
    몇 번째로 인쇄되었는지 궁금한 문서가 현재 Queue에서 몇 번째에 놓여 있는지를 나타내는 정수 M(0 ≤ M < N)이 주어진다.
    이때 맨 왼쪽은 0번째라고 하자. 두 번째 줄에는 N개 문서의 중요도가 차례대로 주어진다.
    중요도는 1 이상 9 이하의 정수이고, 중요도가 같은 문서가 여러 개 있을 수도 있다.

    출력

    각 테스트 케이스에 대해 문서가 몇 번째로 인쇄되는지 출력한다.

    예시

    3
    1 0
    5
    4 2
    1 2 3 4
    6 0
    1 1 9 1 1 1

    1
    2
    5
     */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int testCaseCount = scanner.nextInt();
        for (int i = 0; i < testCaseCount; i++) {
            final String articleCountString = scanner.next();
            final String articleOrderString = scanner.next();
            final int articleCount = Integer.parseInt(articleCountString);
            final int articleOrder = Integer.parseInt(articleOrderString);
            int[] articleImportances = new int[articleCount];
            for (int j = 0; j < articleCount; j++) {
                articleImportances[j] = scanner.nextInt();
            }
            System.out.println(solution(articleCount, articleOrder, articleImportances));
        }
    }

    private static int solution(final int articleCount, final int articleOrder, final int[] articleImportances) {
        //만약 현재 제일 높은 우선 순위가 아니라면 제일 마지막 순서로 이동한다.
        final LinkedList<Article> articles = IntStream.range(0, articleImportances.length)
            .mapToObj(index -> new Article(index, articleImportances[index]))
            .collect(Collectors.toCollection(LinkedList::new));
        final LinkedList<Article> sortedArticles = articles.stream()
            .sorted()
            .collect(Collectors.toCollection(LinkedList::new));

        int printedOrder = 1;

        //출력할 문서의 순서가 찾는 문서가 될 때 까지 반복한다.
        while (true) {
            while (articles.peek().importance != sortedArticles.peek().importance) {
                articles.offer(articles.poll());
            }
            if (articles.peek().order == articleOrder) {
                break;
            }
            articles.poll();
            sortedArticles.poll();
            printedOrder++;
        }

        return printedOrder;
    }

    private static class Article implements Comparable<Article> {

        final int order;
        final int importance;

        Article(final int order, final int importance) {
            this.order = order;
            this.importance = importance;
        }

        @Override
        public int compareTo(final Article o) {
            if (this.importance == o.importance) {
                return this.order - o.order;
            }
            return o.importance - this.importance;
        }

        @Override
        public String toString() {
            return "Article{" +
                "order=" + order +
                ", importance=" + importance +
                '}';
        }
    }
}
