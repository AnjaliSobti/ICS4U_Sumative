package model;
public class DutyTime {
    private String duty;
    private String time;
    private String spareDay1;
    private String spareDay2;

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKey() {
        return getTime() + getDuty();
    }

    public void setSpareDay1(String spareDay1) {
        this.spareDay1 = spareDay1;
    }

    public String getSpareDay1() {
        return spareDay1;
    }

    public void setSpareDay2(String spareDay2) {
        this.spareDay2 = spareDay2;
    }

    public String getSpareDay2() {
        return spareDay2;
    }
 
}
