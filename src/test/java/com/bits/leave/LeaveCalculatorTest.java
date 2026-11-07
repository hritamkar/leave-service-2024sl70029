
package com.bits.leave;

import org.junit.Test;
import static org.junit.Assert.*;

public class LeaveCalculatorTest {

    LeaveCalculator calculator = new LeaveCalculator();

    // Test 1: Calculate remaining leave balance
    @Test
    public void testCalculateLeaveBalance() {
        int balance = calculator.calculateLeaveBalance(24, 8);

        assertEquals(16, balance);
    }

    // Test 2: Calculate leave encashment
    @Test
    public void testCalculateEncashment() {
        double amount = calculator.calculateEncashment(30000, 5);

        assertEquals(5000.0, amount, 0.01);
    }

    // Test 3: Verify invalid input handling
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidLeaveInput() {
        calculator.calculateLeaveBalance(20, 25);
    }
}
