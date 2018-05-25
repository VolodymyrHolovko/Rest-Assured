package BookingREST.Comments;

import java.util.List;

public class Comments {
    int id;
    int business_id;
    String comment;
    float rating;
    String user_id;
    RatingPayload rating_payload;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getBusiness_id() {
        return business_id;
    }

    public String getComment() {
        return comment;
    }



    public void setBusiness_id(int business_id) {
        this.business_id = business_id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public RatingPayload getRating_payload() {
        return rating_payload;
    }

    public void setRating_payload(RatingPayload rating_payload) {
        this.rating_payload = rating_payload;
    }

    public static class RatingPayload {
        int convenience;
        int purity;
        int quality;

        public int getConvenience() {
            return convenience;
        }

        public void setConvenience(int convenience) {
            this.convenience = convenience;
        }

        public int getPurity() {
            return purity;
        }

        public void setPurity(int purity) {
            this.purity = purity;
        }

        public int getQuality() {
            return quality;
        }

        public void setQuality(int quality) {
            this.quality = quality;
        }
    }
}
