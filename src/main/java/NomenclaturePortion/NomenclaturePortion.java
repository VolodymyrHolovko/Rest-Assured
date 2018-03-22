package NomenclaturePortion;

public class NomenclaturePortion {
    NomenclaturePortion data;
    private int minimum;
    private int maximum;
    private int minimumPrice;
    private int step;
    private int stepPrice;
    private boolean isSuccess;

    public NomenclaturePortion(NomenclaturePortion data, int minimum, int maximum, int minimumPrice, int step, int stepPrice, boolean isSuccess) {
        this.data = data;
        this.minimum = minimum;
        this.maximum = maximum;
        this.minimumPrice = minimumPrice;
        this.step = step;
        this.stepPrice = stepPrice;
        this.isSuccess = isSuccess;
    }

    public NomenclaturePortion getData() {
        return data;
    }

    public int getMinimum() {
        return minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public int getMinimumPrice() {
        return minimumPrice;
    }

    public int getStep() {
        return step;
    }

    public int getStepPrice() {
        return stepPrice;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setData(NomenclaturePortion data) {
        this.data = data;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public void setMinimumPrice(int minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public void setStepPrice(int stepPrice) {
        this.stepPrice = stepPrice;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    @Override
    public String toString() {
        return "NomenclaturePortion{" + "data=" + data + ", minimum=" + minimum + ", maximum=" + maximum + ", minimumPrice=" + minimumPrice + ", step=" + step + ", stepPrice=" + stepPrice + ", isSuccess=" + isSuccess + '}';
    }
}
