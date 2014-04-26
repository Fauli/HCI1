package com.example.hci1.arztpraxis.service;

import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;

import com.example.hci1.arztpraxis.domain.Allocation;
import com.example.hci1.arztpraxis.domain.Appointment;
import com.example.hci1.arztpraxis.domain.Assistant;
import com.example.hci1.arztpraxis.domain.Break;
import com.example.hci1.arztpraxis.domain.Doctor;
import com.example.hci1.arztpraxis.domain.Patient;
import com.example.hci1.arztpraxis.domain.Patient.Gender;
import com.example.hci1.arztpraxis.domain.TreatingPerson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Manages the diary of the doctor's office.
 */
public class DiaryController {

    private static final SimpleDateFormat DAY_FORMAT = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat BIRTHDAY_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static final int GENDER = 0;
    private static final int FIRST_NAME = 1;
    private static final int LAST_NAME = 2;
    private static final int ADDRESS = 3;
    private static final int POST_CODE = 4;
    private static final int CITY = 5;
    private static final int PHONE = 6;
    private static final int BIRTHDAY = 7;
    private Set<TreatingPerson> treatingPersons;
    private Set<Allocation> allocations;
    private Set<Patient> patients;

    public DiaryController() {
        allocations = new HashSet<>();
        
        Calendar cal = Calendar.getInstance();
        cal.set(MINUTE, 0);
		cal.set(HOUR_OF_DAY, 10);
		cal.set(Calendar.MONTH, 4);
		cal.set(Calendar.DAY_OF_MONTH, 6);
		Doctor doc = new Doctor("Sophie", "Keller");
		
		Patient p = new Patient(Gender.MALE,"Test","TestLast","Address","PLZ","CityString","phone number",cal);
        Appointment app = new Appointment(cal, cal, doc, p, "Reason", "Nothing");
        allocations.add(app);
        System.out.println(app.toString());

        treatingPersons = new HashSet<>();
        treatingPersons.add(new Doctor("Karin", "Müller"));
        treatingPersons.add(new Doctor("Peter", "Meier"));
        treatingPersons.add(new Doctor("Sophie", "Keller"));
        treatingPersons.add(new Assistant());

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream("/patienten.csv")))) {
            String line;
            patients = new HashSet<>();
            while ((line = reader.readLine()) != null) {
                String[] attrs = line.split(",");
                try {
                    Calendar birthday = Calendar.getInstance();
                    birthday.setTime(BIRTHDAY_FORMAT.parse(attrs[BIRTHDAY]));
                    patients.add(new Patient(Patient.Gender.valueOf(attrs[GENDER]), attrs[FIRST_NAME],
                            attrs[LAST_NAME], attrs[ADDRESS], attrs[POST_CODE], attrs[CITY], attrs[PHONE],
                            birthday));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new DiaryControllerException("Failed to load patient data.", e);
        }

    }

    /**
     * Adds given allocation to the diary.
     *
     * @param allocation To add to the diary.
     */
    public void addAllocation(Allocation allocation) {
        allocations.add(allocation);
    }

    /**
     * Gets the set of all {@link TreatingPerson}s ({@link Doctor}s and {@link Assistant}s).
     *
     * @return The set of all {@link TreatingPerson}s.
     */
    public Set<TreatingPerson> getTreatingPersons() {
        return treatingPersons;
    }

    /**
     * Gets the set of all {@link Doctor}s.
     *
     * @return The set of all {@link Doctor}s.
     */
    public Set<Doctor> getDoctors() {
        HashSet<Doctor> doctors = new HashSet<>();
        for (TreatingPerson treatingPerson : treatingPersons) {
            if (treatingPerson instanceof Doctor) {
                doctors.add((Doctor) treatingPerson);
            }
        }
        return doctors;
    }

    /**
     * Gets the set of all {@link Assistant}s.
     *
     * @return The set of all {@link Assistant}s.
     */
    public Set<Assistant> getAssistants() {
        HashSet<Assistant> assistants = new HashSet<>();
        for (TreatingPerson treatingPerson : treatingPersons) {
            if (treatingPerson instanceof Assistant) {
                assistants.add((Assistant) treatingPerson);
            }
        }
        return assistants;
    }

    /**
     * Gets the set of all {@link Allocation}s.
     *
     * @return The set of all {@link Allocation}s.
     */
    public Set<Allocation> getAllocations() {
        return allocations;
    }

    /**
     * Gets the set of all allocations that are associated with a specific {@link TreatingPerson} ({@link Doctor} or
     * {@link Assistant}).
     *
     * @param treatingPerson Doctor or assistant whose allocations should be returned.
     * @return Set of all allocations that are associated with the given {@link TreatingPerson}.
     */
    public Set<Allocation> getAllocations(TreatingPerson treatingPerson) {
        Set<Allocation> allocationsOfPerson = new HashSet<>();
        for (Allocation alloc : allocations) {
            if (alloc instanceof Appointment && ((Appointment) alloc).getTreatingPerson().equals(treatingPerson)) {
                allocationsOfPerson.add(alloc);
            }
            if (alloc instanceof Break && ((Break) alloc).getDoctor().equals(treatingPerson)) {
                allocationsOfPerson.add(alloc);
            }
        }

        return allocationsOfPerson;
    }

    /**
     * Gets the set of all allocations that are scheduled for the given {@code day}.
     *
     * @param day Date of the day.
     * @return Set of all allocations that are scheduled for the given {@code day}.
     */
    public Set<Allocation> getAllocations(Calendar day) {
        Set<Allocation> allocationsOnDay = new HashSet<>();
        String referenceDate = DAY_FORMAT.format(day.getTime());

        for (Allocation allocation : allocations) {
            if (DAY_FORMAT.format(allocation.getStart().getTime()).equals(referenceDate)) {
                allocationsOnDay.add(allocation);
            }
        }

        return allocationsOnDay;
    }

    /**
     * Gets the set of all allocations that are associated to the given {@link TreatingPerson} and scheduled for the
     * given {@code day}.
     *
     * @param treatingPerson Doctor or assistant whose allocations should be returned.
     * @param day            Date of the day.
     * @return Allocations for the given doctor or assistant on the given day.
     */
    public Set<Allocation> getAllocations(TreatingPerson treatingPerson, Calendar day) {
        Set<Allocation> allocationsOnDay = getAllocations(day);
        Set<Allocation> allocationsOfPerson = getAllocations(treatingPerson);

        boolean ofPersonBigger = allocationsOfPerson.size() > allocationsOnDay.size();
        Set<Allocation> intersection = new HashSet<>(ofPersonBigger ? allocationsOnDay : allocationsOfPerson);
        intersection.retainAll(ofPersonBigger ? allocationsOfPerson : allocationsOnDay);

        return intersection;
    }

    /**
     * Gets the set of all {@link Patient}s.
     *
     * @return The set of all patients.
     */
    public Set<Patient> getPatients() {
        return patients;
    }

    /**
     * Removes the given allocation from the diary.
     *
     * @param allocation Allocation to remove.
     */
    public void removeAllocation(Allocation allocation) {
        allocations.remove(allocation);
    }
}
