package dimasnovik;

public class ErrorCounter {
    private static final ErrorCounter INSTANCE = new ErrorCounter();
    private int errorCount = 0;

    private ErrorCounter() {
    }

    public static ErrorCounter getInstance() {
        return INSTANCE;
    }

    public void addError() {
        errorCount++;
    }

    public int getErrorCount() {
        return errorCount;
    }

}
