package za.co.newplant.musicapi.exception.error;

import java.io.Serializable;
import java.util.Objects;

public class ApiSubError implements Serializable, Comparable<ApiSubError> {
    private String field;
    private String message;

    public ApiSubError() {
    }

    public ApiSubError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApiSubError)) return false;
        ApiSubError that = (ApiSubError) o;
        return getField().equals(that.getField()) &&
                getMessage().equals(that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getField(), getMessage());
    }

    @Override
    public String toString() {
        return "ApiSubError{" +
                "field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }


    @Override
    public int compareTo(ApiSubError o) {
        return this.field.compareTo(o.field);
    }
}
