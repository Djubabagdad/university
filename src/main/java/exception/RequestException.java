package exception;

public class RequestException extends Exception{
    private final String message;

    public RequestException(String str) {
        this.message = str;
    }

    public String toString() {
        return ("Request exception occurred: " + message);
    }
}
