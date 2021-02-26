package za.co.newplant.musicapi.exception;

import za.co.newplant.musicapi.exception.error.ApiError;

public class CustomException extends RuntimeException {

    private  ApiError apiError;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, ApiError apiError) {
        super(message);
        this.apiError = apiError;
    }

    public ApiError getApiError() {
        return apiError;
    }
}
