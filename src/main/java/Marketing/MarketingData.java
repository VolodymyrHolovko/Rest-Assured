package Marketing;

import org.json.JSONArray;
import org.json.simple.JSONObject;

public class MarketingData {
    public JSONObject addNewMarketing() {
        JSONObject marketing = new JSONObject();
        marketing.put("establishmentId", 1);
        marketing.put("title", "title");
        marketing.put("description", "description");
        marketing.put("pathToPhoto", null);
        marketing.put("marketingTypeId", 0);
        marketing.put("address", "address");
        marketing.put("longitude", 22.369531);
        marketing.put("latitude", 23.655902);
        marketing.put("beginTime", 1520851719);
        marketing.put("endTime", 1521283719);
        marketing.put("isActive", true);
        JSONArray links = new JSONArray();
        int socialTypeId = 1;
        String url = "http://facebook.com";
        links.put(socialTypeId);
        links.put(url);
        marketing.put("links", links);
        JSONArray workSchedule = new JSONArray();
        int day = 1;
        int startTime = 1520765319;
        int endTime = 1521370119;
        workSchedule.put(day);
        workSchedule.put(startTime);
        workSchedule.put(endTime);
        marketing.put("workSchedule", workSchedule);
        return marketing;
    }
    public JSONObject updateMarketing() {
            JSONObject marketingupdate = addNewMarketing();
            marketingupdate.remove("establishmentId");
            marketingupdate.put("id",1);
            marketingupdate.replace("title", "title", "title_updated");
            marketingupdate.replace("description", "description", "description_updated");
            marketingupdate.replace("pathToPhoto", null, "media/201803/eNOt8jcLD1hC7hP0.jpg");
            marketingupdate.replace("marketingTypeId", 0, 1);
            marketingupdate.replace("address", "address", "address_updated");
            marketingupdate.replace("longitude", 22.369531, 88.814844);
            marketingupdate.replace("latitude", 23.655902, 31.439261);
            marketingupdate.replace("beginTime", 1520851719, 1520938119);
            marketingupdate.replace("endTime", 1521283719, 1521456519);
            marketingupdate.replace("isActive", true, false);
            JSONArray links = new JSONArray();
            JSONArray linksUpdate = new JSONArray();
            int socialTypeId = 2;
            String url = "http://facebook.com1";
            linksUpdate.put(socialTypeId);
            linksUpdate.put(url);
            marketingupdate.replace("links",links, linksUpdate);
            JSONArray workSchedule = new JSONArray();
            JSONArray workScheduleUpdate = new JSONArray();
            int day = 2;
            int startTime = 1521283719;
            int endTime = 1521542919;
            marketingupdate.replace("workSchedule", workSchedule, workScheduleUpdate);
            return marketingupdate;
    }



}
