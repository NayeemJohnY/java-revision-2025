public class FunctionsMethods {

    public int add(int a, int b) {
        return a + b;
    }

    public void add(double a, double b) {
        System.out.println(a + b);
    }

    public String add(String a, String b) {
        return a + b;
    }

    public int factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public int factorialIterative(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
            ;
        }
        return result;
    }

    public int fibonacci(int n) {
        // fibonacci(0) = 0
        // fibonacci(1) = 1
        // fibonacci(n) = fibonacci(n-1) + fibonacci(n-2) for n > 1

        // fibonacci(5)
        // = fibonacci(4) + fibonacci(3)

        // → fibonacci(4)
        // = fibonacci(3) + fibonacci(2)
        // → fibonacci(3)
        // = fibonacci(2) + fibonacci(1)
        // → fibonacci(2)
        // = fibonacci(1) + fibonacci(0)
        // = 1 + 0 = 1
        // → fibonacci(1) = 1
        // → So, fibonacci(3) = 1 + 1 = 2

        // → fibonacci(2)
        // = fibonacci(1) + fibonacci(0)
        // = 1 + 0 = 1

        // → So, fibonacci(4) = 2 + 1 = 3

        // → fibonacci(3)
        // = fibonacci(2) + fibonacci(1)
        // = 1 + 1 = 2

        // → Final answer:
        // fibonacci(5) = 3 + 2 = **5**
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public int fibonacciIterative(int n) {
        if (n <= 1) {
            return n;
        }
        int a = 0, b = 1, result = 0;
        for (int i = 2; i <= n; i++) {
            result = a + b;
            a = b;
            b = result;
        }
        return result;
    }

    public int sumArray(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public String reverseString(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chararray = str.toCharArray();
        for (int i = chararray.length - 1; i >= 0; i--) {
            stringBuilder.append(chararray[i]);
        }
        return stringBuilder.toString();
    }

    public float findAverage(int... numbers) {
        int sum = 0;
        for (int i : numbers) {
            sum += i;
        }
        return (float) sum / numbers.length;
    }

    public int maxOfThree(int a, int b, int c) {
        if (a > b && a > c)
            return a;
        else if (b > c)
            return b;
        else
            return c;
    }

    public boolean isPrime(int number) {
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0)
                return false;
        }
        return true;
    }

    public boolean isPalindrome(String str) {
        for (int i = 0; i <= str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i))
                return false;
        }
        return true;
    }

    public boolean isEven(int number) {
        return number % 2 == 0;
    }

    public void convertToCelsius(float fahrenheit) {
        System.out.println((fahrenheit - 32) * 5 / 9);
    }

    public static void main(String[] args) {
        FunctionsMethods functionsMethods = new FunctionsMethods();
        System.out.println(functionsMethods.add("Hello", "World"));
        functionsMethods.add(23.89, 23.21);
        System.out.println(functionsMethods.add(10, 20));
        System.out.println(functionsMethods.factorial(5));
        System.out.println(functionsMethods.fibonacci(6));
        System.out.println(functionsMethods.sumArray(new int[] { 12, 45, 67, 78 }));
        System.out.println(functionsMethods.reverseString("stringBuilder"));
        System.out.println(functionsMethods.findAverage(11, 12, 13, 14, 15, 16, 221, 9));
        System.out.println(functionsMethods.maxOfThree(1, 77, 5));
        System.out.println(functionsMethods.isPrime(15));
        System.out.println(functionsMethods.isPrime(13));
        System.out.println(functionsMethods.isPalindrome("silent"));
        System.out.println(functionsMethods.isPalindrome("madam"));
        System.out.println(functionsMethods.isEven(1));
        System.out.println(functionsMethods.isEven(4));
        functionsMethods.convertToCelsius(47);
    }

}
