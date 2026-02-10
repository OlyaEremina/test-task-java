package filtering_utility.project;

public class StringStatistics {
    long count = 0;
    int minLength = Integer.MAX_VALUE;
    int maxLength = Integer.MIN_VALUE;

    public void add(String value) {
        count++;
        int lenghtValue = value.length();
        minLength = Math.min(minLength, lenghtValue);
        maxLength = Math.max(maxLength, lenghtValue);
    }

}
