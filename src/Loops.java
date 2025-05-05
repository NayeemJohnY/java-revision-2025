public class Loops {

    public void printStarPattern(int rows) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print('*');
            }
            System.out.println();
        }
    }

    public void findGCDOfTwoNumbers(int num1, int num2) {
        int gcd = Math.min(num1, num2);
        while (gcd > 0) {
            if (num1 % gcd == 0 && num2 % gcd == 0) {
                break;
            }
            gcd--;
        }
        System.out.println(gcd);
    }

    public void findGCDOfTwoNumbersEulidenAlgorithm(int num1, int num2) {
        while (num2 != 0) {
            int temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }
        System.out.println(num1);
    }

    public void checkPrimeNumber(int num) {
        boolean isPrime = true;
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                isPrime = false;
                break;
            }
        }
        if (isPrime) {
            System.out.println("Given number is Prime Number");
        } else {
            System.out.println("Given number is not Prime Number");
        }
    }

    public void checkPalindromeNumber(int num) {
        int temp = num;
        int result = 0;
        while (temp > 0) {
            result = result * 10 + temp % 10;
            temp /= 10;
        }
        if (num == result) {
            System.out.println("Given number is Palindrome");
        } else {
            System.out.println("Given number is not Palindrome");
        }
    }

    public void reverseNumber(int num) {
        int result = 0;
        while (num > 0) {
            result = result * 10 + num % 10;
            num /= 10;
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {
        Loops loops = new Loops();
        loops.printStarPattern(5);
        loops.findGCDOfTwoNumbers(8, 12);
        loops.checkPrimeNumber(69);
        loops.checkPalindromeNumber(123);
        loops.checkPalindromeNumber(121);
        loops.reverseNumber(12345);
    }

}
