package model;

import java.util.HashMap;
import java.util.Map;


public class ScheduleList {

    private Map<String, Schedule> scheduleMap;

    public ScheduleList() {
        scheduleMap = new HashMap<>();
    }

    public Schedule getSchedule(String teacher) {

        return scheduleMap.get(teacher);
    }

    public void writeSchedule(String teacher, Schedule schedule, String day1) {

        // update the schedule

        if (scheduleMap.containsKey(teacher)) {

            Schedule oldSchedule = scheduleMap.get(teacher);
            if (day1 != null) {
                oldSchedule.setDay1Schedule(day1);
            }
            

            scheduleMap.put(teacher, oldSchedule);
        } else {

            // create the new schedule
            if (day1 != null) {
                schedule.setDay1Schedule(day1);
            }
            this.scheduleMap.put(schedule.getTeacher(), schedule);
        }
    }

    public Map<String, Schedule> getScheduleMap() {
        return scheduleMap;
    }

    public void setScheduleMap (Map<String, Schedule> scheduleMap) {
        this.scheduleMap = scheduleMap;
    }



    
}
