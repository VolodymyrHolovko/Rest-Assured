package BookingREST.WorkingDays;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class WorkingDaysData {
    static final String D_F_T = "yyyy-MM-dd'T'HH:mm:ss.000";

    public WorkingDays addWorkingDays(int business_id, int object_id) {
     WorkingDays addWorkindDay = new WorkingDays();
        DateFormat dateFormat = new SimpleDateFormat(D_F_T, Locale.getDefault());
        Date date = new Date();
        long addTwoHours = date.getTime() + TimeUnit.MINUTES.toMillis(125);
        long addThreeHours = date.getTime() + TimeUnit.MINUTES.toMillis(251);
        String dateTime = dateFormat.format(new Date(addTwoHours));
        String endTime = dateFormat.format(new Date(addThreeHours));
        addWorkindDay.setRule("FREQ=DAILY;DTSTART="+dateTime+";DTEND="+endTime+";INTERVAL=1");
        addWorkindDay.setBusiness_id(business_id);
        addWorkindDay.setObject_type("address");
        addWorkindDay.setObject_id(object_id);
        addWorkindDay.setIs_exclusion(false);
     return addWorkindDay;
    }
}
