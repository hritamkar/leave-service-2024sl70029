
package com.bits.leave;

// Updated to verify Jenkins automatic CI triggering through Poll SCM.
public class LeaveCalculator {

    // Calculate remaining leave balance
    public int calculateLeaveBalance(int totalLeave, int leaveTaken) {

        if (totalLeave < 0 || leaveTaken < 0) {
            throw new IllegalArgumentException(
                "Leave values cannot be negative"
            );
        }

        if (leaveTaken > totalLeave) {
            throw new IllegalArgumentException(
                "Leave taken cannot exceed total leave"
            );
        }

        return totalLeave - leaveTaken;
    }

    // Calculate leave encashment amount
    public double calculateEncashment(
            double monthlySalary, int encashableDays) {

        if (monthlySalary < 0 || encashableDays < 0) {
            throw new IllegalArgumentException(
                "Salary and leave days cannot be negative"
            );
        }

        double dailySalary = monthlySalary / 30.0;

        return dailySalary * encashableDays;
    }
}
