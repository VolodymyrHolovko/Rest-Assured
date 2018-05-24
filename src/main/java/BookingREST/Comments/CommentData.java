package BookingREST.Comments;

import java.util.ArrayList;
import java.util.List;

public class CommentData {
    public Comments createComment(int busines){
        Comments comments = new Comments();
        comments.setBusiness_id(busines);
        comments.setComment("Навіть не стошнило");
        List<Comments.rating_payload > rating_payloads = new ArrayList<>();
        Comments.rating_payload rating_payload = new Comments.rating_payload();
        rating_payload.setConvenience(7);
        rating_payload.setPurity(6);
        rating_payload.setQuality(8);
        rating_payloads.add(rating_payload);
        comments.setRating_payload(rating_payloads);
        return comments;
    }
}
