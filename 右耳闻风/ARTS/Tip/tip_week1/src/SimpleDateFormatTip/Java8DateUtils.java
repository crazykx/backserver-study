package SimpleDateFormatTip;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Java8DateUtils {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private Java8DateUtils(){}

    public static LocalDate parse(String target) {
        return LocalDate.parse(target, DATE_TIME_FORMATTER);
    }

    public static String format(LocalDate target) {
        return target.format(DATE_TIME_FORMATTER);
    }

    // 单线程
    private static void testSimpleDateFormatInSingleThread() {
        final String source = "2019-08-25";
        System.out.println(Java8DateUtils.parse(source));
    }


    // 多线程
    private static  void testSimpleDateFormatWithThreads() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        final String source = "2019-08-25";
        System.out.println(":: parsing date string ::");
        IntStream.rangeClosed(0, 200).forEach((i) -> executorService.submit(() -> System.out.println(Java8DateUtils.parse(source))));

        executorService.shutdown();
    }

    public static void main(String[] args) {
//        testSimpleDateFormatInSingleThread();
        testSimpleDateFormatWithThreads();
    }

}
