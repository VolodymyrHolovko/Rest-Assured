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
    public  JSONObject updateMarketing() {
            JSONObject marketingupdate = addNewMarketing();
            return marketingupdate;
    }


}
