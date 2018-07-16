package BookingREST.Gallery;

public class GalleryData {
    public Gallery addPhoto(int businessId){
        Gallery gallery = new Gallery();
        gallery.setObject_type("business");
        gallery.setObject_id(businessId);
        gallery.setPath("https://staging.eservia.com/image/media/201805/jAgUxCmshMJuFrFl.png");
        return  gallery;
    }

    public Gallery updatePhoto(int businessId){
        Gallery gallery = new Gallery();
        gallery.setPath("https://staging.eservia.com/image/media/201805/jAgUxCmshMJuFrFl1.png");
        return  gallery;
    }
}
