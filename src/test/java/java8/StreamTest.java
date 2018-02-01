/*package java8;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

public class StreamTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        // 输出10个随机数
        Random random = new Random();
          
        for(int i=0; i < 10; i++){
           System.out.println(random.nextInt());
        }
          
        System.out.println("使用 Java 8: ");
        System.out.println("列表: " +strings);
          
        long  count = strings.stream().filter(string->string.isEmpty()).count();
        System.out.println("空字符串数量为: " + count);
          
        count = strings.stream().filter(string -> string.length() == 3).count();
        System.out.println("字符串长度为 3 的数量为: " + count);
          
        // 删除空字符串
        List<String> filtered = strings.stream().filter(string ->!string.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选后的列表: " + filtered);
          
        // 删除空字符串，并使用逗号把它们合并起来
        String mergedString = strings.stream().filter(string ->!string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);
        
        // 获取列表元素平方数
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squaresList = numbers.stream().map( i ->i*i).distinct().collect(Collectors.toList());
        List<Integer> integers = Arrays.asList(1,2,13,4,15,6,17,8,19);
        System.out.println("Squares List: " + squaresList);
        System.out.println("列表: " +integers);
          
        IntSummaryStatistics stats = integers.stream().mapToInt((x) ->x).summaryStatistics();
          
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        System.out.println("随机数: ");
          
        random.ints().limit(10).sorted().forEach(System.out::println);
          
        // 并行处理
        count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println("空字符串的数量为: " + count);
     }

}
*/