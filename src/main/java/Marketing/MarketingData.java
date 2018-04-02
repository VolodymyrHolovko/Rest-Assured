package Marketing;


import java.util.ArrayList;
import java.util.List;

public class MarketingData {

    public Marketing addNewMarketing() {
        Marketing marketing = new Marketing();
        marketing.setEstablishmentId(1);
        marketing.setBusinessId(42);
        marketing.setTitle("title");
        marketing.setDescription("description");
        marketing.setPathToPhoto("media/201803/eNOt8jcLD1hC7hP0.jpg");
        marketing.setAddress("address");
        marketing.setMarketingTypeId(2);
        marketing.setLatitude(22.00);
        marketing.setLongitude(23.00);
        marketing.setBeginTime("2018-03-18T11:50:30.000");
        marketing.setEndTime("2018-04-15T11:45:30.000");
        marketing.setActive(true);
        List<WorkSchedule> workSchedules = new ArrayList<WorkSchedule>();
        WorkSchedule workSchedule = new WorkSchedule();
        workSchedule.setStartTime(1521472003);
        workSchedule.setEndTime(1523718403);
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
    public Marketing updateMarketing(int ids) {
        Marketing marketingUpdate = addNewMarketing();
        marketingUpdate.setId(ids);
        marketingUpdate.setTitle("title_update");
        marketingUpdate.setDescription("description_update");
        marketingUpdate.setPathToPhoto("media/201803/mhGdSTf7kdT0LfdS.jpg");
        marketingUpdate.setMarketingTypeId(2);
        marketingUpdate.setAddress("adress_update");
        marketingUpdate.setLongitude(24.00);
        marketingUpdate.setLatitude(25.00);
        marketingUpdate.setBeginTime("2018-03-29T11:55:33.000");
        marketingUpdate.setEndTime("2018-03-30T11:42:32.000");
        marketingUpdate.setActive(false);

        List<WorkSchedule> workSchedules = new  ArrayList<WorkSchedule>();
        WorkSchedule workScheduleUpdate = new WorkSchedule();
        workScheduleUpdate.setStartTime(1520765319);
        workScheduleUpdate.setEndTime(1521370119);
        workScheduleUpdate.setDay(2);
        workSchedules.add(workScheduleUpdate);
        marketingUpdate.setWorkSchedule(workSchedules);

        List<Link> links = new ArrayList<Link>();
        Link link = new Link();
        link.setSocialTypeId(2);
        link.setUrl("http://www.instagram.com/");
        links.add(link);
        marketingUpdate.setLinks(links);

        return marketingUpdate;
    }



}
