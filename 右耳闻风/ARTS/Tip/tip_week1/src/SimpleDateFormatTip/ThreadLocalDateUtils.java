package SimpleDateFormatTip;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ThreadLocalDateUtils {

    public static final ThreadLocal SIMPLE_DATE_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    private ThreadLocalDateUtils(){}

    public static Date parse(String target) {
        try {
            return ((SimpleDateFormat)SIMPLE_DATE_FORMAT.get()).parse(target);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String format(Date target) {
        return ((SimpleDateFormat)SIMPLE_DATE_FORMAT.get()).format(target);
    }

    // 单线程
    private static void testSimpleDateFormatInSingleThread() {
        final String source = "2019-08-25";
        System.out.println(ThreadLocalDateUtils.parse(source));
    }


    // 多线程
    private static  void testSimpleDateFormatWithThreads() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        final String source = "2019-08-25";
        System.out.println(":: parsing date string ::");
        IntStream.rangeClosed(0, 200).forEach((i) -> executorService.submit(() -> System.out.println(ThreadLocalDateUtils.parse(source))));

        executorService.shutdown();
    }

    public static void main(String[] args) {
//        testSimpleDateFormatInSingleThread();
        testSimpleDateFormatWithThreads();
    }

}
