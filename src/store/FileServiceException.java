package store;

/**
 * Custom exception used for file service errors.
 */
public class FileServiceException extends Exception {

    /**
     * Creates a new file service exception with a message.
     */
    public FileServiceException(String message) {
        super(message);
    }

    /**
     * Creates a new file service exception with a message and cause.
     */
    public FileServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}