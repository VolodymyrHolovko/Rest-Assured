package BookingREST.Comments;

public class CommentData {
    public Comments createComment(int busines){
        Comments comments = new Comments();
        comments.setBusiness_id(busines);
        comments.setComment("Навіть не стошнило");

        Comments.RatingPayload rating_payload = new Comments.RatingPayload();
        rating_payload.setConvenience((int) 7.0);
        rating_payload.setPurity((int) 6.0);
        rating_payload.setQuality((int) 8.0);
        comments.setRating_payload(rating_payload);
        return comments;
    }
}
