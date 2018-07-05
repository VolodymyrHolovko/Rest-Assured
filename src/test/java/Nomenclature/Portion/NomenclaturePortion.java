package Nomenclature.Portion;

public class NomenclaturePortion {
    private int minimum;
    private int maximum;
    private int minimumPrice;
    private int step;
    private int stepPrice;

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
}
