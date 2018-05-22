package BookingREST.Gallery;

public class Gallery {
    private  String object_type;
    private  int object_id;
    private String path;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObject_type() {
        return object_type;
    }

    public int getObject_id() {
        return object_id;
    }

    public String getPath() {
        return path;
    }

    public void setObject_type(String object_type) {
        this.object_type = object_type;
    }

    public void setObject_id(int object_id) {
        this.object_id = object_id;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
