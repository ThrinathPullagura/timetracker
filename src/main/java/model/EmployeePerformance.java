package model;

public class EmployeePerformance {
    private String employeeName;
    private int tasksCompleted;
    private int attendanceDays;

    public EmployeePerformance(String employeeName, int tasksCompleted, int attendanceDays) {
        this.employeeName = employeeName;
        this.tasksCompleted = tasksCompleted;
        this.attendanceDays = attendanceDays;
    }

    // Getters and setters...
    
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getTasksCompleted() {
        return tasksCompleted;
    }

    public void setTasksCompleted(int tasksCompleted) {
        this.tasksCompleted = tasksCompleted;
    }

    public int getAttendanceDays() {
        return attendanceDays;
    }

    public void setAttendanceDays(int attendanceDays) {
        this.attendanceDays = attendanceDays;
    }
}
