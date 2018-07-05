package BookingREST.Category;

import java.util.List;

public class CategoryBusinessResponse {
    List<Category> data;
    Category links;
    Category meta;

    public Category getLinks() {
        return links;
    }

    public void setLinks(Category links) {
        this.links = links;
    }

    public Category getMeta() {
        return meta;
    }

    public void setMeta(Category meta) {
        this.meta = meta;
    }

    public List<Category> getData() {
        return data;
    }

    public void setData(List<Category> data) {
        this.data = data;
    }
}
