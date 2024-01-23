package model;

import java.util.Date;

public class TeacherSchedule {
    private DutyTime dutyTime;
    private int dayType;
    private Date scheduleDate;
    private String teacher;

    public DutyTime getDutyTime() {
        return dutyTime;
    }

    public void setDutyTime(DutyTime dutyTime) {
        this.dutyTime = dutyTime;
    }

    public int getDayType() {
        return dayType;
    }

    public void setDayType(int dayType) {
        this.dayType = dayType;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String toString(int dayType, Date scheudleDate, String teacher) {
        return dayType + " " + scheduleDate + " " + teacher; 
    }
}
