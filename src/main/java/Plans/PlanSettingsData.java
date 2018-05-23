package Plans;

import Plans.Plan;
import Plans.PlanSettings;

public class PlanSettingsData {
    public PlanSettings UpdatePlanSettings(){
        PlanSettings planSettings = new PlanSettings();
        planSettings.setAddresslimit(50);
        planSettings.setAddresspurchase_limit(60);
        planSettings.setStafflimit(70);
        planSettings.setStaffpurchase_limit(80);
        planSettings.setStaffauthentication(true);
        planSettings.setMarketing(true);
        planSettings.setBookinguser_history_limit(20);
        planSettings.setBookingcreate_max_duration(1000);
        planSettings.setBookinganalytics(true);

        return planSettings;
    }
}
