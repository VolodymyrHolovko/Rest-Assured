package BookingREST.Businesses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Businesses {
    int promoter_id;
    int strategy_id;
    int sector_id;
    String name;
    String short_description;
    String description;
    String alias;
    @JsonProperty
    boolean is_verified;
    String url;
    String background;
    String logo;
    String link_instagram;
    String link_facebook;
    int id;
    boolean is_searchable;

    public boolean isIs_searchable() {
        return is_searchable;
    }

    public void setIs_searchable(boolean is_searchable) {
        this.is_searchable = is_searchable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPromoter_id() {
        return promoter_id;
    }

    public int getStrategy_id() {
        return strategy_id;
    }

    public int getSector_id() {
        return sector_id;
    }

    public String getName() {
        return name;
    }

    public String getShort_description() {
        return short_description;
    }

    public String getDescription() {
        return description;
    }

    public String getAlias() {
        return alias;
    }

    public boolean isIs_verified() {
        return is_verified;
    }

    public String getUrl() {
        return url;
    }

    public String getBackground() {
        return background;
    }

    public String getLogo() {
        return logo;
    }

    public String getLink_instagram() {
        return link_instagram;
    }

    public String getLink_facebook() {
        return link_facebook;
    }

    public void setPromoter_id(int promoter_id) {
        this.promoter_id = promoter_id;
    }

    public void setStrategy_id(int strategy_id) {
        this.strategy_id = strategy_id;
    }

    public void setSector_id(int sector_id) {
        this.sector_id = sector_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setIs_verified(boolean is_verified) {
        this.is_verified = is_verified;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setLink_instagram(String link_instagram) {
        this.link_instagram = link_instagram;
    }

    public void setLink_facebook(String link_facebook) {
        this.link_facebook = link_facebook;
    }
}
