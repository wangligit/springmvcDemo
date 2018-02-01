/*package java8;

import org.junit.Before;
import org.junit.Test;

public class LambdaTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;
        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;
        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> { return a * b; };
        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;
        
        System.out.println("10 + 5 = " + addition.operation(10, 5));
        System.out.println("10 - 5 = " + subtraction.operation(10, 5));
        System.out.println("10 x 5 = " + multiplication.operation(10, 5));
        System.out.println("10 / 5 = " + division.operation(10, 5));
        
        // 不用括号
        GreetingService greetService1 = message ->
        System.out.println("Hello " + message);
          
        // 用括号
        GreetingService greetService2 = (message) ->
        System.out.println("Hello " + message);
          
        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");
    }

    interface MathOperation{
        int operation(int a, int b);
    }
    
    interface GreetingService{
        void sayMessage(String message);
    }
}
*/