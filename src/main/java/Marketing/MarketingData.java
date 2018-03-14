package Marketing;

import org.json.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MarketingData {
    public Marketing addNewMarketing() {
        Marketing marketing = new Marketing();
        marketing.setEstablishmentId(1);
        marketing.setTitle("title");
        marketing.setDescription("description");
        marketing.setPathToPhoto("media/201803/eNOt8jcLD1hC7hP0.jpg");
        marketing.setAddress("address");
        marketing.setMarketingTypeId(2);
        marketing.setLatitude(22);
        marketing.setLongitude(23);
        marketing.setBeginTime("2018-03-29T11:50:30");
        marketing.setEndTime("2018-03-30T11:50:30");
        marketing.setActive(true);

        List<WorkSchedule> workSchedules = new ArrayList<WorkSchedule>();
        WorkSchedule workSchedule = new WorkSchedule();
        workSchedule.setStartTime(1520765319);
        workSchedule.setEndTime(1521370119);
        workSchedule.setDay(1);
        workSchedules.add(workSchedule);
        marketing.setWorkSchedule(workSchedules);

        List<Link> links = new ArrayList<Link>();
        Link link = new Link();
        link.setSocialTypeId(1);
        link.setUrl("http://facebook.com");
        links.add(link);
        marketing.setLinks(links);

        return marketing;
    }
    public Marketing updateMarketing() {
        Marketing marketingUpdate = addNewMarketing();
        marketingUpdate.setId(1);
        marketingUpdate.setTitle("title_update");
        marketingUpdate.setDescription("description_update");
        marketingUpdate.setPathToPhoto("media/201803/mhGdSTf7kdT0LfdS.jpg");
        marketingUpdate.setMarketingTypeId(1);
        marketingUpdate.setAddress("adress_update");
        marketingUpdate.setLongitude(24);
        marketingUpdate.setLatitude(25);
        marketingUpdate.setBeginTime("2018-03-29T11:55:33");
        marketingUpdate.setEndTime("2018-03-30T11:42:32");
        marketingUpdate.setActive(false);

        List<WorkSchedule> workSchedules = new  ArrayList<WorkSchedule>();
        WorkSchedule workScheduleUpdate = new WorkSchedule();
        workScheduleUpdate.setStartTime(1520765319);
        workScheduleUpdate.setEndTime(1521370119);
        workScheduleUpdate.setDay(2);
        workSchedules.add(workScheduleUpdate);
        marketingUpdate.setWorkSchedule(workSchedules);



        // TODO set other fields


        return marketingUpdate;
    }



}
