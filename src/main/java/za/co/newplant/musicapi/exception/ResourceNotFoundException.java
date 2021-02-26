package za.co.newplant.musicapi.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String msg) {
        super(String.format("Content with ID %s not found",msg));
    }
}