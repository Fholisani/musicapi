package za.co.newplant.musicapi.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import za.co.newplant.musicapi.exception.error.ApiError;
import za.co.newplant.musicapi.exception.error.ApiSubError;
import za.co.newplant.musicapi.exception.error.Error;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(value = {ResourceAlreadyExists.class})
    public ResponseEntity<Error> handleAnyException(Exception ex, WebRequest request) {
        Error response=new Error(1,"DUPLICATE - " + ex.getMessage());
        LOG.error("handleAnyException DUPLICATE - "+  ex.getMessage());
        return new ResponseEntity<Error>(response, new HttpHeaders(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiError> customException(CustomException ex) {
        LOG.error("customException - "+  ex.getMessage());
        Set<ApiSubError> messages = new TreeSet<>();
        messages.addAll(ex.getApiError().getSubErrors().stream().map(fieldError ->
                new ApiSubError(fieldError.getField(),fieldError.getMessage())).collect(Collectors.toList()));
        ApiError response=new ApiError(2,  "Validation errors",messages);

        return new ResponseEntity<ApiError>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Error> resourceNotFound(ResourceNotFoundException ex){
        Error response=new Error(3,"NOT_FOUND - " + ex.getMessage());
        LOG.error("resourceNotFound - "+  ex.getMessage());
        return new ResponseEntity<Error>(response, HttpStatus.NOT_FOUND);
    }




    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

        Set<ApiSubError> messages = new TreeSet<>();
        messages.addAll(constraintViolations.stream().map(constraintViolation ->
                new ApiSubError(constraintViolation.getInvalidValue().toString(),
                        constraintViolation.getMessage())).collect(Collectors.toList()));
        ApiError response=new ApiError(4,  "Validation errors",messages);
        LOG.error("handleConstraintViolation - "+  ex.getMessage());
        return new ResponseEntity<ApiError>(response, HttpStatus.BAD_REQUEST);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        BindingResult result = ex.getBindingResult();
        Set<ApiSubError> messages = new TreeSet<>();
        messages.addAll(result.getFieldErrors().stream().map(fieldError ->
                new ApiSubError(fieldError.getField(),fieldError.getDefaultMessage())).collect(Collectors.toList()));
        ApiError response=new ApiError(4,  "Validation errors",messages);
        LOG.error("handleMethodArgumentNotValid - "+  ex.getMessage());
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Error> handleAllUncaughtException(
            Exception exception,
            WebRequest request){
        LOG.error("Unknown error occurred", exception);
        Error response=new Error(2,  exception.getMessage());
        LOG.error("customException - "+  exception.getMessage());
        return new ResponseEntity<Error>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}




