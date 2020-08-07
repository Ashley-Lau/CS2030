package cs2030.simulator;

import java.util.Comparator;

public class ScheduleComparator implements Comparator<Schedule> {
    /**
     * Schedule comparator which compares the order of the schedule.
     */
    public int compare(Schedule s1, Schedule s2) {
        if (s1.getTime() != s2.getTime()) { // compares the time first
            return s1.getTime() - s2.getTime() < 0 ? -1 : 1; 
        } else {
            if (s1 instanceof ServerSchedule && s2 instanceof ServerSchedule) { 
                if (s1.getServer().getState() == s2.getServer().getState()) { 
                    // returns the server with lower number
                    return s1.getServer().getId() - s2.getServer().getId();
                } else {
                    return s1.getServer().getState().getIndex() 
                        - s2.getServer().getState().getIndex();
                } 
            } else if (s1 instanceof ServerSchedule) { 
                // ServerSchedule is always updated before CustomerSchedules
                return -1; 
            } else if (s2 instanceof ServerSchedule) {
                return 1; 
            } else if (s1.getCustomer().getId() != s2.getCustomer().getId()) {
                return s1.getCustomer().getId() - s2.getCustomer().getId();
            } else { 
                return s1.getCustomer().getState().getIndex() -
                    s2.getCustomer().getState().getIndex();
            }
        }
    }
}
