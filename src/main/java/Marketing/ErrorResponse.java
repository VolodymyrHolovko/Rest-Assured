package Marketing;

public class ErrorResponse {
        private  int errorType;
        private  String errorDescription;


        public int getErrorType() {
            return errorType;
        }

        public void setErrorType(int errorType) {
            this.errorType = errorType;
        }

        public String getErrorDescription() {
            return errorDescription;
        }

        public void setErrorDescription(String errorDescription) {
            this.errorDescription = errorDescription;
        }
}
