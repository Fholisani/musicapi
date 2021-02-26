package za.co.newplant.musicapi.exception.error;


import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class ApiError implements Serializable {
    private int code;
    private String message ;
    private Set<ApiSubError> subErrors;


    public ApiError(int code, String message, Set<ApiSubError> subErrors) {
        this.code = code;
        this.message = message;
        this.subErrors = subErrors;
    }

    public int getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = Math.toIntExact(code);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Set<ApiSubError> getSubErrors() {
        return subErrors;
    }

    public void setSubErrors(Set<ApiSubError> subErrors) {
        this.subErrors = subErrors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApiError)) return false;
        ApiError apiError = (ApiError) o;
        return getCode() == apiError.getCode() &&
                getMessage().equals(apiError.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getMessage());
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", suError=" + subErrors +
                '}';
    }
}
