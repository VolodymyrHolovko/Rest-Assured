package BookingREST.Comments;

import java.util.List;

public class Comments {
    int business_id;
    String comment;
    List<rating_payload> rating_payload;

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

    public List<Comments.rating_payload> getRating_payload() {
        return rating_payload;
    }

    public void setRating_payload(List<Comments.rating_payload> rating_payload) {
        this.rating_payload = rating_payload;
    }

    public class rating_payload{
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
