package Nomenclature.Option.Option;

public class OptionError {
    OptionError error;
    private String errorDescription;

    public OptionError getError() {
        return error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setError(OptionError error) {
        this.error = error;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
