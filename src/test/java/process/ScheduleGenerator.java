package process;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import data.ExcelData;
import helper.DayTypeCalculator;
import model.DutyTime;
import model.DutyTimeList;
import model.Schedule;
import model.ScheduleList;

public class ScheduleGenerator {
    private ScheduleList scheduleList = new ScheduleList();
    private DutyTimeList dutyTimeList = new DutyTimeList();
    private int[] dayType = {
        DayTypeCalculator.getDayType(5),
        DayTypeCalculator.getDayType(6),
        DayTypeCalculator.getDayType(7),
        DayTypeCalculator.getDayType(8),
        DayTypeCalculator.getDayType(9)
    };
    public static void main(String[] args) {

        ScheduleGenerator scheduleGenerator = new ScheduleGenerator();
        scheduleGenerator.processSchedule();

     }


    public Object[][] processSchedule() {
        readSchedule();
        readDutyList();
        // next step = go through scheudle list and assign day 1 and day 2 duty to the teacher
        setSpare();
        List<List<Object>> data = calculateSlots();

        return convertListTo2DArray(data);

    }


    private Object[][] convertListTo2DArray(List<List<Object>> dynamicArray) {
        int rows = dynamicArray.size();
        int cols = dynamicArray.isEmpty() ? 0 : dynamicArray.get(0).size();

        Object[][] tableData = new Object[rows][cols];

        for (int i = 0; i < rows; i++) {
            List<Object> row = dynamicArray.get(i);
            tableData[i] = row.toArray(new Object[0]);
        }

        return tableData;
    }

    public void readSchedule() {

        try {
            ExcelData excelData = new ExcelData();
            List<List<Object>> values = excelData.read("Schedule!C:F");
            if (values == null || values.isEmpty()) {
                System.out.println("No schedule, no need to process data.");
            } else {
                int i = 0;
                for (List<Object> row : values) {
                    if (i == 0) {
                        i++;
                        continue;
                    } 
                    Schedule schedule = new Schedule();

                    schedule.setDescription((String)row.get(0));
                    schedule.setTeacher((String)row.get(1));

                    String dayBlock = (String)row.get(3);
                    String[] dayBlocks = dayBlock.split(" ");
                    String day1Schedule = dayBlocks[0];
                    day1Schedule = day1Schedule.replace("Day1(", "").replace(")", "");
                    
                    // pull the schedule from the list, add the day blocks and update the schedule
                    // add a new schedule
                    scheduleList.writeSchedule(schedule.getTeacher(), schedule, day1Schedule);

                }
            }
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
            System.out.println("error");
        }
    }

    public void readDutyList() {

        try {
            ExcelData excelData = new ExcelData();
            List<List<Object>> values = excelData.read("Duty List!A:D");
            if (values == null || values.isEmpty()) {
                System.out.println("No duty list, no need to process data.");
            } else {
                int i = 0;
                for (List<Object> row : values) {
                    if (i == 0) {
                        i++;
                        continue;
                    } 
                    DutyTime dutyTime = new DutyTime();

                    dutyTime.setTime((String)row.get(0));
                    dutyTime.setDuty((String)row.get(3));
                    dutyTime.setSpareDay1((String)row.get(1));
                    dutyTime.setSpareDay2((String)row.get(2));

                    dutyTimeList.writeDuty(dutyTime);

                }

            }
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
            System.out.println("error");
        }

    }

    public void setSpare() {
        for (Schedule schedule : scheduleList.getScheduleMap().values()) {
            if (!schedule.getDay1Schedule().contains("A")) {
                schedule.setSpare("A");
            } 
            if (!schedule.getDay1Schedule().contains("B")) {
                schedule.setSpare("B");
            } 
            if (!schedule.getDay1Schedule().contains("C")) {
                schedule.setSpare("C");
            }
            if (!schedule.getDay1Schedule().contains("D")) {
                schedule.setSpare("D");
            } 
            scheduleList.writeSchedule(schedule.getTeacher(), schedule, null);

        }
    }

    public List<List<Object>> calculateSlots() {

        List<List<Object>> data = new ArrayList<>();


        // Map<String, String> slotsMap = new HashMap<>();
        for (DutyTime dutyTime : dutyTimeList.getScheduleMap().values()) {
//            TeacherSchedule teacherSchedule = new TeacherSchedule();
            List<Object> rowData = new ArrayList<>();

            rowData.add(dutyTime.getTime());
            rowData.add(dutyTime.getDuty());
            
            for (int i = 0; i< dayType.length; i++) {
                String teacher = allocateTeacher(dutyTime, dayType[i], i);
                // teacherSchedule.setDutyTime(dutyTime);
                // teacherSchedule.setDayType(dayType[i]);
                // teacherSchedule.setTeacher(teacher);
                // teacherScheduleList.add(null, teacherSchedule);
                
                rowData.add(teacher);
            }
            data.add(rowData);
            
        }

        return data;
    }

    public String allocateTeacher(DutyTime dutyTime, int dayType, int dayNumber) {
        if (dayType == 1) {
            for (Schedule schedule : scheduleList.getScheduleMap().values()) {
                // pick a match based on the spare comparison between the duty slot and the teacher's spare
                // also make sure of teacher not being double allocated for the day 1 slot
                if (dutyTime.getSpareDay1().contains(schedule.getSpare()) && schedule.getAllocated() == -1) {
                    schedule.setAllocated(dayNumber);
                    scheduleList.writeSchedule(schedule.getTeacher(), schedule, null);
                    return schedule.getTeacher();
                }
            }
        }
        if (dayType == 2) {
            for (Schedule schedule : scheduleList.getScheduleMap().values()) {
                // pick a match based on the spare comparison between the duty slot and the teacher's spare
                // also make sure of teacher not being double allocated for the day 2 slot
                if(dutyTime.getSpareDay2().contains(schedule.getSpare()) &&  schedule.getAllocated() == -1) {
                    schedule.setAllocated(dayNumber);
                    scheduleList.writeSchedule(schedule.getTeacher(), schedule, null);
                    return schedule.getTeacher();
                }
            }
        }
        // if nothing found, return empty string
        return null;
    }
    
}
