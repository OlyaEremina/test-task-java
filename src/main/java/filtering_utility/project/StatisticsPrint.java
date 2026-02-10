package filtering_utility.project;

public class StatisticsPrint {
    public static void printStatistics(
            NumberStatistics intStatistics,
            NumberStatistics floatStatistics,
            StringStatistics stringStatistics,
            ProgramSettings settings) {

        if (settings.isFullStat) {
            System.out.println("Полная статистика по каждому типу обработанных данных.");

            if (intStatistics.count > 0) {
                System.out.println();
                System.out.println("integer:");
                System.out.println("Количество: " + intStatistics.count);
                System.out.println("Минимум: " + intStatistics.min);
                System.out.println("Максимум : " + intStatistics.max);
                System.out.println("Сумма: " + intStatistics.sum);
                System.out.println("Среднее: " + intStatistics.sum / intStatistics.count);
            }

            if (floatStatistics.count > 0) {
                System.out.println();
                System.out.println("float:");
                System.out.println("Количество: " + floatStatistics.count);
                System.out.println("Минимум : " + floatStatistics.min);
                System.out.println("Максимум : " + floatStatistics.max);
                System.out.println("Сумма: " + floatStatistics.sum);
                System.out.println("Среднее: " + floatStatistics.sum / floatStatistics.count);
            }

            if (stringStatistics.count > 0) {
                System.out.println();
                System.out.println("String:");
                System.out.println("Количество: " + stringStatistics.count);
                System.out.println("Размер самой короткой строки: " + stringStatistics.minLength);
                System.out.println("Размер самой длинной строки: " + stringStatistics.maxLength);

            }

        } else if (settings.isShortStat) {
            System.out.println("Краткая статистика по обработанным данным.");
            System.out.println("Элеметов типа integer: " + intStatistics.count);
            System.out.println("Элеметов типа float: " + floatStatistics.count);
            System.out.println("Элеметов типа String: " + stringStatistics.count);
        }

    }
}
