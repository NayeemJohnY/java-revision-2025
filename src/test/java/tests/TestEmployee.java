package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.java_revision.Employee;
import com.java_revision.Manager;

public class TestEmployee {

    
    @Test
    public void testEmployeeSalray(){
        Employee employee = new Employee("John", 123, 30000);
        Assert.assertEquals(employee.getSalary(), employee.calculateTotalSalary());
    }

    @Test
    public void testManagerSalray(){
        Manager manger = new Manager("Manager", 456, 30000, 1000);
        Assert.assertEquals(manger.getSalary() + manger.getBonus(), manger.calculateTotalSalary());
    }
}

