package helper;

import java.util.List;

import model.Schedule;

public class ListSearch {
    public static boolean check(List<Schedule> list, String itemToBeSearched){
    
        return list.stream().filter(o -> o.getTeacher().equals(itemToBeSearched)).findFirst().isPresent();
    }
}
