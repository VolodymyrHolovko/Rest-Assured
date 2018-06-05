package BookingREST.WorkingDays;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class WorkingDaysData {
    static final String D_F_T = "yyyy-MM-dd'T'HH:mm:ss.000";

    public WorkingDays addWorkingDays(int business_id, int object_id, String objectType) {
     WorkingDays addWorkindDay = new WorkingDays();
        DateFormat dateFormat = new SimpleDateFormat(D_F_T, Locale.getDefault());
        Date date = new Date();
        long addTwoHours = date.getTime() + TimeUnit.MINUTES.toMillis(125);
        long addThreeHours = date.getTime() + TimeUnit.MINUTES.toMillis(251);
         String dateTime = dateFormat.format(new Date(addTwoHours));
         String endTime = dateFormat.format(new Date(addThreeHours));
        addWorkindDay.setRule("FREQ=DAILY;DTSTART="+dateTime+";DTEND="+endTime+";INTERVAL=1");
        addWorkindDay.setBusiness_id(business_id);
        addWorkindDay.setObject_type(objectType);
        addWorkindDay.setObject_id(object_id);
        addWorkindDay.setIs_exclusion(false);
     return addWorkindDay;
    }

    public WorkingDays hardCode(int business_id, int object_id, String objectType) {
        WorkingDays addWorkindDay = new WorkingDays();
        addWorkindDay.setRule("FREQ=DAILY;DTSTART=2018-04-17 08:00:00;DTEND=2018-04-17 18:00:00;INTERVAL=1");
        addWorkindDay.setBusiness_id(business_id);
        addWorkindDay.setObject_type(objectType);
        addWorkindDay.setObject_id(object_id);
        addWorkindDay.setIs_exclusion(false);
        return addWorkindDay;
    }

    public WorkingDays updateWorkingDays() {
        WorkingDays updateWorkingDay = new WorkingDays();
        DateFormat dateFormat = new SimpleDateFormat(D_F_T, Locale.getDefault());
        Date date = new Date();
        long addTwoHours = date.getTime() + TimeUnit.MINUTES.toMillis(129);
        long addThreeHours = date.getTime() + TimeUnit.MINUTES.toMillis(259);
        String dateTimeUpdate = dateFormat.format(new Date(addTwoHours));
        String endTimeUpdate = dateFormat.format(new Date(addThreeHours));
        updateWorkingDay.setRule("FREQ=DAILY;DTSTART="+dateTimeUpdate+";DTEND="+endTimeUpdate+";INTERVAL=1");
        updateWorkingDay.setIs_exclusion(true);
        return updateWorkingDay;
    }
    public WorkingDays addWorkingDaysBeauty(int business_id, int object_id2) {
        WorkingDays addWorkingDayBeauty = new WorkingDays();
        DateFormat dateFormat = new SimpleDateFormat(D_F_T, Locale.getDefault());
        Date date = new Date();
        long addTwoHours = date.getTime() + TimeUnit.MINUTES.toMillis(126);
        long addThreeHours = date.getTime() + TimeUnit.MINUTES.toMillis(252);
        String dateTime = dateFormat.format(new Date(addTwoHours));
        String endTime = dateFormat.format(new Date(addThreeHours));
        addWorkingDayBeauty.setRule("FREQ=DAILY;DTSTART="+dateTime+";DTEND="+endTime+";INTERVAL=2");
        addWorkingDayBeauty.setBusiness_id(business_id);
        addWorkingDayBeauty.setObject_type("staff");
        addWorkingDayBeauty.setObject_id(object_id2);
        addWorkingDayBeauty.setIs_exclusion(false);
        return addWorkingDayBeauty;
    }
    public WorkingDays updateWorkingDaysBeauty(int business_id, int object_id2) {
        WorkingDays updateWorkingDayBeauty = new WorkingDays();
        DateFormat dateFormat = new SimpleDateFormat(D_F_T, Locale.getDefault());
        Date date = new Date();
        long addTwoHours = date.getTime() + TimeUnit.MINUTES.toMillis(127);
        long addThreeHours = date.getTime() + TimeUnit.MINUTES.toMillis(253);
        String dateTime = dateFormat.format(new Date(addTwoHours));
        String endTime = dateFormat.format(new Date(addThreeHours));
        updateWorkingDayBeauty.setRule("FREQ=DAILY;DTSTART="+dateTime+";DTEND="+endTime+";INTERVAL=3");
        updateWorkingDayBeauty.setBusiness_id(business_id);
        updateWorkingDayBeauty.setObject_type("staff");
        updateWorkingDayBeauty.setObject_id(object_id2);
        updateWorkingDayBeauty.setIs_exclusion(true);
        return  updateWorkingDayBeauty;
    }
}
