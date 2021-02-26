package za.co.newplant.musicapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import za.co.newplant.musicapi.exception.error.Errors;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * ContentResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-02-17T13:34:46.401Z[GMT]")


public class ContentResponse   {
  @JsonProperty("data")
  private DataContentResponse data = null;

  @JsonProperty("errors")
  private Errors errors = null;

  @JsonProperty("message")
  private String message = null;

  public ContentResponse data(DataContentResponse data) {
    this.data = data;
    return this;
  }


  public ContentResponse(DataContentResponse data, Errors errors, String message) {
    this.data = data;
    this.errors = errors;
    this.message = message;
  }

  /**
   * Get data
   * @return data
   **/
  @Schema(description = "")
  
    @Valid
    public DataContentResponse getData() {
    return data;
  }

  public void setData(DataContentResponse data) {
    this.data = data;
  }

  public ContentResponse errors(Errors errors) {
    this.errors = errors;
    return this;
  }

  /**
   * Get errors
   * @return errors
   **/
  @Schema(description = "")
  
    @Valid
    public Errors getErrors() {
    return errors;
  }

  public void setErrors(Errors errors) {
    this.errors = errors;
  }

  public ContentResponse message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   **/
  @Schema(description = "")
  
    public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ContentResponse contentResponse = (ContentResponse) o;
    return Objects.equals(this.data, contentResponse.data) &&
        Objects.equals(this.errors, contentResponse.errors) &&
        Objects.equals(this.message, contentResponse.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, errors, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ContentResponse {\n");
    
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
