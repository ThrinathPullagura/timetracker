package model;

import java.sql.Timestamp;

public class TimeLog {
    private Timestamp timeIn;
    private Timestamp timeOut;

    public TimeLog(Timestamp timeIn2, Timestamp timeOut2) {
		// TODO Auto-generated constructor stub
	}

	// Getters and setters
    public Timestamp getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Timestamp timeIn) {
        this.timeIn = timeIn;
    }

    public Timestamp getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Timestamp timeOut) {
        this.timeOut = timeOut;
    }
}
