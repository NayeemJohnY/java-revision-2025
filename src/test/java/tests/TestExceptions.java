package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.java_revision.Exceptions;
import com.java_revision.InvalidAgeException;

public class TestExceptions {

    Exceptions exceptions = new Exceptions();

    @Test(dataProvider = "dataProvider")
    public void testArrayBoundException(int index) {
        System.out.println("Hello");
        exceptions.arrayIndexBoundException(index);
    }

    @DataProvider(name = "dataProvider")
    public Object[] dataProvider() {
        return new Object[] { 0, -1, 10, 34, 3, 4, 5};
    }

    @Test(dataProvider = "DivisionProvider")
    public void testArithmeticException(int num1, int num2){
        exceptions.arithemticException(num1, num2);
    }

    @Test(dataProvider = "DivisionProvider")
    public void testUseFinallyBlock(int num1, int num2){
        exceptions.useFinallyBlock(num1, num2);
    }

    @DataProvider(name = "DivisionProvider")
    public Object[][] divisionProvider() {
        return new Object[][] {
            {1, 2},
            {5, 6},
            {8, 4},
            {10, 10}
        };
    }

    @Test(dataProvider = "dataProvider")
    private void testValidateAge(int age){
        try {
            exceptions.validateAge(age);
        } catch (InvalidAgeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test(dataProvider = "DivisionProvider")
    public void testMultipleCatchBlock(int index1, int index2){
        exceptions.multipleCatchBlock(index1, index2);
    }

    @Test
    public void testExceptionProgogate(){
        try {
            exceptions.readFile("src//test//java//tests//TestEmployee.java");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNestedTryCatch(){
        exceptions.nestedTryCatch(0);
    }

    @Test
    public void testFileException(){
        try {
            exceptions.fileException("Filename");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        };
    }

    @Test
    public void testNullException(){
        exceptions.nullException(null);
    }

    @Test
    public void testClassCastException(){
        exceptions.classCastException(123);
    }

    @Test
    public void testNumberFormatException(){
        exceptions.numberFormatException("123");
        exceptions.numberFormatException("11111sws");
    }

    @Test
    public void testInputMismatchException(){
        exceptions.inputMismatchException();
    }

    @Test
    public void testStackOveflowException(){
        exceptions.recursiveCall();
    }

}
