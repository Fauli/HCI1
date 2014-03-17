package com.example.hci1.arztpraxis.domain;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public abstract class Allocation {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    private static final List<Integer> ALLOWED_MINUTES = Arrays.asList(0, 15, 30, 45);

    private Calendar start;
    private Calendar end;

    /**
     * Creates a new allocation that starts and ends on the given time.
     *
     * @param start Start of the allocation.
     * @param end   End of the allocation.
     * @throws AllocationException If the allocation does not {@code start} or {@code end} on {@code x:00},
     *                             {@code x:15}, {@code x:30} or {@code x:45}.
     */
    public Allocation(Calendar start, Calendar end) {
        if (!isTimeValid(start)) {
            throw new AllocationException("Allocation may start only on x:00, x:15, x:30 or x:45.");
        }
        if (!isTimeValid(end)) {
            throw new AllocationException("Allocation may end only on x:00, x:15, x:30 or x:45.");
        }

        this.start = start;
        this.end = end;
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        if (!isTimeValid(start)) {
            throw new AllocationException("Allocation may start only on x:00, x:15, x:30 or x:45.");
        }
        this.start = start;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        if (!isTimeValid(end)) {
            throw new AllocationException("Allocation may end only on x:00, x:15, x:30 or x:45.");
        }

        this.end = end;
    }

    @Override
    public String toString() {
        return "Allocation{"
                + "start=" + DATE_FORMAT.format(start.getTime())
                + ", end=" + DATE_FORMAT.format(end.getTime())
                + '}';
    }

    private static boolean isTimeValid(Calendar calendar) {
        return ALLOWED_MINUTES.contains(calendar.get(Calendar.MINUTE));
    }

    /**
     * Tests whether {@code this} allocation overlaps the {@code other}. Returns true if it does, false otherwise.
     *
     * @param other Allocation to compare this one to.
     * @return True if both allocations overlap, false otherwise.
     */
    public boolean overlaps(Allocation other) {
        return getStart().before(other.getEnd()) && getEnd().after(other.getStart());
    }
}
