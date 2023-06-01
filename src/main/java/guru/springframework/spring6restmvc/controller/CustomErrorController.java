package guru.springframework.spring6restmvc.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jt, Spring Framework Guru.
 */
@ControllerAdvice
public class CustomErrorController {
// This is to handle Any transaction exception
    //In Spring, a transaction is like a special box
// that ensures that the money transfer happens correctly and completely.
    @ExceptionHandler
    ResponseEntity handleException(TransactionException exception) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.badRequest();
        // provides a convenient way to build and customize the response body for an HTTP response
//So we are see weather the exception is instance of ConstraintViolationException
        //if it is then we are going to get the constraint violation
        //ConstraintViolationException is a type of exception that is thrown when the
        //state of an object does not match the constraints that are defined on that object's
        //So for us the name is more then the length of 50
        //We then create a map and every ConstraintViolationException we are going to
        //put the property path and the message in the map-> the one that
        //@Size will give us and then we will return the map as a list and Display it to the console

        if (exception.getCause().getCause() instanceof ConstraintViolationException){
            ConstraintViolationException ve = (ConstraintViolationException) exception.getCause().getCause();
            List errors = ve.getConstraintViolations().stream()
                    .map(constraintViolation -> {
                        Map<String,String> errMap = new HashMap<>();
                        errMap.put(constraintViolation.getPropertyPath().toString()
                                ,constraintViolation.getMessage());

                        return errMap;
                    }).toList();
            return responseEntity.body(errors);
        }
        return responseEntity.build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity handleBindErrors(MethodArgumentNotValidException exception){

        List errorList = exception.getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, String > errorMap = new HashMap<>();
                    errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                    return errorMap;
                }).collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errorList);
    }
}
