package com.example.hci1.arztpraxis.domain;

import java.util.Calendar;

public class Appointment extends Allocation {
    private TreatingPerson treatingPerson;
    private Patient patient;
    private String reason;
    private String remarks;

    public Appointment(Calendar start, Calendar end, TreatingPerson treatingPerson, Patient patient, String reason,
                       String remarks) {
        super(start, end);
        this.treatingPerson = treatingPerson;
        this.patient = patient;
        this.reason = reason;
        this.remarks = remarks;
    }

    public TreatingPerson getTreatingPerson() {
        return treatingPerson;
    }

    public void setTreatingPerson(TreatingPerson treatingPerson) {
        this.treatingPerson = treatingPerson;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Appointment{"
                + "start=" + getStart()
                + ", end=" + getEnd()
                + ", treatingPerson=" + treatingPerson
                + ", patient=" + patient
                + ", reason=" + reason
                + ", remarks=" + remarks
                + "} ";
    }
}
