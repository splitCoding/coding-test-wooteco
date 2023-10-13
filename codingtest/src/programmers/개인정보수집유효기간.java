package programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 개인정보수집유효기간 {

    class Solution {

        public int[] solution(String today, String[] terms, String[] privacies) {
            int todayDaysCount = parseDate(today);
            Map<String, Integer> termsMap = new HashMap();
            for (int i = 0; i < terms.length; i++) {
                String[] parsedTerm = terms[i].split(" ");
                termsMap.put(parsedTerm[0], Integer.parseInt(parsedTerm[1]));
            }
            List<Integer> destroy = new ArrayList();
            for (int i = 0; i < privacies.length; i++) {
                String[] parsedPrivacy = privacies[i].split(" ");
                int privacieStartDay = parseDate(parsedPrivacy[0]);
                int termMonthCount = termsMap.get(parsedPrivacy[1]);
                int expiredDay = privacieStartDay + (termMonthCount * 28);
                if (todayDaysCount >= expiredDay) {
                    destroy.add(i + 1);
                }
            }
            int[] answer = new int[destroy.size()];
            for (int i = 0; i < destroy.size(); i++) {
                answer[i] = destroy.get(i);
            }
            return answer;
        }

        private int parseDate(String date) {
            String[] parsedDate = date.split("\\.");
            int year = Integer.parseInt(parsedDate[0]);
            int month = Integer.parseInt(parsedDate[1]);
            int day = Integer.parseInt(parsedDate[2]);

            int totalDays = 0;
            totalDays += day;
            totalDays += month * 28;
            totalDays += year * 12 * 28;
            return totalDays;
        }
    }
}
