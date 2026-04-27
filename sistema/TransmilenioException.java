/**
 * Exception class for the Transmilenio system errors.
 */
public class TransmilenioException extends Exception {
    public static final String ROUTE_NOT_FOUND = "The route does not exist.";
    public static final String NO_WAIT_TIME = "The route has no wait time.";
    public static final String STATION_NOT_FOUND = "The station does not exist.";

    public TransmilenioException(String message) {
        super(message);
    }
}
