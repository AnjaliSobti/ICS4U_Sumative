package model;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private String teacher;
    private String course;
    private String description;
    private List<String> day1Schedule = new ArrayList<>();
    private String spare;
    private int allocated = -1;

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher; 
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course =  course; 
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description =  description; 
    }

    public List<String> getDay1Schedule() {
        return day1Schedule;
    }

    public void setDay1Schedule(String day1Schedule) {
        this.day1Schedule.add(day1Schedule); 
    }

    public String getSpare() {
        return spare;
    }

    public void setSpare(String spare) {
        this.spare = spare;
    }

    public int getAllocated() {
        return allocated;
    }

    public void setAllocated(int allocated) {
        this.allocated = allocated;
    }

}
