package model;

import java.util.ArrayList;
import java.util.List;


public class TeacherScheduleList {

    private List<TeacherSchedule> teacherScheduleMap;

    public TeacherScheduleList() {
        teacherScheduleMap = new ArrayList<>();
    }
    
    public void add(String teacher, TeacherSchedule teacherSchedule) {

        this.teacherScheduleMap.add(teacherSchedule);
    }


    public List<TeacherSchedule> getScheduleMap() {
        return teacherScheduleMap;
    }

    public void setScheduleMap (List<TeacherSchedule> teacherScheduleMap) {
        this.teacherScheduleMap = teacherScheduleMap;
    }



    
}
