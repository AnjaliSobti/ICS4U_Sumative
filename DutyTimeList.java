package model;

import java.util.LinkedHashMap;
import java.util.Map;


public class DutyTimeList {

    private Map<String, DutyTime> dutyMap;

    public DutyTimeList() {
        dutyMap = new LinkedHashMap<>();
    }
    
    public DutyTime getDuty(DutyTime dutyTime) {

        return dutyMap.get(dutyTime.getKey());
    }

    public void writeDuty(DutyTime dutyTime) {
        this.dutyMap.put(dutyTime.getKey(), dutyTime);
    }

    public Map<String, DutyTime> getScheduleMap() {
        return dutyMap;
    }

    public void setScheduleMap (Map<String, DutyTime> dutyMap) {
        this.dutyMap = dutyMap;
    }



    
}
