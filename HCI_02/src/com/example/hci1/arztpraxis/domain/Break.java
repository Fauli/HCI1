package com.example.hci1.arztpraxis.domain;

import java.util.Calendar;

public class Break extends Allocation {

    private Doctor doctor;

    public Break(Calendar start, Calendar end, Doctor doctor) {
        super(start, end);
        this.doctor = doctor;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Break{"
                + "start=" + getStart()
                + ", end=" + getEnd()
                + ", doctor=" + doctor
                + "} ";
    }
}
