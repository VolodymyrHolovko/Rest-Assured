package Nomenclature.Sizes;

public class SizeResponse {
    Size data;
    SizeError error;


    public Size getData() {
        return data;
    }

    public void setData(Size data) {
        this.data = data;
    }

    public SizeError getError() {
        return error;
    }

    public void setError(SizeError error) {
        this.error = error;
    }
}
